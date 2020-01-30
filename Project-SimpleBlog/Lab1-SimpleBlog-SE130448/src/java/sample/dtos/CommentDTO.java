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
public class CommentDTO {

    String commentID;
    String content;
    String email;
    String dateComment;
    String articleID;
    String author;
    String avatarUserComment;
   
    

    public CommentDTO(String content, String email, String dateComment, String author) {
        this.content = content;
        this.email = email;
        this.dateComment = dateComment;
        this.author = author;
    }

    public CommentDTO(String commentID, String content, String email, String dateComment, String author) {
        this.commentID = commentID;
        this.content = content;
        this.email = email;
        this.dateComment = dateComment;
        this.author = author;
    }

    public CommentDTO(String commentID, String content, String email, String dateComment, String articleID, String author) {
        this.commentID = commentID;
        this.content = content;
        this.email = email;
        this.dateComment = dateComment;
        this.articleID = articleID;
        this.author = author;
    }

    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateComment() {
        return dateComment;
    }

    public void setDateComment(String dateComment) {
        this.dateComment = dateComment;
    }

    public String getArticleID() {
        return articleID;
    }

    public void setArticleID(String articleID) {
        this.articleID = articleID;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAvatarUserComment() {
        return avatarUserComment;
    }

    public void setAvatarUserComment(String avatarUserComment) {
        this.avatarUserComment = avatarUserComment;
    }

}
