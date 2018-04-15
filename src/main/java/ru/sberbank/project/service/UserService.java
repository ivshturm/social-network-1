package ru.sberbank.project.service;


import ru.sberbank.project.model.Relation;
import ru.sberbank.project.model.User;
import ru.sberbank.project.model.UserTo;
import ru.sberbank.project.util.exception.NotFoundException;

import java.util.List;

public interface UserService {

    User create(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    void update(User user);

    void update(UserTo user);

    List<User> getAll();

    List<User> findAllByNameOrLastName(String param);

    List<User> getAllFollowersByUserId(Integer id);

    List<User> getAllFollowingByUserId(Integer id);

    void subscribeToUser(int authUserId, int userIdToSubscribe);

    void unsubscribeToUser(int authUserId, int userIdToUnsubscribe);

    Relation checkSubscribe(int authUserId, int userIdToSubscribe);
}