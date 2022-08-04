package ua.cc.spon.shlibot.service;

import ua.cc.spon.shlibot.repository.entity.TelegramUser;
import ua.cc.spon.shlibot.repository.entity.UserList;

import java.util.Set;

/**
 * {@link Service} for handling {@link UserList} entity.
 */
public interface UserListService {

    /**
     * Save provided {@link UserList} entity.
     *
     * @param  userList provided telegram user.
     */
    void save(UserList userList);

    Set<UserList> findByOwner(TelegramUser telegramUser);

}
