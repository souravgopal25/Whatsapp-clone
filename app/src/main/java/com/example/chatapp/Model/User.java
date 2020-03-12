package com.example.chatapp.Model;

public class User {
    private String uid;
    private String username;
    private String imageURL;
    private String status;

    public User() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User(String id, String username, String imageURL, String status) {
        this.uid = id;
        this.username = username;
        this.status=status;
        this.imageURL = imageURL;
    }

    public String getId() {
        return uid;
    }

    public User(String uid, String username, String imageURL) {
        this.uid = uid;
        this.username = username;
        this.imageURL = imageURL;
    }

    public void setId(String id) {
        this.uid = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
