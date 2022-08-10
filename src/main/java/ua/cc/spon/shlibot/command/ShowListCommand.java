package ua.cc.spon.shlibot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ua.cc.spon.shlibot.repository.entity.Product;
import ua.cc.spon.shlibot.repository.entity.UserList;
import ua.cc.spon.shlibot.service.MessageService;
import ua.cc.spon.shlibot.service.ProductService;
import ua.cc.spon.shlibot.service.UserListService;

import java.util.List;

/**
 * ShowList {@link Command}.
 */

public class ShowListCommand implements Command {


    private final MessageService messageService;
    private final ProductService productService;
    private final UserListService userListService;

    public ShowListCommand(MessageService messageService, ProductService productService, UserListService userListService) {
        this.messageService = messageService;
        this.productService = productService;
        this.userListService = userListService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
        int listId = Integer.parseInt(update.getCallbackQuery().getData()
                .substring(CommandName.SHOW_LIST.getCommandName().length()).trim());

        UserList userList = userListService.findById(listId).orElseThrow();

        List<Product> content = productService.getAllByListIs(userList);
        if (!content.isEmpty()) {
            messageService.sendMessageListContent(chatId, userList.getName(), content);
        } else {
            messageService.sendMessageListIsEmpty(chatId, userList.getName());
        }

    }
}
