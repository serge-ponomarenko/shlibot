package ua.cc.spon.shlibot.command;

import org.junit.jupiter.api.DisplayName;

import static ua.cc.spon.shlibot.command.CommandName.NO;
import static ua.cc.spon.shlibot.command.NoCommand.NO_USER;

@DisplayName("Unit-level testing for NoCommand")
class NoCommandTest extends AbstractCommandTest{
    @Override
    String getCommandName() {
        return NO.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return NO_USER;
    }

    @Override
    Command getCommand() {
        return new NoCommand(sendBotMessageService,
                telegramUserService, userListService, productService);
    }
}