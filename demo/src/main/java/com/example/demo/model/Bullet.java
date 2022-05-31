package com.example.demo.model;

import com.example.demo.WelcomePage;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Bullet extends Rectangle {
    public Bullet(Plane plane) {
        super(plane.getX() + plane.getWidth(), plane.getY() + plane.getHeight() / 2, 35, 15);
        Image image = new Image(String.valueOf(WelcomePage.class.getResource("image/images/bullet.png")));
        this.setFill(new ImagePattern(image));
    }
    public void moveRight() {
        this.setX(this.getX() + 13);
    }

    public boolean hasCollisionWithRight() {
        return this.getX() >= 1220;
    }


    public boolean hasCollisionWithBoss(Boss boss) {
        boolean width = this.getX() >= boss.getX() && this.getX() <= boss.getX() + boss.getWidth();
        boolean height = this.getY() >= boss.getY() && this.getY() <= boss.getY() + boss.getHeight();
        return width && height;
    }

    public boolean hasCollisionWithMiniBoss(MiniBoss miniBoss) {
        return miniBoss.getBoundsInParent().intersects(this.getLayoutBounds());
    }
}
