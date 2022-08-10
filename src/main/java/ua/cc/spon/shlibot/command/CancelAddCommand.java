package ua.cc.spon.shlibot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ua.cc.spon.shlibot.service.MessageService;
import ua.cc.spon.shlibot.service.ProductService;

/**
 * CancelAdd {@link Command}.
 */

public class CancelAddCommand implements Command {


    private final MessageService messageService;
    private final ProductService productService;

    public CancelAddCommand(MessageService messageService, ProductService productService) {
        this.messageService = messageService;
        this.productService = productService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
        int productId = Integer.parseInt(update.getCallbackQuery().getData()
                .substring(CommandName.CANCEL_ADD.getCommandName().length()).trim());

        productService.deleteById(productId);
        messageService.sendMessageCancelAddingLastProduct(chatId);

    }
}
