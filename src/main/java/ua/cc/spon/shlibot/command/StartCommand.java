package ua.cc.spon.shlibot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ua.cc.spon.shlibot.service.SendBotMessageService;

/**
 * Start {@link Command}.
 */
public class StartCommand implements Command {

    private final SendBotMessageService sendBotMessageService;

    //todo: realize internationalization
    public final static String START_MESSAGE = "Привет. Это бот для списка покупок. Просто пиши в меня товары, " +
            "которые тебе нужно купить, я их сложу в удобный список. Ты можешь шарить списки с друзьями и семьей, " +
            "а также создавать другие списки.";

    public StartCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), START_MESSAGE);
    }
}
