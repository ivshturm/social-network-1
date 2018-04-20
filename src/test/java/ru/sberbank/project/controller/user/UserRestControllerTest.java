package ru.sberbank.project.controller.user;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import ru.sberbank.project.model.User;
import ru.sberbank.project.model.UserTo;
import ru.sberbank.project.service.UserService;
import ru.sberbank.project.util.JacksonObjectMapper;
import ru.sberbank.project.util.UserUtil;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.sberbank.project.controller.user.UserRestController.REST_URL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static ru.sberbank.project.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-mvc.xml",
        "classpath:spring/spring-db.xml"
})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/testDbPopulate.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserRestControllerTest {

    private static final CharacterEncodingFilter CHARACTER_ENCODING_FILTER = new CharacterEncodingFilter();

    static {
        CHARACTER_ENCODING_FILTER.setEncoding("UTF-8");
        CHARACTER_ENCODING_FILTER.setForceEncoding(true);
    }

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserService userService;

    @PostConstruct
    private void postConstruct() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilter(CHARACTER_ENCODING_FILTER)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void getAuthUser() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userAuth(USER_1)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(USER_1.getId())))
                .andExpect(jsonPath("$.name", is(USER_1.getName())))
                .andExpect(jsonPath("$.lastName", is(USER_1.getLastName())))
                .andExpect(jsonPath("$.email", is(USER_1.getEmail())))
                .andExpect(jsonPath("$.roles.length()").value(USER_1.getRoles().size()))
                .andExpect(jsonPath("$.roles", Matchers.containsInAnyOrder("ROLE_USER")));
    }

    @Test
    public void testGetUnAuth() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isUnauthorized());
    }
    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL)
                .with(userAuth(USER_1)))
                .andExpect(status().isNoContent());

        assertMatch(userService.getAll(), USER_2);
    }

    @Test
    public void testUpdate() throws Exception {
        UserTo updatedTo = new UserTo(null, "newName", "newLastName", "newemail@ya.ru",
                "newPassword", LocalDate.of(1995, 12, 12).atStartOfDay());

        mockMvc.perform(put(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .content(JacksonObjectMapper.writeValue(updatedTo))
                .with(userAuth(USER_1)))
                .andExpect(status().isOk());

        User user = UserUtil.createNewFromTo(updatedTo);
        user.setId(USER_1_ID);

        assertMatch(userService.getAll(), user, USER_2);
    }

    @Test
    public void testUpdateInvalid() throws Exception {
        UserTo updatedTo = new UserTo(null, null, null, null, "password",
                LocalDate.of(1995, 12, 12).atStartOfDay());

        mockMvc.perform(put(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .content(JacksonObjectMapper.writeValue(updatedTo))
                .with(userAuth(USER_1)))
                .andExpect(status().is4xxClientError());
    }
}