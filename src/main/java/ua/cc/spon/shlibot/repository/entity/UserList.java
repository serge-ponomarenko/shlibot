package ua.cc.spon.shlibot.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Target;

import javax.persistence.*;
import java.util.Set;

/**
 * User's List of goods entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lists")
public class UserList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "list_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "share_token")
    private String shareToken;

    @Column(name = "deleted")
    private boolean deleted;

    @OneToOne
    @JoinColumn(name="user_id")
    private TelegramUser owner;

    @ManyToMany
    @JoinTable(name="users_lists"
            , joinColumns={
            @JoinColumn(name="list_id")
    }
            , inverseJoinColumns={
            @JoinColumn(name="user_id")
    }
    )
    private Set<TelegramUser> users;

}
