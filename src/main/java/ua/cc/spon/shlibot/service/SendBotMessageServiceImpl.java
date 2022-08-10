package ua.cc.spon.shlibot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ua.cc.spon.shlibot.bot.ShoppingListTelegramBot;

/**
 * Implementation of {@link SendBotMessageService} interface.
 */
@Service
public class SendBotMessageServiceImpl implements SendBotMessageService {

    private final ShoppingListTelegramBot shoppingListBot;

    @Autowired
    public SendBotMessageServiceImpl(ShoppingListTelegramBot shoppingListBot) {
        this.shoppingListBot = shoppingListBot;
    }

    @Override
    public void sendMessage(String chatId, String message) {
        sendMessage(chatId, message, null);
    }

    @Override
    public void sendMessage(String chatId, String message, ReplyKeyboard keyboard) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText(message);

        if (keyboard != null) sendMessage.setReplyMarkup(keyboard);

        try {
            shoppingListBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            //todo: add logging
            e.printStackTrace();
        }
    }
}
