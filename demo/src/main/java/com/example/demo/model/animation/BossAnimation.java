package com.example.demo.model.animation;

import com.example.demo.controller.GameController;
import com.example.demo.model.Boss;
import com.example.demo.model.Plane;
import javafx.animation.Transition;
import javafx.util.Duration;

public class BossAnimation extends Transition {
    private int indexForBallToBeShot = 1;
    public BossAnimation() {
        this.setCycleDuration(Duration.millis(700));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        int index = (int)(v * 5);
        if (GameController.getInstance().conditionOfShootingTheBall() && !Boss.getInstance().isShooting()) {
            GameController.getInstance().buildBall();
            Boss.getInstance().setShooting(true);
        }
        if (indexForBallToBeShot % 300 == 150) {
            //yellow
            if (indexForBallToBeShot % 600 == 150) {
                GameController.getInstance().buildMiniBoss(true);
            }
            //purple
            else if (indexForBallToBeShot % 600 == 450) {
                GameController.getInstance().buildMiniBoss(false);
            }
        }
        else if (indexForBallToBeShot % 300 == 155) {
            //yellow
            if (indexForBallToBeShot % 600 == 155) {
                GameController.getInstance().buildMiniBoss(false);
            }
            //purple
            else if (indexForBallToBeShot % 600 == 455) {
                GameController.getInstance().buildMiniBoss(true);
            }
        }
        else if (indexForBallToBeShot % 300 == 160) {
            //yellow
            if (indexForBallToBeShot % 600 == 160) {
                GameController.getInstance().buildMiniBoss(true);
            }
            //purple
            else if (indexForBallToBeShot % 600 == 460) {
                GameController.getInstance().buildMiniBoss(false);
            }
        }
        indexForBallToBeShot++;
        Boss.getInstance().setImagePattern(Boss.getInstance().getBossFly().get(index));
        if (Boss.getInstance().hasCollisionWithPlane(Plane.getInstance())) {
            //decrease the health point of plane
            Plane.getInstance().setLivesNumber(Plane.getInstance().getLivesNumber() - 1);
            if (Plane.getInstance().getLivesNumber() <= 0) {
                // dead so end the game
                this.stop();
                GameController.getInstance().endGame(false);
            }
        }
    }
}
