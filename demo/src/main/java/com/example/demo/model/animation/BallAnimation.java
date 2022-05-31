package com.example.demo.model.animation;

import com.example.demo.WelcomePage;
import com.example.demo.controller.GameController;
import com.example.demo.model.Ball;
import com.example.demo.model.Boss;
import com.example.demo.model.Plane;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class BallAnimation extends Transition {

    Ball ball;
    Plane plane;

    public BallAnimation(Ball ball, Plane plane) {
        this.ball = ball;
        this.plane = plane;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        if (ball.hasCollisionWithLeft()) {
            Pane pane = (Pane) WelcomePage.scene.getRoot();
            pane.getChildren().remove(ball);
            this.stop();
            Boss.getInstance().setShooting(false);
        }
        else if (ball.hasCollisionWithPlane(plane)) {
            // decrease the health point of plane
            Plane.getInstance().setLivesNumber(Plane.getInstance().getLivesNumber() - 1);
            Pane pane = (Pane) WelcomePage.scene.getRoot();
            pane.getChildren().remove(ball);
            this.stop();
            Boss.getInstance().setShooting(false);
            if (Plane.getInstance().getLivesNumber() <= 0) {
                //dead so end the game
                GameController.getInstance().endGame(false);
            }
        }
        else {
            ball.moveLeft();
        }
    }
}
