package ua.cc.spon.shlibot.command;

import org.junit.jupiter.api.DisplayName;

import static ua.cc.spon.shlibot.command.CommandName.HELP;
import static ua.cc.spon.shlibot.command.HelpCommand.HELP_MESSAGE;

@DisplayName("Unit-level testing for HelpCommand")
class HelpCommandTest extends AbstractCommandTest{
    @Override
    String getCommandName() {
        return HELP.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return HELP_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new HelpCommand(sendBotMessageService);
    }
}