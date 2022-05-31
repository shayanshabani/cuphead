package com.example.demo.model.animation;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class BackGroundAnimation extends Transition {
    private ImageView imageView;

    public BackGroundAnimation(ImageView imageView) {
        this.imageView = imageView;
        imageView.setY(300);
        this.setCycleDuration(Duration.millis(50));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        imageView.setX(imageView.getX() - 0.5);
        if (imageView.getX() <= -1280) {
            imageView.setX(0);
        }
    }
}
