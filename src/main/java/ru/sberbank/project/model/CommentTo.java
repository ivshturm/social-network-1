package ru.sberbank.project.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import ru.sberbank.project.util.ParseDeserializer;

import java.time.LocalDateTime;

public class CommentTo {

    private int id;

    private int userId;

    private int articleId;

    private String text;

    private String dateTime;

    private UserTo userTo;

    public CommentTo() {
    }

    public CommentTo(Comment comment) {
        this.userId = comment.getUserId();
        this.articleId = comment.getArticleId();
        this.text = comment.getText();
        this.dateTime = comment.getDateTime().toString().replace('T', ' ');
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserTo getUserTo() {
        return userTo;
    }

    public void setUserTo(UserTo userTo) {
        this.userTo = userTo;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDateTime() {
        return dateTime;
    }
}
