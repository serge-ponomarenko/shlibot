package ua.cc.spon.shlibot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import ua.cc.spon.shlibot.repository.entity.TelegramUser;
import ua.cc.spon.shlibot.repository.entity.UserList;
import ua.cc.spon.shlibot.service.SendBotMessageService;
import ua.cc.spon.shlibot.service.TelegramUserService;
import ua.cc.spon.shlibot.service.UserListService;

import java.util.HashSet;
import java.util.Set;

/**
 * Start {@link Command}.
 */
public class StartCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;
    private final UserListService userListService;

    //todo: realize internationalization
    public static final String START_MESSAGE = "Привет. Это бот для списка покупок. Просто пиши в меня товары, " +
            "которые тебе нужно купить, я их сложу в удобный список. Ты можешь шарить списки с друзьями и семьей, " +
            "а также создавать другие списки.";

    public StartCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService, UserListService userListService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
        this.userListService = userListService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        String userName = update.getMessage().getFrom().getUserName();
        telegramUserService.findByChatId(chatId).ifPresentOrElse(
                user -> {
                    user.setActive(true);
                    user.setName(userName);
                    telegramUserService.save(user);
                },
                () -> {
                    TelegramUser telegramUser = new TelegramUser();
                    telegramUser.setActive(true);
                    telegramUser.setChatId(chatId);
                    telegramUser.setName(userName);
                    telegramUserService.save(telegramUser);

                    UserList mainList = new UserList();
                    mainList.setName("Основной");
                    mainList.setOwner(telegramUser);

                    Set<TelegramUser> users = new HashSet<>();
                    users.add(telegramUser);
                    mainList.setUsers(users);
                    userListService.save(mainList);

                    telegramUser.setMainList(mainList);
                    telegramUserService.save(telegramUser);
                }
        );
        sendBotMessageService.sendMessage(chatId, START_MESSAGE);
    }
}
