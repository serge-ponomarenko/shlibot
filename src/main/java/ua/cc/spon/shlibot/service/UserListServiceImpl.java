package ua.cc.spon.shlibot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.cc.spon.shlibot.repository.UserListRepository;
import ua.cc.spon.shlibot.repository.entity.TelegramUser;
import ua.cc.spon.shlibot.repository.entity.UserList;

import java.util.List;
import java.util.Set;

/**
 * Implementation of {@link UserListService}.
 */
@Service
public class UserListServiceImpl implements UserListService {

    private final UserListRepository userListRepository;

    @Autowired
    public UserListServiceImpl(UserListRepository userListRepository) {
        this.userListRepository = userListRepository;
    }

    @Override
    public void save(UserList userList) {
        userListRepository.save(userList);
    }

    @Override
    public Set<UserList> findByOwner(TelegramUser telegramUser) {
        return userListRepository.findByOwner(telegramUser);
    }
}
