package ru.sberbank.project;

import ru.sberbank.project.model.Comment;
import ru.sberbank.project.model.CommentTo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class CommentTestData {

    public static final int COMMENT_1_ID = ArticleTestData.ARTICLE_2_ID + 1;
    public static final int COMMENT_2_ID = COMMENT_1_ID + 1;

    public static final Comment COMMENT_1 = new Comment(COMMENT_1_ID, UserTestData.USER_2_ID, ArticleTestData.ARTICLE_1_ID, "Good article");
    public static final Comment COMMENT_2 = new Comment(COMMENT_2_ID, UserTestData.USER_1_ID, ArticleTestData.ARTICLE_1_ID, "Like");

    public static void assertMatch(Comment actual, Comment expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "dateTime");
    }

    public static void assertMatch(Iterable<Comment> actual, Comment... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Comment> actual, Iterable<Comment> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("dateTime").isEqualTo(expected);
    }

    public static void assertMatch(List<CommentTo> actual, Comment... expected) {
        assertMatch(actual.stream().map(Comment::new).collect(Collectors.toList()),
                    Arrays.asList(expected));
    }
}
