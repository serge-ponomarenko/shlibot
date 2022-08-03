package ua.cc.spon.shlibot.command;

import org.junit.jupiter.api.DisplayName;

import static ua.cc.spon.shlibot.command.UnknownCommand.UNKNOWN_MESSAGE;

@DisplayName("Unit-level testing for UnknownCommand")
class UnknownCommandTest extends AbstractCommandTest{
    @Override
    String getCommandName() {
        return "/asdasd";
    }

    @Override
    String getCommandMessage() {
        return UNKNOWN_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new UnknownCommand(sendBotMessageService);
    }
}