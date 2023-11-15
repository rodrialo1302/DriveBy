/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Rodri
 */
public class User {
    private String username;
    private String mail;
    private String password;
    private String name;
    private String car;
    private String bio;
    private String media;
    private int points;
    private int frame;
    
    
    
    
    public User(String username, String mail, String password, String name, String car, String bio, String media,int frame, int points){
        this.username= username;
        this.mail = mail;
        this.password = password;
        this.name = name;
        this.car = car;
        this.bio = bio;
        this.media = media;
        this.frame = frame;
        this.points = points;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    
    public int getFrame(){
        return frame;
    }
    
    public void setFrame(int frame){
        this.frame = frame;
    }

    
    
    
}
