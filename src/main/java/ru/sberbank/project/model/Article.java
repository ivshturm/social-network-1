package ru.sberbank.project.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import ru.sberbank.project.util.*;

@Entity
@Table(name = "articles")
public class Article extends AbstractBaseEntity {

    @Column(name = "user_id")
    private int userId;

    @Size(min = 2, max = 100)
    @Column(name = "name", nullable = false)
    private String name;

    @Size(min = 2, max = 2000)
    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "date_time", updatable = false)
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = ParseDeserializer.class)
    private LocalDateTime dateTime;

    public Article() {}

    public Article(String name, String text) {
        this.name = name;
        this.text = text;
        this.userId = userId;
    }

    public Article(Integer id, String name, String text) {
        super(id);
        this.name = name;
        this.text = text;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public int getUserId() {
        return userId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Article{" +
                "name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", userId=" + userId +
                ", dateTime=" + dateTime +
                '}';
    }
}
