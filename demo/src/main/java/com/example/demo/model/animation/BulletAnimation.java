package com.example.demo.model.animation;

import com.example.demo.WelcomePage;
import com.example.demo.controller.GameController;
import com.example.demo.controller.UserController;
import com.example.demo.model.Boss;
import com.example.demo.model.Bullet;
import com.example.demo.model.MiniBoss;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class BulletAnimation extends Transition {

    private Bullet bullet;
    private Boss boss;

    public BulletAnimation(Bullet bullet, Boss boss) {
        this.bullet = bullet;
        this.boss = boss;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {

        for (MiniBoss miniBoss : GameController.getInstance().getMiniBosses()) {
            if (bullet.hasCollisionWithMiniBoss(miniBoss)) {
                miniBoss.setHealth(miniBoss.getHealth() - 1);
                Pane pane = (Pane) WelcomePage.scene.getRoot();
                pane.getChildren().remove(bullet);
                this.stop();
                if (miniBoss.getHealth() == 0) {
                    pane.getChildren().remove(miniBoss);
                    GameController.getInstance().removeMiniBoss(miniBoss);
                }
                UserController.getInstance().getCurrentUser().setScore(UserController.getInstance().getCurrentUser().getScore() + 2);
                return;
            }
        }

        if (bullet.hasCollisionWithRight()) {
            Pane pane = (Pane) WelcomePage.scene.getRoot();
            pane.getChildren().remove(bullet);
            this.stop();
        }
        else if (bullet.hasCollisionWithBoss(boss)) {
            boss.setHealthPoint(boss.getHealthPoint() - (5 * UserController.getInstance().getCurrentUser().getAttackPoint()));
            Pane pane = (Pane) WelcomePage.scene.getRoot();
            pane.getChildren().remove(bullet);
            this.stop();
            UserController.getInstance().getCurrentUser().setScore(UserController.getInstance().getCurrentUser().getScore() + 5);
            if (boss.getHealthPoint() <= 0) {
                //dead so end the game and win
                GameController.getInstance().endGame(true);
            }
        }
        else {
            bullet.moveRight();
        }
    }
}
