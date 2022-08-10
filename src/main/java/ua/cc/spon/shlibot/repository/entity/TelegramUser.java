package ua.cc.spon.shlibot.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Telegram User entity.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
    @JoinColumn(name = "mainlist_id")
    private UserList mainList;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private Set<UserList> userLists = new HashSet<>();
}
