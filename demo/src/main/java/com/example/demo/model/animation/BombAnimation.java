package com.example.demo.model.animation;

import com.example.demo.WelcomePage;
import com.example.demo.controller.GameController;
import com.example.demo.controller.UserController;
import com.example.demo.model.Bomb;
import com.example.demo.model.Boss;
import com.example.demo.model.MiniBoss;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class BombAnimation extends Transition {

    private Bomb bomb;
    private Boss boss;

    public BombAnimation(Bomb bomb, Boss boss) {
        this.bomb = bomb;
        this.boss = boss;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
    }
    @Override
    protected void interpolate(double v) {

        for (MiniBoss miniBoss : GameController.getInstance().getMiniBosses()) {
            if (bomb.hasCollisionWithMiniBoss(miniBoss)) {

                GameController.getInstance().removeMiniBoss(miniBoss);
                Pane pane = (Pane) WelcomePage.scene.getRoot();
                pane.getChildren().remove(bomb);
                this.stop();
                pane.getChildren().remove(miniBoss);
                GameController.getInstance().removeMiniBoss(miniBoss);
                UserController.getInstance().getCurrentUser().setScore(UserController.getInstance().getCurrentUser().getScore() + 5);
                return;
            }
        }

        if (bomb.hasCollisionWithDown()) {
            Pane pane = (Pane) WelcomePage.scene.getRoot();
            pane.getChildren().remove(bomb);
            this.stop();
        }
        else if (bomb.hasCollisionWithBoss(boss)) {
            boss.setHealthPoint(boss.getHealthPoint() - (10 * UserController.getInstance().getCurrentUser().getAttackPoint()));
            Pane pane = (Pane) WelcomePage.scene.getRoot();
            pane.getChildren().remove(bomb);
            this.stop();
            UserController.getInstance().getCurrentUser().setScore(UserController.getInstance().getCurrentUser().getScore() + 10);
            if (boss.getHealthPoint() <= 0) {
                //boss is dead so end the game
                GameController.getInstance().endGame(true);
            }
        }
        else {
            bomb.moveDown();
        }
    }
}
