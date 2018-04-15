package ru.sberbank.project.repository.user;

import ru.sberbank.project.model.User;

import java.util.List;

public interface UserRepository {

    User save(User user);

    boolean delete(int id);

    User get(int id);

    User getByEmail(String email);

    List<User> getAll();

    List<User> findAllByNameOrLastName(String param);

    List<User> getAllFollowersByUserId(Integer id);

    List<User> getAllFollowingByUserId(Integer id);
}