package ua.cc.spon.shlibot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ua.cc.spon.shlibot.service.MessageService;
import ua.cc.spon.shlibot.service.TelegramUserService;

import java.util.Optional;

/**
 * MoveToAnotherList {@link Command}.
 */

public class MoveToAnotherListRequestCommand implements Command {


    private final MessageService messageService;
    private final TelegramUserService telegramUserService;

    public MoveToAnotherListRequestCommand(MessageService messageService, TelegramUserService telegramUserService) {
        this.messageService = messageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
        String productId = update.getCallbackQuery().getData()
                .substring(CommandName.MOVE_TO_ANOTHER_LIST_REQUEST.getCommandName().length()).trim();

        telegramUserService.findByChatIdAndActiveIsTrue(chatId).ifPresentOrElse(
                user -> Optional.ofNullable(user.getUserLists())
                        .ifPresentOrElse(userLists -> messageService.sendMessageChooseListToMove(chatId, productId, userLists),
                                () -> messageService.sendMessageUserHasNoLists(chatId)
                        ),
                () -> messageService.sendMessageUserNotRegistered(chatId)
        );
    }
}
