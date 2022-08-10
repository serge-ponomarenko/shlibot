package ua.cc.spon.shlibot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ua.cc.spon.shlibot.repository.entity.Product;
import ua.cc.spon.shlibot.repository.entity.UserList;
import ua.cc.spon.shlibot.service.MessageService;
import ua.cc.spon.shlibot.service.ProductService;
import ua.cc.spon.shlibot.service.TelegramUserService;

import java.util.Date;
import java.util.Optional;

/**
 * No {@link Command}.
 */
public class NoCommand implements Command {

    private final MessageService messageService;
    private final TelegramUserService telegramUserService;
    private final ProductService productService;

    public NoCommand(MessageService messageService,
                     TelegramUserService telegramUserService,
                     ProductService productService) {
        this.messageService = messageService;
        this.telegramUserService = telegramUserService;
        this.productService = productService;
    }

    @Override
    public void execute(Update update) {

        String chatId = Optional.ofNullable(update.getMessage())
                .orElseGet(() -> update.getCallbackQuery().getMessage())
                .getChatId().toString();
        String message = Optional.ofNullable(update.getMessage().getText())
                .orElseGet(() -> update.getCallbackQuery().getData());

        telegramUserService.findByChatIdAndActiveIsTrue(chatId).ifPresentOrElse(
                user -> {
                    UserList mainList = user.getMainList();

                    Product product = new Product();
                    product.setName(message);
                    product.setCreationDate(new Date());
                    product.setList(mainList);

                    productService.save(product);

                    messageService.sendMessageProductAdded(chatId, product, mainList);
                },
                () -> messageService.sendMessageUserNotRegistered(chatId)
        );
    }
}
