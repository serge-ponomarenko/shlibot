package ua.cc.spon.shlibot.repository.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Telegram User entity.
 */
@Data
@Entity
@Table(name = "tg_user")
public class TelegramUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(name = "chat_id")
    private String chatId;

    @Column(name = "active")
    private boolean active;
}
