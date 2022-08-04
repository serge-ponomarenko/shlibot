package ua.cc.spon.shlibot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ua.cc.spon.shlibot.repository.entity.Product;
import ua.cc.spon.shlibot.repository.entity.TelegramUser;
import ua.cc.spon.shlibot.repository.entity.UserList;
import ua.cc.spon.shlibot.service.ProductService;
import ua.cc.spon.shlibot.service.SendBotMessageService;
import ua.cc.spon.shlibot.service.TelegramUserService;
import ua.cc.spon.shlibot.service.UserListService;

import java.time.LocalDate;
import java.util.Date;

/**
 * No {@link Command}.
 */
public class NoCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;
    private final UserListService userListService;
    private final ProductService productService;

    //todo: realize internationalization
    public static final String NO_MESSAGE = "<b>%s</b> добавлено в основной список.";
    public static final String NO_USER = "Вы еще не зарегестрированы. Вам следует начать с комманды /start.";

    public NoCommand(SendBotMessageService sendBotMessageService,
                     TelegramUserService telegramUserService,
                     UserListService userListService,
                     ProductService productService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
        this.userListService = userListService;
        this.productService = productService;
    }

    @Override
    public void execute(Update update) {

        String chatId = update.getMessage().getChatId().toString();
        String message = update.getMessage().getText();

        telegramUserService.findByChatIdAndActiveIsTrue(chatId).ifPresentOrElse(
                user -> {
                    UserList mainList = user.getMainList();

                    Product product = new Product();
                    product.setId(0);
                    product.setName(message);
                    product.setCreationDate(new Date());
                    product.setList(mainList);

                    productService.save(product);

                    sendBotMessageService.sendMessage(chatId, String.format(NO_MESSAGE, message));
                },
                () -> {
                    sendBotMessageService.sendMessage(chatId, NO_USER);
                }
            );
    }
}
