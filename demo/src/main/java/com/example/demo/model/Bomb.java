package com.example.demo.model;

import com.example.demo.WelcomePage;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Bomb extends Rectangle {
    public Bomb(Plane plane) {
        super(plane.getX() + plane.getWidth(), plane.getY() + plane.getHeight(), 55, 45);
        Image image = new Image(String.valueOf(WelcomePage.class.getResource("image/images/bomb.png")));
        this.setFill(new ImagePattern(image));
    }

    public void moveDown() {
        this.setY(this.getY() + 13);
    }

    public boolean hasCollisionWithDown() {
        return this.getY() + this.getHeight() >= 720;
    }

    public boolean hasCollisionWithBoss(Boss boss) {
        boolean width = this.getX() >= boss.getX() && this.getX() <= boss.getX() + boss.getWidth();
        boolean height = this.getY() - boss.getY() <= 0 && this.getY() - boss.getY() >= -15;
        return width && height;
    }

    public boolean hasCollisionWithMiniBoss(MiniBoss miniBoss) {
        return miniBoss.getBoundsInParent().intersects(this.getLayoutBounds());
    }
}
