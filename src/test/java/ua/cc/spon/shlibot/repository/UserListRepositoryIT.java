package ua.cc.spon.shlibot.repository;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import ua.cc.spon.shlibot.repository.entity.TelegramUser;
import ua.cc.spon.shlibot.repository.entity.UserList;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserListRepositoryIT {

    @Autowired
    private UserListRepository userListRepository;

    @Sql(scripts = {"/sql/clearDbs.sql"})
    @Test
    void shouldProperlySaveUserList() {
        //given
        UserList userList = new UserList();
        Set<UserList> userLists = new HashSet<>();
        Set<TelegramUser> users = new HashSet<>();
        TelegramUser user = Mockito.mock(TelegramUser.class);
        //new TelegramUser(0, "123456789", true, "TestUser", userList, userLists);
        userLists.add(userList);
        users.add(user);
        userList.setName("Test");
        userList.setOwner(user);
        userList.setUsers(users);
        userList.setShareToken("asdasdasd");
        userList.setDeleted(false);


        //when
        UserList saved = userListRepository.save(userList);

        //then
        assertEquals(userList, saved);
    }

}