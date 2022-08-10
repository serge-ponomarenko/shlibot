package ua.cc.spon.shlibot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ua.cc.spon.shlibot.service.MessageService;
import ua.cc.spon.shlibot.service.TelegramUserService;

import java.util.Optional;

/**
 * MyLists {@link Command}.
 */

public class MyListsCommand implements Command {


    private final MessageService messageService;
    private final TelegramUserService telegramUserService;

    public MyListsCommand(MessageService messageService, TelegramUserService telegramUserService) {
        this.messageService = messageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        String chatId = Optional.ofNullable(update.getMessage())
                .orElseGet(() -> update.getCallbackQuery().getMessage())
                .getChatId().toString();

        telegramUserService.findByChatIdAndActiveIsTrue(chatId).ifPresentOrElse(
                user -> Optional.ofNullable(user.getUserLists())
                        .ifPresentOrElse(userLists -> messageService.sendMessageShowUserLists(chatId, userLists),
                                () -> messageService.sendMessageUserHasNoLists(chatId)
                        ),
                () -> messageService.sendMessageUserNotRegistered(chatId)
        );

    }
}
