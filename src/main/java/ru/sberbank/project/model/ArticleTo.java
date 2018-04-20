package ru.sberbank.project.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import ru.sberbank.project.util.ParseDeserializer;

import java.time.LocalDateTime;

public class ArticleTo {

    private int id;

    private int userId;

    private String name;

    private String text;

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = ParseDeserializer.class)
    private LocalDateTime dateTime;

    private String userFullName;

    public ArticleTo() {}

    public ArticleTo(Article article) {
        this.id = article.getId();
        this.userId = article.getUserId();
        this.name = article.getName();
        this.text = article.getText();
        this.dateTime = article.getDateTime();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    @Override
    public String toString() {
        return "ArticleTo{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", dateTime=" + dateTime +
                ", userFullName='" + userFullName + '\'' +
                '}';
    }
}

