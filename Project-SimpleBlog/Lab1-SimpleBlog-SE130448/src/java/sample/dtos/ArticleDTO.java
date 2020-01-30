/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dtos;

/**
 *
 * @author sonho
 */
public class ArticleDTO {

    String articleID;
    String title;
    String shortDescription;
    String img;
    String postContent;
    String author;
    String avatarUserPost;
    String postingDate;
    String email;
    int statusArticle;

    public ArticleDTO(String articleID, String title, String shortDescription, String img, String postContent, String author, String avatarUserPost, String postingDate, String email, int statusArticle) {
        this.articleID = articleID;
        this.title = title;
        this.shortDescription = shortDescription;
        this.img = img;
        this.postContent = postContent;
        this.author = author;
        this.avatarUserPost = avatarUserPost;
        this.postingDate = postingDate;
        this.email = email;
        this.statusArticle = statusArticle;
    }

    public ArticleDTO(String articleID, String title, String shortDescription, String img, String postContent, String author, String avatarUserPost, String postingDate, String email) {
        this.articleID = articleID;
        this.title = title;
        this.shortDescription = shortDescription;
        this.img = img;
        this.postContent = postContent;
        this.author = author;
        this.avatarUserPost = avatarUserPost;
        this.postingDate = postingDate;
        this.email = email;
    }

    public ArticleDTO(String articleID, String title, String shortDescription, String img, String author, String postingDate, String email, int statusArticle) {
        this.articleID = articleID;
        this.title = title;
        this.shortDescription = shortDescription;
        this.img = img;
        this.author = author;
        this.postingDate = postingDate;
        this.email = email;
        this.statusArticle = statusArticle;
    }

    public ArticleDTO(String articleID, String title, String shortDescription, String img, String postContent, String author, String postingDate, String email, int statusArticle) {
        this.articleID = articleID;
        this.title = title;
        this.shortDescription = shortDescription;
        this.img = img;
        this.postContent = postContent;
        this.author = author;
        this.postingDate = postingDate;
        this.email = email;
        this.statusArticle = statusArticle;
    }

    public String getAvatarUserPost() {
        return avatarUserPost;
    }

    public void setAvatarUserPost(String avatar) {
        this.avatarUserPost = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getArticleID() {
        return articleID;
    }

    public void setArticleID(String articleID) {
        this.articleID = articleID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(String postingDate) {
        this.postingDate = postingDate;
    }

    public int getStatusArticle() {
        return statusArticle;
    }

    public void setStatusArticle(int statusArticle) {
        this.statusArticle = statusArticle;
    }

}
