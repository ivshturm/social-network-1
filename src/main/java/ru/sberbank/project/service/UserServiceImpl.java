package ru.sberbank.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.sberbank.project.model.Relation;
import ru.sberbank.project.repository.relation.RelationRepository;
import ru.sberbank.project.security.AuthorizedUser;
import ru.sberbank.project.model.User;
import ru.sberbank.project.repository.user.UserRepository;
import ru.sberbank.project.model.UserTo;
import ru.sberbank.project.util.exception.NotFoundException;

import java.util.List;
import java.util.Objects;

import static ru.sberbank.project.util.UserUtil.prepareToSave;
import static ru.sberbank.project.util.UserUtil.updateFromTo;
import static ru.sberbank.project.util.ValidationUtil.checkNotFound;
import static ru.sberbank.project.util.ValidationUtil.checkNotFoundWithId;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository repository;
    private final RelationRepository relationRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, RelationRepository relationRepository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.relationRepository = relationRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(prepareToSave(user, passwordEncoder));
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @Override
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        checkNotFoundWithId(repository.save(prepareToSave(user, passwordEncoder)), user.getId());
    }

    @Transactional
    @Override
    public void update(UserTo userTo) {
        User user = updateFromTo(get(userTo.getId()), userTo);
        repository.save(prepareToSave(user, passwordEncoder));
    }

    @Override
    public List<User> findAllByNameOrLastName(String param) {
        Assert.notNull(param, "parameter must not be null");
        return repository.findAllByNameOrLastName(param);
    }

    @Override
    public List<User> getAllFollowersByUserId(Integer id) {
        return repository.getAllFollowersByUserId(id);
    }

    @Override
    public List<User> getAllFollowingByUserId(Integer id) {
        return repository.getAllFollowingByUserId(id);
    }

    @Override
    public void subscribeToUser(int authUserId, int userIdToSubscribe) {
        relationRepository.save(new Relation(authUserId, userIdToSubscribe));
    }

    @Override
    public void unsubscribeToUser(int authUserId, int userIdToUnsubscribe) {
        relationRepository.delete(new Relation(authUserId, userIdToUnsubscribe));
    }

    @Override
    public Relation checkSubscribe(int authUserId, int verifiableUserId) {
        return relationRepository.find(authUserId, verifiableUserId);
    }

    @Override
    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }
}