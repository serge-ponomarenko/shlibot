package ua.cc.spon.shlibot.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ua.cc.spon.shlibot.command.CommandContainer;
import ua.cc.spon.shlibot.service.ProductService;
import ua.cc.spon.shlibot.service.SendBotMessageServiceImpl;
import ua.cc.spon.shlibot.service.TelegramUserService;
import ua.cc.spon.shlibot.service.UserListService;

import static ua.cc.spon.shlibot.command.CommandName.NO;

@Component
public class ShoppingListTelegramBot extends TelegramLongPollingBot {

    public static String COMMAND_PREFIX = "/";

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    private final CommandContainer commandContainer;

    @Autowired
    public ShoppingListTelegramBot(TelegramUserService telegramUserService,
                                   UserListService userListService,
                                   ProductService productService) {
        this.commandContainer =
                new CommandContainer(new SendBotMessageServiceImpl(this),
                        telegramUserService, userListService, productService);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();

            if (message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();

                commandContainer.retrieveCommand(commandIdentifier).execute(update);
            } else {
                commandContainer.retrieveCommand(NO.getCommandName()).execute(update);
            }

        }
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}
