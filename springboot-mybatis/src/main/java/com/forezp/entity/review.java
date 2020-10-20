package com.forezp.entity;

public class review {
    private InternalError review_id;
    private String article_time;
    private String article_reviews;
    private Integer user_id;
    private Integer article_id;
    private String user_name;

    public String getArticle_reviews() {
        return article_reviews;
    }

    public void setArticle_reviews(String article_reviews) {
        this.article_reviews = article_reviews;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public InternalError getReview_id() {
        return review_id;
    }

    public void setReview_id(InternalError review_id) {
        this.review_id = review_id;
    }

    public String getArticle_time() {
        return article_time;
    }

    public void setArticle_time(String article_time) {
        this.article_time = article_time;
    }

    @Override
    public String toString() {
        return "review{" +
                "review_id=" + review_id +
                ", article_time='" + article_time + '\'' +
                ", article_reviews='" + article_reviews + '\'' +
                ", user_id=" + user_id +
                ", article_id=" + article_id +
                ", user_name='" + user_name + '\'' +
                '}';
    }
}
