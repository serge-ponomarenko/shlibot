package ua.cc.spon.shlibot.command;

import com.google.common.collect.ImmutableMap;
import ua.cc.spon.shlibot.service.*;

import static ua.cc.spon.shlibot.command.CommandName.*;

/**
 * Container of the {@link Command}s, which are using for handling telegram commands.
 */
public class CommandContainer {

    private final ImmutableMap<String, Command> commandMap;
    private final Command noCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService,
                            TelegramUserService telegramUserService,
                            UserListService userListService,
                            ProductService productService,
                            MessageService messageService) {
        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand(messageService, telegramUserService, userListService))
                .put(STOP.getCommandName(), new StopCommand(messageService, telegramUserService))
                .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .put(STAT.getCommandName(), new StatCommand(sendBotMessageService, telegramUserService))
                .put(MY_LISTS.getCommandName(), new MyListsCommand(messageService, telegramUserService))
                .put(SHOW_LIST.getCommandName(), new ShowListCommand(messageService, productService, userListService))
                .put(CANCEL_ADD.getCommandName(), new CancelAddCommand(messageService, productService))
                .put(MOVE_TO_ANOTHER_LIST_REQUEST.getCommandName(), new MoveToAnotherListRequestCommand(messageService, telegramUserService))
                .put(MOVE_TO_LIST.getCommandName(), new MoveToListCommand(messageService, productService, userListService))
                .build();

        noCommand = new NoCommand(messageService, telegramUserService, productService);
    }

    public Command retrieveCommand(String commandIdentifier) {

        String command = commandMap.keySet().stream()
                .filter(commandIdentifier::startsWith)
                .findFirst()
                .orElse(commandIdentifier);

        return commandMap.getOrDefault(command, noCommand);
    }

}
