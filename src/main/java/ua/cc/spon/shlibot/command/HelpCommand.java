package ua.cc.spon.shlibot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ua.cc.spon.shlibot.service.SendBotMessageService;

import static ua.cc.spon.shlibot.command.CommandName.*;

/**
 * Help {@link Command}.
 */
public class HelpCommand implements Command {

    private final SendBotMessageService sendBotMessageService;

    //todo: realize internationalization
    public static final String HELP_MESSAGE = String.format("✨<b>Дотупные команды</b>✨\n\n"
                    + "<b>Начать\\закончить работу с ботом</b>\n"
                    + "%s - начать работу со мной\n"
                    + "%s - приостановить работу со мной\n\n"
                    + "%s - получить помощь в работе со мной\n"
                    + "%s - получить статистику\n",
            START.getCommandName(), STOP.getCommandName(),
            HELP.getCommandName(), STAT.getCommandName());

    public HelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), HELP_MESSAGE);
    }
}
