package ua.cc.spon.shlibot.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ua.cc.spon.shlibot.command.CommandContainer;
import ua.cc.spon.shlibot.service.*;

@Component
public class ShoppingListTelegramBot extends TelegramLongPollingBot {

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    private final CommandContainer commandContainer;

    @Autowired
    public ShoppingListTelegramBot(TelegramUserService telegramUserService,
                                   UserListService userListService,
                                   ProductService productService) {

        KeyboardMarkupManager keyboardMarkupManager = new KeyboardMarkupManager();
        SendBotMessageService sendBotMessageService = new SendBotMessageServiceImpl(this);
        MessageService messageService = new MessageService(keyboardMarkupManager, sendBotMessageService);
        this.commandContainer =
                new CommandContainer(sendBotMessageService,
                        telegramUserService, userListService,
                        productService, messageService);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String commandIdentifier = update.getMessage().getText().trim();
            commandContainer.retrieveCommand(commandIdentifier).execute(update);

        }
        if (update.hasCallbackQuery()) {
            String callBackQuery = update.getCallbackQuery().getData();
            System.out.println(callBackQuery);
            commandContainer.retrieveCommand(callBackQuery).execute(update);

            AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery(update.getCallbackQuery().getId());
            EditMessageReplyMarkup editMessageReplyMarkup = new EditMessageReplyMarkup();
            editMessageReplyMarkup.setChatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()));
            editMessageReplyMarkup.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
            editMessageReplyMarkup.setReplyMarkup(null);
            try {
                execute(answerCallbackQuery);
                execute(editMessageReplyMarkup);
            } catch (TelegramApiException e) {}  // TODO: 09.08.2022  


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
