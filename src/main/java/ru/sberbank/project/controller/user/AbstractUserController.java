package ru.sberbank.project.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.sberbank.project.model.Relation;
import ru.sberbank.project.model.User;
import ru.sberbank.project.service.UserService;
import ru.sberbank.project.model.UserTo;
import ru.sberbank.project.util.UserUtil;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static ru.sberbank.project.util.ValidationUtil.assureIdConsistent;
import static ru.sberbank.project.util.ValidationUtil.checkNew;

public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService service;

    public User get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public List<User> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public User create(User user) {
        log.info("create {}", user);
        checkNew(user);
        return service.create(user);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public void update(User user, int id) {
        log.info("update {} with id={}", user, id);
        assureIdConsistent(user, id);
        service.update(user);
    }

    public void update(UserTo userTo, int id) {
        log.info("update {} with id={}", userTo, id);
        assureIdConsistent(userTo, id);
        service.update(userTo);
    }

    public List<UserTo> findAllByNameOrLastName(String param) {
        log.info("findAllByNameOrLastName {}", param);
        if(param.isEmpty()) {
            return Collections.emptyList();
        }
        return service.findAllByNameOrLastName(param)
                .stream()
                .map(UserUtil::asTo)
                .collect(Collectors.toList());
    }

    public List<UserTo> getAllFollowersByUserId(Integer id) {
        log.info("getAllFollowersByUserId {}", id);
        return service.getAllFollowersByUserId(id)
                .stream()
                .map(UserUtil::asTo)
                .collect(Collectors.toList());
    }

    public List<UserTo> getAllFollowingByUserId(Integer id) {
        log.info("getAllFollowingByUserId {}", id);
        return service.getAllFollowingByUserId(id)
                .stream()
                .map(UserUtil::asTo)
                .collect(Collectors.toList());
    }

    public void subscribeToUser(int authUserId, int userIdToSubscribe) {
        log.info("subscribeToUser {} {}", authUserId, userIdToSubscribe);
        service.subscribeToUser(authUserId, userIdToSubscribe);
    }

    public void unsubscribeToUser(int authUserId, int userIdToUnsubscribe) {
        log.info("unsubscribeToUser {} {}", authUserId, userIdToUnsubscribe);
        service.unsubscribeToUser(authUserId, userIdToUnsubscribe);
    }

    public Relation checkSubscribe(int authUserId, int verifiableUserId) {
        log.info("checkSubscribe {} {}", authUserId, verifiableUserId);
        return service.checkSubscribe(authUserId, verifiableUserId);
    }
}