package com.example.demo.model;

import com.example.demo.controller.GameController;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class MiniBoss extends Rectangle {
    private final ArrayList<Image> yellowMiniBosses;
    private final ArrayList<Image> purpleMiniBosses;
    private int health;
    private final boolean yellow;

    public MiniBoss(int x, int y, boolean yellow) {
        super(x, y, 70, 70);
        yellowMiniBosses = GameController.getInstance().buildYellowMiniBosses();
        purpleMiniBosses = GameController.getInstance().buildPurpleMiniBosses();
        this.yellow = yellow;
        health = 2;
        //first picture
        if (yellow)
            setImagePattern(yellowMiniBosses.get(0));
        else
            setImagePattern(purpleMiniBosses.get(0));
    }

    public ArrayList<Image> getPurpleMiniBosses() {
        return purpleMiniBosses;
    }

    public ArrayList<Image> getYellowMiniBosses() {
        return yellowMiniBosses;
    }

    public void setImagePattern(Image image) {
        this.setFill(new ImagePattern(image));
    }

    public boolean isYellow() {
        return yellow;
    }

    public void moveLeft() {
        this.setX(this.getX() - 5);
    }

    public boolean hasCollisionWithLeft() {
        return this.getX() <= 0;
    }

    public boolean hasCollisionWithPlane(Plane plane) {
        boolean width = this.getX() - (plane.getX() + plane.getWidth()) >= 0 && this.getX() - (plane.getX() + plane.getWidth()) <= 6;
        boolean height = this.getY() - plane.getY() >= 0 && this.getY() - plane.getY() <= 6;
        boolean height2 = plane.getY() - this.getY() >= 0 && plane.getY() - this.getY() <= 6;
        return width &&  (height || height2);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
