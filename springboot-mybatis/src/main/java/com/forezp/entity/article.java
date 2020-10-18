package com.forezp.entity;

public class article {
    private Integer article_id;
    private String artcle_title;
    private String artcle_cover_img;
    private String article_text_img;
    private String article_text;
    private Integer article_comments_like;
    private Integer article_comments;
    private Integer user_id;
    private String article_upload;
    private Integer article_type;

    public Integer getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }

    public String getArtcle_title() {
        return artcle_title;
    }

    public void setArtcle_title(String artcle_title) {
        this.artcle_title = artcle_title;
    }

    public String getArtcle_cover_img() {
        return artcle_cover_img;
    }

    public void setArtcle_cover_img(String artcle_cover_img) {
        this.artcle_cover_img = artcle_cover_img;
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

    public Integer getArticle_comments() {
        return article_comments;
    }

    public void setArticle_comments(Integer article_comments) {
        this.article_comments = article_comments;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getArticle_upload() {
        return article_upload;
    }

    public void setArticle_upload(String article_upload) {
        this.article_upload = article_upload;
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
                ", artcle_title='" + artcle_title + '\'' +
                ", artcle_cover_img='" + artcle_cover_img + '\'' +
                ", article_text_img='" + article_text_img + '\'' +
                ", article_text='" + article_text + '\'' +
                ", article_comments_like=" + article_comments_like +
                ", article_comments=" + article_comments +
                ", user_id=" + user_id +
                ", article_upload='" + article_upload + '\'' +
                ", article_type=" + article_type +
                '}';
    }
}
