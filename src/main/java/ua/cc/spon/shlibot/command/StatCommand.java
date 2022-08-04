package ua.cc.spon.shlibot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ua.cc.spon.shlibot.repository.entity.TelegramUser;
import ua.cc.spon.shlibot.service.SendBotMessageService;
import ua.cc.spon.shlibot.service.TelegramUserService;

/**
 * Stat {@link Command}.
 */
public class StatCommand implements Command {

    //todo: realize internationalization
    public static final String STAT_MESSAGE = "Сейчас бот испльзуют %s человек.";

    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    public StatCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        int activeUserCount = telegramUserService.retrieveAllActiveUsers().size();
        String chatId = update.getMessage().getChatId().toString();
        sendBotMessageService.sendMessage(chatId, String.format(STAT_MESSAGE, activeUserCount));
    }
}
