package ru.sberbank.project.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import ru.sberbank.project.model.Role;
import ru.sberbank.project.model.User;
import ru.sberbank.project.model.UserTo;

import java.time.LocalDate;
import java.time.LocalTime;

public class UserUtil {

    public static User createNewFromTo(UserTo newUser) {
        return new User(null, newUser.getName(), newUser.getLastName(), newUser.getEmail().toLowerCase(), newUser.getPassword(), LocalDate.of(newUser.getBirthdayYear(), newUser.getBirthdayMonth(), newUser.getBirthdayDay()).atTime(LocalTime.MIN), Role.ROLE_USER);
    }

    public static UserTo asTo(User user) {
        return new UserTo(user.getId(), user.getName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getBirthday());
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setLastName(userTo.getLastName());
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setBirthday(LocalDate.of(userTo.getBirthdayYear(), userTo.getBirthdayMonth(), userTo.getBirthdayDay()).atTime(LocalTime.MIN));
        user.setPassword(userTo.getPassword());
        return user;
    }

    public static User prepareToSave(User user, PasswordEncoder passwordEncoder) {
        String password = user.getPassword();
        user.setPassword(StringUtils.isEmpty(password) ? password : passwordEncoder.encode(password));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}