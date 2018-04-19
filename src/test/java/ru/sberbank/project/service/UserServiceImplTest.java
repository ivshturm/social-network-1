package ru.sberbank.project.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.sberbank.project.data.UserTestData;
import ru.sberbank.project.model.Role;
import ru.sberbank.project.model.User;
import ru.sberbank.project.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/testDbPopulate.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserServiceImplTest {

    @Autowired
    protected UserService service;

    @Test
    public void create() {
        User newUser = new User(null, "User", "Testov", "new@gmail.com", "newPass",
                LocalDate.of(2001, 1, 1).atStartOfDay(), Role.ROLE_USER);
        User created = service.create(newUser);
        newUser.setId(created.getId());
        UserTestData.assertMatch(service.getAll(), UserTestData.USER_2, newUser, UserTestData.USER_1);
    }

    @Test(expected = DataAccessException.class)
    public void duplicateMailCreate() throws Exception {
        service.create(new User(null, "Duplicate", "Testov",  "user@yandex.ru", "newPass",
                LocalDate.of(2001, 1, 1).atStartOfDay(), Role.ROLE_USER));
    }

    @Test
    public void delete() {
        service.delete(UserTestData.USER_1_ID);
        UserTestData.assertMatch(service.getAll(), UserTestData.USER_2);
    }

    @Test(expected = NotFoundException.class)
    public void notFoundDelete() throws Exception {
        service.delete(1);
    }

    @Test
    public void get() throws Exception {
        User user = service.get(UserTestData.USER_1_ID);
        UserTestData.assertMatch(user, UserTestData.USER_1);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(1);
    }

    @Test
    public void update() throws Exception {
        User updated = new User(UserTestData.USER_1);
        updated.setName("UpdatedName");
        updated.setLastName("UpdatedLastName");
        service.update(updated);
        UserTestData.assertMatch(service.get(UserTestData.USER_1_ID), updated);
    }

    @Test
    public void getAll() throws Exception {
        List<User> all = service.getAll();
        UserTestData.assertMatch(all, UserTestData.USER_2, UserTestData.USER_1);
    }

    @Test
    public void findAllByNameOrLastNameTwoUsers() {
        List<User> all = service.findAllByNameOrLastName("test");
        UserTestData.assertMatch(all, UserTestData.USER_1, UserTestData.USER_2);
    }

    @Test
    public void findAllByNameOrLastNameOneUser() {
        List<User> all = service.findAllByNameOrLastName("user");
        UserTestData.assertMatch(all, UserTestData.USER_1);
    }

    @Test
    public void findAllByNameOrLastNameNull() {
        List<User> all = service.findAllByNameOrLastName("parameter");
        UserTestData.assertMatch(all);
    }

    @Test
    public void getAllFollowersByUserId() {
        List<User> all = service.getAllFollowersByUserId(UserTestData.USER_1_ID);
        UserTestData.assertMatch(all, UserTestData.USER_2);
    }

    @Test
    public void getAllFollowingByUserId() {
        List<User> all = service.getAllFollowersByUserId(UserTestData.USER_1_ID);
        UserTestData.assertMatch(all, UserTestData.USER_2);
    }

    @Test
    public void subscribeToUser() {
        User newUser = new User(null, "User", "Testov", "new@gmail.com", "newPass",
                LocalDate.of(2001, 1, 1).atStartOfDay(), Role.ROLE_USER);
        User created = service.create(newUser);
        int createdId = created.getId();
        newUser.setId(createdId);
        service.subscribeToUser(createdId, UserTestData.USER_1_ID);
        UserTestData.assertMatch(service.getAllFollowersByUserId(UserTestData.USER_1_ID), UserTestData.USER_2, newUser);
    }

    @Test
    public void unsubscribeToUser() {
        service.unsubscribeToUser(UserTestData.USER_1_ID, UserTestData.USER_2_ID);
        List<User> followers = service.getAllFollowersByUserId(UserTestData.USER_2_ID);
        UserTestData.assertMatch(followers);
    }

    @Test
    public void checkSubscribeTrue() {
        Assert.assertNotNull(service.checkSubscribe(UserTestData.USER_1_ID, UserTestData.USER_2_ID));
    }

    @Test
    public void checkSubscribeFalse() {
        service.unsubscribeToUser(UserTestData.USER_1_ID, UserTestData.USER_2_ID);
        Assert.assertNull(service.checkSubscribe(UserTestData.USER_1_ID, UserTestData.USER_2_ID));
    }
}