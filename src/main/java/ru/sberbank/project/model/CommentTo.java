package ru.sberbank.project.model;


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
        this.id = comment.getId();
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
