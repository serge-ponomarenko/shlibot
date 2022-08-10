package ua.cc.spon.shlibot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ua.cc.spon.shlibot.repository.entity.TelegramUser;
import ua.cc.spon.shlibot.repository.entity.UserList;
import ua.cc.spon.shlibot.service.MessageService;
import ua.cc.spon.shlibot.service.TelegramUserService;
import ua.cc.spon.shlibot.service.UserListService;

import java.util.HashSet;
import java.util.Set;

/**
 * Start {@link Command}.
 */

public class StartCommand implements Command {

    public static final String CAPTION_MAIN_LIST_NAME = "Основной";

    private final MessageService messageService;
    private final TelegramUserService telegramUserService;
    private final UserListService userListService;

    public StartCommand(MessageService messageService,
                        TelegramUserService telegramUserService,
                        UserListService userListService) {
        this.messageService = messageService;
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
                    mainList.setName(CAPTION_MAIN_LIST_NAME);
                    mainList.setOwner(telegramUser);

                    Set<TelegramUser> users = new HashSet<>();
                    users.add(telegramUser);
                    mainList.setUsers(users);
                    userListService.save(mainList);

                    telegramUser.setMainList(mainList);
                    telegramUserService.save(telegramUser);
                }
        );

        messageService.sendStartMessage(chatId);
    }
}
