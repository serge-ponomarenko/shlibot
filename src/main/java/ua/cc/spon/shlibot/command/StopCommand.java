package ua.cc.spon.shlibot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ua.cc.spon.shlibot.service.SendBotMessageService;
import ua.cc.spon.shlibot.service.TelegramUserService;

/**
 * Stop {@link Command}.
 */
public class StopCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    //todo: realize internationalization
    public final static String STOP_MESSAGE = "Я не буду больше тебе присылать никаких уведомлений. " +
            "Но если ты вдруг решишь вернуться, то все твои списки останутся не тронутыми. \uD83D\uDE1F";

    public StopCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        sendBotMessageService.sendMessage(chatId, STOP_MESSAGE);
        telegramUserService.findByChatId(chatId)
                .ifPresent(user -> {
                    user.setActive(false);
                    telegramUserService.save(user);
                });
    }
}
