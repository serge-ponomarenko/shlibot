package ua.cc.spon.shlibot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.cc.spon.shlibot.repository.TelegramUserRepository;
import ua.cc.spon.shlibot.repository.entity.TelegramUser;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link TelegramUserService}.
 */
@Service
public class TelegramUserServiceImpl implements TelegramUserService {

    private final TelegramUserRepository telegramUserRepository;

    @Autowired
    public TelegramUserServiceImpl(TelegramUserRepository telegramUserRepository) {
        this.telegramUserRepository = telegramUserRepository;
    }

    @Override
    public void save(TelegramUser telegramUser) {
        telegramUserRepository.save(telegramUser);
    }

    @Override
    public List<TelegramUser> retrieveAllActiveUsers() {
        return telegramUserRepository.findAllByActiveTrue();
    }

    @Override
    public Optional<TelegramUser> findByChatId(String chatId) {
        return telegramUserRepository.findByChatId(chatId);
    }

    @Override
    public Optional<TelegramUser> findByChatIdAndActiveIsTrue(String chatId) {
        return telegramUserRepository.findByChatIdAndActiveIsTrue(chatId);
    }
}
