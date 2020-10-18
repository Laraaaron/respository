package com.forezp.entity;

public class article {
    private Integer article_id;
    private String article_title;
    private String article_cover_img;
    private String article_text_img;
    private String article_text;
    private Integer article_comments_like;
    private Integer article_comments_nums;
    private Integer user_id;
    private String article_upload_time;
    private Integer article_type;

    public Integer getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }

    public String getArticle_title() {
        return article_title;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public String getArticle_cover_img() {
        return article_cover_img;
    }

    public void setArticle_cover_img(String article_cover_img) {
        this.article_cover_img = article_cover_img;
    }

    public String getArticle_text_img() {
        return article_text_img;
    }

    public void setArticle_text_img(String article_text_img) {
        this.article_text_img = article_text_img;
    }

    public String getArticle_text() {
        return article_text;
    }

    public void setArticle_text(String article_text) {
        this.article_text = article_text;
    }

    public Integer getArticle_comments_like() {
        return article_comments_like;
    }

    public void setArticle_comments_like(Integer article_comments_like) {
        this.article_comments_like = article_comments_like;
    }

    public Integer getArticle_comments_nums() {
        return article_comments_nums;
    }

    public void setArticle_comments_nums(Integer article_comments_nums) {
        this.article_comments_nums = article_comments_nums;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getArticle_upload_time() {
        return article_upload_time;
    }

    public void setArticle_upload_time(String article_upload_time) {
        this.article_upload_time = article_upload_time;
    }

    public Integer getArticle_type() {
        return article_type;
    }

    public void setArticle_type(Integer article_type) {
        this.article_type = article_type;
    }

    @Override
    public String toString() {
        return "article{" +
                "article_id=" + article_id +
                ", article_title='" + article_title + '\'' +
                ", article_cover_img='" + article_cover_img + '\'' +
                ", article_text_img='" + article_text_img + '\'' +
                ", article_text='" + article_text + '\'' +
                ", article_comments_like=" + article_comments_like +
                ", article_comments_nums=" + article_comments_nums +
                ", user_id=" + user_id +
                ", article_upload_time='" + article_upload_time + '\'' +
                ", article_type=" + article_type +
                '}';
    }
}
