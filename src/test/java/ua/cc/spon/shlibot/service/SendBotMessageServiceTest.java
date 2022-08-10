package ua.cc.spon.shlibot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ua.cc.spon.shlibot.bot.ShoppingListTelegramBot;

@DisplayName("Unit-level testing for SendBotMessageService")
class SendBotMessageServiceTest {

    private SendBotMessageService sendBotMessageService;
    private ShoppingListTelegramBot shoppingListBot;

    @BeforeEach
    public void init() {
        shoppingListBot = Mockito.mock(ShoppingListTelegramBot.class);
        sendBotMessageService = new SendBotMessageServiceImpl(shoppingListBot);
    }

    @Test
    public void shouldProperlySendMessage() throws TelegramApiException {
        //given
        String chatId = "test_chat_id";
        String message = "test_message";

        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(message);
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);

        //when
        sendBotMessageService.sendMessage(chatId, message);

        //then
        Mockito.verify(shoppingListBot).execute(sendMessage);
    }



}