package ua.cc.spon.shlibot.command;

import org.junit.jupiter.api.DisplayName;

import static ua.cc.spon.shlibot.command.CommandName.STAT;
import static ua.cc.spon.shlibot.command.StatCommand.STAT_MESSAGE;

@DisplayName("Unit-level testing for StatCommand")
class StatCommandTest extends AbstractCommandTest{
    @Override
    String getCommandName() {
        return STAT.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return String.format(STAT_MESSAGE, 0);
    }

    @Override
    Command getCommand() {
        return new StatCommand(sendBotMessageService, telegramUserService);
    }
}