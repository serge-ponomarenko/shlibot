package ua.cc.spon.shlibot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import ua.cc.spon.shlibot.bot.KeyboardMarkupManager;
import ua.cc.spon.shlibot.repository.entity.Product;
import ua.cc.spon.shlibot.repository.entity.UserList;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private final KeyboardMarkupManager keyboardMarkupManager;
    private final SendBotMessageService sendBotMessageService;

    @Autowired
    public MessageService(KeyboardMarkupManager keyboardMarkupManager, SendBotMessageService sendBotMessageService) {
        this.keyboardMarkupManager = keyboardMarkupManager;
        this.sendBotMessageService = sendBotMessageService;
    }

    public void sendMessageProductAdded(String chatId, Product product, UserList list) {
        String addedToList = "<b>%s</b> добавлено в список: <b>%s</b>.";


        ReplyKeyboard inlineKeyboardAfterAdded =
                keyboardMarkupManager.createInlineKeyboardAfterAdded(String.valueOf(product.getId()), String.valueOf(list.getId()));

        sendBotMessageService.sendMessage(chatId, String.format(addedToList, product.getName(), list.getName()),
                inlineKeyboardAfterAdded);

    }


    public void sendMessageUserNotRegistered(String chatId) {
        String userNotRegistered = "Вы еще не зарегестрированы или не активны. Вам следует начать с комманды /start.";

        sendBotMessageService.sendMessage(chatId, userNotRegistered);

    }

    public void sendMessageShowUserLists(String chatId, Set<UserList> userLists) {

        String message = "Ваши списки (выберите для дальнейших действий):";

        Map<Integer, String> lists = userLists.stream().collect(Collectors.toMap(UserList::getId, UserList::getName));

        ReplyKeyboard keyboardMarkup = keyboardMarkupManager.createInlineKeyboardUserLists(lists);

        sendBotMessageService.sendMessage(chatId, message, keyboardMarkup);

    }

    public void sendStartMessage(String chatId) {

        String message = "Привет. Это бот для списка покупок. Просто пиши в меня товары, " +
                "которые тебе нужно купить, я их сложу в удобный список. Ты можешь шарить списки с друзьями и семьей, " +
                "а также создавать другие списки.";

        ReplyKeyboard keyboardMarkup = keyboardMarkupManager.createMainKeyboard();
        sendBotMessageService.sendMessage(chatId, message, keyboardMarkup);

    }

    public void sendMessageListContent(String chatId, String listName, List<Product> content) {

        String header = "Список продуктов <b>" + listName + "</b>:\n";
        String collect = content.stream().map(Product::getName).collect(Collectors.joining("\n"));
        sendBotMessageService.sendMessage(chatId, header + collect);

    }

    public void sendMessageListIsEmpty(String chatId, String listName) {
        String message = "Список " + listName + " пустой!";
        sendBotMessageService.sendMessage(chatId, message);
    }

    public void sendMessageNoSuchList(String chatId) {
        String message = "Такого списка у вас нету!";
        sendBotMessageService.sendMessage(chatId, message);
    }

    public void sendMessageUserHasNoLists(String chatId) {
        String message = "У вас нету списков! Создайте новый.";
        sendBotMessageService.sendMessage(chatId, message);
    }

    public void sendMessageUserStop(String chatId) {
        String message = "Я не буду больше тебе присылать никаких уведомлений. " +
                "Но если ты вдруг решишь вернуться, то все твои списки останутся не тронутыми. \uD83D\uDE1F";
        sendBotMessageService.sendMessage(chatId, message);
    }


    public void sendMessageProductNotInUserLists(String chatId) {
        String message = "Этот продукт не находится в ваших списках!";
        sendBotMessageService.sendMessage(chatId, message);
    }

    public void sendMessageCancelAddingLastProduct(String chatId) {
        String message = "Добавление последнего продукта отменено.";
        sendBotMessageService.sendMessage(chatId, message);
    }

    public void sendMessageChooseListToMove(String chatId, String productId, Set<UserList> userLists) {

        String message = "Выберете список, куда переместить:";

        Map<Integer, String> lists = userLists.stream().collect(Collectors.toMap(UserList::getId, UserList::getName));

        ReplyKeyboard keyboardMarkup = keyboardMarkupManager.createInlineKeyboardUserListsToMove(lists, productId);

        sendBotMessageService.sendMessage(chatId, message, keyboardMarkup);
    }


}
