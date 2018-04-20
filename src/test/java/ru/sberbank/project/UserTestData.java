package ru.sberbank.project;

import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import ru.sberbank.project.model.Role;
import ru.sberbank.project.model.User;

import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTestData {
    public static final int USER_1_ID = TestData.START_SEQ;
    public static final int USER_2_ID = USER_1_ID + 1;

    public static final User USER_1 = new User(USER_1_ID, "User", "Testov", "user@yandex.ru", "password",
            LocalDate.of(2000, 1, 1).atStartOfDay(), Role.ROLE_USER);

    public static final User USER_2 = new User(USER_2_ID, "Test", "Unitov", "test@yandex.ru", "password",
            LocalDate.of(1990, 11, 11).atStartOfDay(), Role.ROLE_USER);

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "password");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "password").isEqualTo(expected);
    }

    public static RequestPostProcessor userAuth(User user) {
        return SecurityMockMvcRequestPostProcessors.httpBasic(user.getEmail(), user.getPassword());
    }
}