package ru.sberbank.project.data;

import ru.sberbank.project.model.Article;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ArticleTestData {

    public static final int ARTICLE_1_ID = UserTestData.USER_2_ID + 1;
    public static final int ARTICLE_2_ID = ARTICLE_1_ID + 1;

    public static final Article ARTICLE_1 = new Article(ARTICLE_1_ID, "First article",
            "It is the best article!", UserTestData.USER_1_ID);
    public static final Article ARTICLE_2 = new Article(ARTICLE_2_ID, "Second article",
            "Very bad article", UserTestData.USER_1_ID);

    public static void assertMatch(Article actual, Article expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "dateTime");
    }

    public static void assertMatch(Iterable<Article> actual, Article... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Article> actual, Iterable<Article> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("dateTime").isEqualTo(expected);
    }
}
