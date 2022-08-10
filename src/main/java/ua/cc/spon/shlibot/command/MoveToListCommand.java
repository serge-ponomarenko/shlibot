package ua.cc.spon.shlibot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ua.cc.spon.shlibot.repository.entity.Product;
import ua.cc.spon.shlibot.repository.entity.UserList;
import ua.cc.spon.shlibot.service.MessageService;
import ua.cc.spon.shlibot.service.ProductService;
import ua.cc.spon.shlibot.service.UserListService;

/**
 * MoveToList {@link Command}.
 */


public class MoveToListCommand implements Command {


    private final MessageService messageService;
    private final ProductService productService;
    private final UserListService userListService;

    public MoveToListCommand(MessageService messageService, ProductService productService, UserListService userListService) {
        this.messageService = messageService;
        this.productService = productService;
        this.userListService = userListService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
        String str = update.getCallbackQuery().getData()
                .substring(CommandName.MOVE_TO_LIST.getCommandName().length()).trim();
        int listId = Integer.parseInt(str.split(":")[0]);
        int productId = Integer.parseInt(str.split(":")[1]);

        Product product = productService.findById(productId).orElseThrow();
        UserList list = userListService.findById(listId).orElseThrow();

        product.setList(list);
        productService.save(product);

        messageService.sendMessageProductAdded(chatId, product, list);
    }
}
