package com.itheima.Pjf.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private String _id;
    private String movieName;

    private String movieId;
    private String commentUser;
    private String rating;
    private String attitude;
    private String ip;
    private String photoUrl;
    private String time;
    private String ifUse;
    private String commentContent;
    private String ifSee;

    @Override
    public String toString() {
        return "Comment{" +
                "_id='" + _id + '\'' +
                ", movieName='" + movieName + '\'' +
                ", movieId='" + movieId + '\'' +
                ", commentUser='" + commentUser + '\'' +
                ", rating='" + rating + '\'' +
                ", attitude='" + attitude + '\'' +
                ", ip='" + ip + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", time='" + time + '\'' +
                ", ifUse='" + ifUse + '\'' +
                ", commentContent='" + commentContent + '\'' +
                ", ifSee='" + ifSee + '\'' +
                '}';
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getCommentUser() {
        return commentUser;
    }

    public void setCommentUser(String commentUser) {
        this.commentUser = commentUser;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getAttitude() {
        return attitude;
    }

    public void setAttitude(String attitude) {
        this.attitude = attitude;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIfUse() {
        return ifUse;
    }

    public void setIfUse(String ifUse) {
        this.ifUse = ifUse;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getIfSee() {
        return ifSee;
    }

    public void setIfSee(String ifSee) {
        this.ifSee = ifSee;
    }
}
