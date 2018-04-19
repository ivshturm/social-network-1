package ru.sberbank.project.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import ru.sberbank.project.util.ParseDeserializer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment extends AbstractBaseEntity {

    @Column(name = "user_id")
    private int userId;

    @Column(name = "article_id")
    private int articleId;

    @Column(name = "text")
    private String text;

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = ParseDeserializer.class)
    @Column(name = "date_time", updatable = false)
    private LocalDateTime dateTime;

    public Comment() {
    }

    public Comment(int userId, int articleId, String text) {
        this.userId = userId;
        this.articleId = articleId;
        this.text = text;
    }

    public Comment(int id, int userId, int articleId, String text) {
        super(id);
        this.userId = userId;
        this.articleId = articleId;
        this.text = text;
    }

    public Comment(int userId, int articleId, String text, LocalDateTime dateTime) {
        this.userId = userId;
        this.articleId = articleId;
        this.text = text;
        this.dateTime = dateTime;
    }

    public Comment(CommentTo commentTo) {
        super(commentTo.getId());
        this.userId = commentTo.getUserId();
        this.articleId = commentTo.getArticleId();
        this.text = commentTo.getText();
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "userId=" + userId +
                ", articleId=" + articleId +
                ", text='" + text + '\'' +
                ", dateTime=" + dateTime +
                ", id=" + id +
                '}';
    }
}
