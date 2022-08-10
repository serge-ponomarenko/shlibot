package ua.cc.spon.shlibot.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ua.cc.spon.shlibot.command.CommandName.*;

@Component
public class KeyboardMarkupManager {

    public static final String CAPTION_MY_LISTS = "\ud83d\uddd2 Мои списки";
    public static final String CAPTION_SETTINGS = "\u2699 Настройки";
    public static final String CAPTION_ANOTHER_LIST = "Другой список";
    public static final String CAPTION_CANCEL = "Отменить";
    public static final String CAPTION_SHOW_LIST = "Посмотреть список";

    public ReplyKeyboardMarkup createMainKeyboard() {

        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        KeyboardRow row = new KeyboardRow();
        List<KeyboardRow> rows = new ArrayList<>();

        KeyboardButton kb = new KeyboardButton();
        kb.setText(CAPTION_MY_LISTS);
        row.add(kb);
        KeyboardButton kb2 = new KeyboardButton();
        kb2.setText(CAPTION_SETTINGS);
        row.add(kb2);

        rows.add(row);

        markup.setResizeKeyboard(true);
        markup.setKeyboard(rows);

        return markup;
    }

    public InlineKeyboardMarkup createInlineKeyboardUserLists(Map<Integer, String> lists) {

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();

        for (Map.Entry<Integer, String> entry : lists.entrySet()) {
            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
            inlineKeyboardButton.setText(entry.getValue());
            inlineKeyboardButton.setCallbackData(SHOW_LIST.getCommandName() + " " + entry.getKey());
            rowInline.add(inlineKeyboardButton);
        }

        // Set the keyboard to the markup
        rowsInline.add(rowInline);
        // Add it to the message
        markupInline.setKeyboard(rowsInline);

        return markupInline;
    }

    public InlineKeyboardMarkup createInlineKeyboardAfterAdded(String productId, String listId) {

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();

        InlineKeyboardButton ikb1 = new InlineKeyboardButton();

        ikb1.setText(CAPTION_ANOTHER_LIST);
        ikb1.setCallbackData(MOVE_TO_ANOTHER_LIST_REQUEST.getCommandName() + " " + productId);
        InlineKeyboardButton ikb2 = new InlineKeyboardButton();
        ikb2.setText(CAPTION_CANCEL);
        ikb2.setCallbackData(CANCEL_ADD.getCommandName()+ " " + productId);
        InlineKeyboardButton ikb3 = new InlineKeyboardButton();
        ikb3.setText(CAPTION_SHOW_LIST);
        ikb3.setCallbackData(SHOW_LIST.getCommandName() + " " + listId);
        rowInline.add(ikb1);
        rowInline.add(ikb2);
        rowInline.add(ikb3);

        // Set the keyboard to the markup
        rowsInline.add(rowInline);
        // Add it to the message
        markupInline.setKeyboard(rowsInline);

        return markupInline;
    }

    public ReplyKeyboard createInlineKeyboardUserListsToMove(Map<Integer, String> lists, String productId) {

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();

        for (Map.Entry<Integer, String> entry : lists.entrySet()) {
            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
            inlineKeyboardButton.setText(entry.getValue());
            inlineKeyboardButton.setCallbackData(MOVE_TO_LIST.getCommandName() + " " + entry.getKey() + ":" + productId);
            rowInline.add(inlineKeyboardButton);
        }

        // Set the keyboard to the markup
        rowsInline.add(rowInline);
        // Add it to the message
        markupInline.setKeyboard(rowsInline);

        return markupInline;
    }
}
