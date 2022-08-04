package ua.cc.spon.shlibot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.cc.spon.shlibot.repository.entity.TelegramUser;
import ua.cc.spon.shlibot.repository.entity.UserList;

import java.util.Set;

/**
 * {@link Repository} for handling with {@link UserList} entity.
 */
@Repository
public interface UserListRepository extends JpaRepository<UserList, Integer> {

    Set<UserList> findByOwner(TelegramUser user);

}
