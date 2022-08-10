package ua.cc.spon.shlibot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ua.cc.spon.shlibot.service.MessageService;
import ua.cc.spon.shlibot.service.TelegramUserService;

/**
 * Stop {@link Command}.
 */
public class StopCommand implements Command {

    private final MessageService messageService;
    private final TelegramUserService telegramUserService;

    public StopCommand(MessageService messageService, TelegramUserService telegramUserService) {
        this.messageService = messageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();

        messageService.sendMessageUserStop(chatId);

        telegramUserService.findByChatId(chatId)
                .ifPresent(user -> {
                    user.setActive(false);
                    telegramUserService.save(user);
                });
    }
}
