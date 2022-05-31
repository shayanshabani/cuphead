package com.example.demo.model.animation;

import com.example.demo.WelcomePage;
import com.example.demo.controller.GameController;
import com.example.demo.model.MiniBoss;
import com.example.demo.model.Plane;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class MiniBossAnimation extends Transition {

    private MiniBoss miniBoss;

    public MiniBossAnimation(MiniBoss miniBoss) {
        this.miniBoss = miniBoss;
        this.setCycleDuration(Duration.millis(500));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        int index = (int) (v * 3);
        //check movement and collision
        if (miniBoss.hasCollisionWithLeft()) {
            Pane pane = (Pane) WelcomePage.scene.getRoot();
            pane.getChildren().remove(miniBoss);
            this.stop();
        }
        else if (miniBoss.hasCollisionWithPlane(Plane.getInstance())) {
            // decrease the health point of plane
            Plane.getInstance().setLivesNumber(Plane.getInstance().getLivesNumber() - 1);
            Pane pane = (Pane) WelcomePage.scene.getRoot();
            pane.getChildren().remove(miniBoss);
            this.stop();
            if (Plane.getInstance().getLivesNumber() <= 0) {
                //dead plane so end the game
                GameController.getInstance().endGame(false);
            }
        }
        else {
            miniBoss.moveLeft();
        }
        //next picture
        //yellow
        if (miniBoss.isYellow()) {
            miniBoss.setImagePattern(miniBoss.getYellowMiniBosses().get(index));
        }
        //purple
        else {
            miniBoss.setImagePattern(miniBoss.getPurpleMiniBosses().get(index));
        }
    }
}
