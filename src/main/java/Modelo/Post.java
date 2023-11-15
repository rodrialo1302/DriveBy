package Modelo;

import java.time.LocalDateTime;

/**
 *
 * @author Rodri
 */
public class Post {
    private int postID;
    private String username;
    private String location;
    private String desc;
    private String alertType;
    private LocalDateTime date;
    private String media;

    public Post(int postID, String username, String location, String desc, String alertType, LocalDateTime date, String media) {
        this.postID = postID;
        this.username = username;
        this.location = location;
        this.desc = desc;
        this.alertType = alertType;
        this.date = date;
        this.media = media;
    }

    
    
    
    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAlertType() {
        return alertType;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    
    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    
    
}
