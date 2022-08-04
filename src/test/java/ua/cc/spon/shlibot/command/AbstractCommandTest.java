package ua.cc.spon.shlibot.command;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ua.cc.spon.shlibot.bot.ShoppingListTelegramBot;
import ua.cc.spon.shlibot.service.*;

/**
 * Abstract class for testing {@link Command}s.
 */
abstract class AbstractCommandTest {

    protected ShoppingListTelegramBot shoppingListTelegramBot = Mockito.mock(ShoppingListTelegramBot.class);
    protected SendBotMessageService sendBotMessageService = new SendBotMessageServiceImpl(shoppingListTelegramBot);
    protected TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);
    protected UserListService userListService = Mockito.mock(UserListService.class);
    protected ProductService productService = Mockito.mock(ProductService.class);

    abstract String getCommandName();

    abstract String getCommandMessage();

    abstract Command getCommand();

    @Test
    public void shouldProperlyExecuteCommand() throws TelegramApiException {
        //given
        Long chatId = 1234567890123L;

        Update update = new Update();
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.getChatId()).thenReturn(chatId);
        Mockito.when(message.getText()).thenReturn(getCommandName());
        Mockito.when(message.getFrom()).thenReturn(new User());
        update.setMessage(message);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(getCommandMessage());
        sendMessage.enableHtml(true);

        //when
        getCommand().execute(update);

        //then
        Mockito.verify(shoppingListTelegramBot).execute(sendMessage);
    }

}
