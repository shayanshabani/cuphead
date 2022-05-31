package com.example.demo.model;

import com.example.demo.WelcomePage;
import com.example.demo.controller.UserController;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Plane extends Rectangle {
    private static Plane plane;
    private Image image;
    private boolean bomb;
    private int livesNumber;
    private Plane() {
        super(200, 300, 70, 70);
        image = new Image(String.valueOf(WelcomePage.class.getResource("image/images/red.png")));
        this.setFill(new ImagePattern(image));
        bomb = false;
        livesNumber = 5;
    }

    public static Plane getInstance() {
        if (plane == null)
            plane = new Plane();
        return plane;
    }

    public void moveUp() {
        if (!hasCollisionWithUp())
            this.setY(this.getY() - 10);
    }

    public void moveRight() {
        if (!hasCollisionWithRight())
            this.setX(this.getX() + 10);
    }

    public void moveDown() {
        if (!hasCollisionWithDown())
            this.setY(this.getY() + 10);
    }

    public void moveLeft() {
        if (!hasCollisionWithLeft())
            this.setX(this.getX() - 10);
    }

    public boolean hasCollisionWithUp() {
        return this.getY() <= 0;
    }

    public boolean hasCollisionWithRight() {
        return this.getX() + this.getWidth() >= 1280;
    }

    public boolean hasCollisionWithDown() {
        return this.getY() + this.getHeight() >= 720;
    }

    public boolean hasCollisionWithLeft() {
        return this.getX() <= 0;
    }

    public boolean isBomb() {
        return bomb;
    }

    public void setBomb(boolean bomb) {
        this.bomb = bomb;
    }

    public int getLivesNumber() {
        return livesNumber;
    }

    public void setLivesNumber(int livesNumber) {
        this.livesNumber = livesNumber;
    }
}
