package com.example.demo.controller;

import com.example.demo.WelcomePage;
import com.example.demo.model.*;
import com.example.demo.model.animation.*;
import com.example.demo.view.EndGameView;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class GameController {
    private static GameController gameController;

    private BallAnimation ballAnimation;
    private BombAnimation bombAnimation;
    private BossAnimation bossAnimation;
    private BulletAnimation bulletAnimation;
    private MiniBossAnimation miniBossAnimation;
    private MediaPlayer mediaPlayer;
    private ArrayList<MiniBoss> miniBosses;

    private GameController() {
        miniBosses = new ArrayList<>();
    }

    public static GameController getInstance() {
        if (gameController == null)
            gameController = new GameController();
        return gameController;
    }

    public ArrayList<Image> buildBossFly() {
        ArrayList<Image> bossFly = new ArrayList<>();
        Image image;
        for (int i = 1; i < 7; i++) {
            image = new Image(String.valueOf(WelcomePage.class.getResource("image/BossFly/" + i + ".png")));
            bossFly.add(image);
        }
        return bossFly;
    }
    public ArrayList<Image> buildBossShoot() {
        ArrayList<Image> bossShoot = new ArrayList<>();
        Image image;
        for (int i = 1; i < 13; i++) {
            image = new Image(String.valueOf(WelcomePage.class.getResource("image/BossShoot/" + i + ".png")));
            bossShoot.add(image);
        }
        return bossShoot;
    }

    public void makeEasy() {
        UserController.getInstance().getCurrentUser().setAttackPoint(1.5);
        UserController.getInstance().getCurrentUser().setDamagePoint(0.5);
        UserController.getInstance().getCurrentUser().setHealthPoint(10);
        Plane.getInstance().setLivesNumber(10);
        UserController.getInstance().getCurrentUser().setMode("Easy");
    }
    public void makeMedium() {
        UserController.getInstance().getCurrentUser().setAttackPoint(1);
        UserController.getInstance().getCurrentUser().setDamagePoint(1);
        UserController.getInstance().getCurrentUser().setHealthPoint(5);
        Plane.getInstance().setLivesNumber(5);
        UserController.getInstance().getCurrentUser().setMode("Medium");
    }
    public void makeHard() {
        UserController.getInstance().getCurrentUser().setAttackPoint(0.5);
        UserController.getInstance().getCurrentUser().setDamagePoint(1.5);
        UserController.getInstance().getCurrentUser().setHealthPoint(2);
        Plane.getInstance().setLivesNumber(2);
        UserController.getInstance().getCurrentUser().setMode("Hard");
    }

    public ArrayList<Image> buildYellowMiniBosses() {
        ArrayList<Image> yellowMiniBosses = new ArrayList<>();
        Image image;
        for (int i = 1; i < 5; i++) {
            image = new Image(String.valueOf(WelcomePage.class.getResource("image/MiniBossFly/yellow/" + i + ".png")));
            yellowMiniBosses.add(image);
        }
        return yellowMiniBosses;
    }

    public ArrayList<Image> buildPurpleMiniBosses() {
        ArrayList<Image> purpleMiniBosses = new ArrayList<>();
        Image image;
        for (int i = 1; i < 5; i++) {
            image = new Image(String.valueOf(WelcomePage.class.getResource("image/MiniBossFly/purple/" + i + ".png")));
            purpleMiniBosses.add(image);
        }
        return purpleMiniBosses;
    }

    public Plane buildPlane(Pane pane) {
        Plane plane = Plane.getInstance();
        plane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.UP) || keyEvent.getCode().equals(KeyCode.W)) {
                    plane.moveUp();
                }
                else if (keyEvent.getCode().equals(KeyCode.RIGHT) || keyEvent.getCode().equals(KeyCode.D)) {
                    plane.moveRight();
                }
                else if (keyEvent.getCode().equals(KeyCode.DOWN) || keyEvent.getCode().equals(KeyCode.S)) {
                    plane.moveDown();
                }
                else if (keyEvent.getCode().equals(KeyCode.LEFT) || keyEvent.getCode().equals(KeyCode.A)) {
                    plane.moveLeft();
                }
                else if (keyEvent.getCode().equals(KeyCode.SPACE)) {
                    if (!plane.isBomb()) {
                        //shoot bullet
                        Bullet bullet = new Bullet(plane);
                        pane.getChildren().add(bullet);
                        bulletAnimation = new BulletAnimation(bullet, Boss.getInstance());
                        bulletAnimation.play();
                        bossHealthPointText(Boss.getInstance(), pane);
                    }
                    else {
                        //drop a bomb
                        Bomb bomb = new Bomb(plane);
                        pane.getChildren().add(bomb);
                        bombAnimation = new BombAnimation(bomb, Boss.getInstance());
                        bombAnimation.play();
                        bossHealthPointText(Boss.getInstance(), pane);
                    }

                }
                else if (keyEvent.getCode().equals(KeyCode.TAB)) {
                    if (plane.isBomb()) {
                        //turn to bullet
                        plane.setBomb(false);
                        pane.getChildren().add(createLogo(false));
                    }
                    else {
                        //turn to bomb
                        plane.setBomb(true);
                        pane.getChildren().add(createLogo(true));
                    }
                }
                else if (keyEvent.getCode().equals(KeyCode.X)) {
                    if (mediaPlayer.isMute())
                        unMute();
                    else
                        mute();
                }
                else if (keyEvent.getCode().equals(KeyCode.Q) || keyEvent.getCode().equals(KeyCode.ESCAPE)) {
                    endGame(false);
                }
            }
        });
        return plane;
    }

    public Boss buildBoss() {
        bossAnimation = new BossAnimation();
        bossAnimation.play();
        return Boss.getInstance();
    }

    public void buildBall() {
        Ball ball = new Ball(Boss.getInstance());
        ballAnimation = new BallAnimation(ball, Plane.getInstance());
        ballAnimation.play();
        Pane pane = (Pane) WelcomePage.scene.getRoot();
        pane.getChildren().add(ball);
    }

    public void buildMiniBoss(boolean yellow) {
        MiniBoss miniBoss = new MiniBoss(1280, (int) (Plane.getInstance().getY() + Plane.getInstance().getHeight() / 2), yellow);
        miniBossAnimation = new MiniBossAnimation(miniBoss);
        miniBossAnimation.play();
        Pane pane = (Pane) WelcomePage.scene.getRoot();
        pane.getChildren().add(miniBoss);
        miniBosses.add(miniBoss);
    }

    public ImageView createLogo(boolean isBomb) {
        // logo
        Image image;
        if (!isBomb) {
            image = new Image(String.valueOf(WelcomePage.class.getResource("image/images/BulletLogo.png")));
        }
        else {
            image = new Image(String.valueOf(WelcomePage.class.getResource("image/images/BombLogo.png")));
        }
        ImageView imageView = new ImageView(image);
        imageView.setX(1230);
        imageView.setY(0);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        return imageView;
    }

    public void bossHealthPointText(Boss boss, Pane pane) {
        //health point of boss
        String healthPoint = Double.toString(boss.getHealthPoint() / 5);
        Text text = new Text("Boss Health : " + healthPoint + "%");
        text.setStyle("-fx-fill: yellow; -fx-font-size: 24");
        text.setX(1000);
        text.setY(80);
        pane.getChildren().remove(boss.getHealthPointText());
        boss.setHealthPointText(text);
        pane.getChildren().add(text);
    }

    public void endGame(boolean win) {
        if (ballAnimation != null)
            ballAnimation.stop();
        if (bombAnimation != null)
            bossAnimation.stop();
        if (bossAnimation != null)
            bossAnimation.stop();
        if (bulletAnimation != null)
            bulletAnimation.stop();
        if (miniBossAnimation != null)
            miniBossAnimation.stop();
        mediaPlayer.stop();
        Boss.getInstance().setHealthPoint(500);
        Plane.getInstance().setLivesNumber(UserController.getInstance().getCurrentUser().getHealthPoint());
        try {
            new EndGameView().run(win);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean conditionOfShootingTheBall() {
        boolean first = (Plane.getInstance().getY() + Plane.getInstance().getHeight() / 2) - (Boss.getInstance().getY() + Boss.getInstance().getHeight() / 2) >= 0;
        boolean second = (Plane.getInstance().getY() + Plane.getInstance().getHeight() / 2) - (Boss.getInstance().getY() + Boss.getInstance().getHeight() / 2) <= 40;
        boolean third = (Boss.getInstance().getY() + Boss.getInstance().getHeight() / 2) - (Plane.getInstance().getY() + Plane.getInstance().getHeight() / 2) >= 0;
        boolean fourth = (Boss.getInstance().getY() + Boss.getInstance().getHeight() / 2) - (Plane.getInstance().getY() + Plane.getInstance().getHeight() / 2) <= 40;
        return (first && second) || (third && fourth);
    }

    public void removeMiniBoss(MiniBoss miniBoss) {
        miniBosses.remove(miniBoss);
    }

    public ArrayList<MiniBoss> getMiniBosses() {
        return miniBosses;
    }

    public void buildBackGround(Pane pane) {
        Image image = new Image(String.valueOf(WelcomePage.class.getResource("image/BackGround/3.png")));
        ImageView imageView = new ImageView(image);
        pane.getChildren().add(imageView);
        BackGroundAnimation backGroundAnimation = new BackGroundAnimation(imageView);
        backGroundAnimation.play();
    }

    public void playMusic() {
        Media media = new Media(Objects.requireNonNull(WelcomePage.class.getResource("music/Game.mp3")).toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    public void mute() {
        mediaPlayer.setMute(true);
        mediaPlayer.pause();
    }
    public void unMute() {
        mediaPlayer.setMute(false);
        mediaPlayer.play();
    }
}
