package com.example.demo.model;

import com.example.demo.WelcomePage;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

import static java.util.Objects.*;

public class User {
    private String username;
    private String password;
    private int score;
    private double attackPoint;
    private double damagePoint;
    private int healthPoint;
    private String mode;
    private String profilePicture;
    public User(String username, String password, String profilePicture) {
        this.username = username;
        this.password = password;
        score = 0;
        attackPoint = 1;
        damagePoint = 1;
        healthPoint = 5;
        mode = "Medium";
        this.profilePicture = profilePicture;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getAttackPoint() {
        return attackPoint;
    }

    public double getDamagePoint() {
        return damagePoint;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public void setAttackPoint(double attackPoint) {
        this.attackPoint = attackPoint;
    }

    public void setDamagePoint(double damagePoint) {
        this.damagePoint = damagePoint;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

}
