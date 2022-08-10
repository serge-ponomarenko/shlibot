package ua.cc.spon.shlibot.command;

import ua.cc.spon.shlibot.bot.KeyboardMarkupManager;

/**
 * Enumeration for {@link Command}'s.
 */
public enum CommandName {

    START("/start"),
    STOP("/stop"),
    HELP("/help"),
    NO(""),
    STAT("/stat"),
    SHOW_LIST("/showList"),
    MY_LISTS(KeyboardMarkupManager.CAPTION_MY_LISTS),
    SETTINGS(KeyboardMarkupManager.CAPTION_SETTINGS),
    CANCEL_ADD("/cancelAdd"),
    MOVE_TO_ANOTHER_LIST_REQUEST("/moveToAnotherListReq"),
    MOVE_TO_LIST("/moveToList");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
