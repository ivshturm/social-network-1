package ru.sberbank.project.controller.article;

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
import ru.sberbank.project.model.Article;
import ru.sberbank.project.service.ArticleService;
import ru.sberbank.project.util.JacksonObjectMapper;

import javax.annotation.PostConstruct;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.sberbank.project.ArticleTestData.*;
import static ru.sberbank.project.ArticleTestData.assertMatch;
import static ru.sberbank.project.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-mvc.xml",
        "classpath:spring/spring-db.xml"
})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/testDbPopulate.sql", config = @SqlConfig(encoding = "UTF-8"))
public class ArticleRestControllerTest {

    private static final CharacterEncodingFilter CHARACTER_ENCODING_FILTER = new CharacterEncodingFilter();
    private static final String REST_URL = ArticleRestController.REST_URL + "/";

    static {
        CHARACTER_ENCODING_FILTER.setEncoding("UTF-8");
        CHARACTER_ENCODING_FILTER.setForceEncoding(true);
    }

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ArticleService articleService;

    @PostConstruct
    private void postConstruct() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilter(CHARACTER_ENCODING_FILTER)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void getArticle() throws Exception {
        mockMvc.perform(get(REST_URL + ARTICLE_1_ID)
                .with(userAuth(USER_1)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(ARTICLE_1_ID)))
                .andExpect(jsonPath("$.name", is(ARTICLE_1.getName())))
                .andExpect(jsonPath("$.text", is(ARTICLE_1.getText())))
                .andExpect(jsonPath("$.userId", is(ARTICLE_1.getUserId())));
    }

    @Test
    public void deleteArticle() throws Exception {
        mockMvc.perform(delete(REST_URL + ARTICLE_1_ID)
                .with(userAuth(USER_1)))
                .andExpect(status().isNoContent());

        assertMatch(articleService.getAll(USER_1_ID), ARTICLE_2);
    }

    @Test
    public void getAll() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userAuth(USER_1)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(ARTICLE_1_ID)))
                .andExpect(jsonPath("$[0].name", is(ARTICLE_1.getName())))
                .andExpect(jsonPath("$[0].text", is(ARTICLE_1.getText())))
                .andExpect(jsonPath("$[0].userId", is(ARTICLE_1.getUserId())))
                .andExpect(jsonPath("$[1].id", is(ARTICLE_2_ID)))
                .andExpect(jsonPath("$[1].name", is(ARTICLE_2.getName())))
                .andExpect(jsonPath("$[1].text", is(ARTICLE_2.getText())))
                .andExpect(jsonPath("$[1].userId", is(ARTICLE_2.getUserId())));
    }

    @Test
    public void updateSuccess() throws Exception {
        Article updatedArticle = new Article(ARTICLE_1_ID, "updated name", "updated text", USER_1_ID);

        mockMvc.perform(put(REST_URL + ARTICLE_1_ID).contentType(MediaType.APPLICATION_JSON)
                .content(JacksonObjectMapper.writeValue(updatedArticle))
                .with(userAuth(USER_1)))
                .andExpect(status().isOk());

        assertMatch(articleService.getAll(USER_1_ID), updatedArticle, ARTICLE_2);
    }
}