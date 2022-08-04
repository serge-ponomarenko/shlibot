package ua.cc.spon.shlibot.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Telegram User entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "tg_name")
    private String name;

    @OneToOne
    @JoinColumn(name="mainlist_id")
    private UserList mainList;

    @ManyToMany(mappedBy="users")
    private Set<UserList> userLists = new HashSet<>();
}
