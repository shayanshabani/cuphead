package com.example.demo.model;

import com.example.demo.WelcomePage;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Ball extends Circle {

    public Ball(Boss boss) {
        super(boss.getX() - 40, boss.getY() + boss.getHeight() / 2, 40);
        Image image = new Image(String.valueOf(WelcomePage.class.getResource("image/images/egg.png")));
        this.setFill(new ImagePattern(image));
    }

    public void moveLeft() {
        this.setCenterX(this.getCenterX() - 7);
    }

    public boolean hasCollisionWithPlane(Plane plane) {
        return plane.getBoundsInParent().intersects(this.getLayoutBounds());
    }

    public boolean hasCollisionWithLeft() {
        return this.getCenterX() - this.getRadius() <= 0;
    }
}
