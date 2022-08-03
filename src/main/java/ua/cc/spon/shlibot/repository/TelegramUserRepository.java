package ua.cc.spon.shlibot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.cc.spon.shlibot.repository.entity.TelegramUser;

import java.util.List;
import java.util.Optional;

/**
 * {@link Repository} for handling with {@link TelegramUser} entity.
 */
@Repository
public interface TelegramUserRepository extends JpaRepository<TelegramUser, Integer> {

    List<TelegramUser> findAllByActiveTrue();

    Optional<TelegramUser> findByChatId(String chatId);
}
