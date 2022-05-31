package com.example.demo.model;

import com.example.demo.WelcomePage;
import com.example.demo.controller.GameController;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class Boss extends Rectangle {
    private static Boss boss1;
    private final ArrayList<Image> bossFly;
    private final ArrayList<Image> bossShoot;
    private double healthPoint;
    private boolean shooting;
    private Text healthPointText;

    private Boss() {
        super(750, 120, 400, 400);
        bossFly = GameController.getInstance().buildBossFly();
        bossShoot = GameController.getInstance().buildBossShoot();
        setImagePattern(bossFly.get(0));
        healthPoint = 500;
        //show health Point
        String healthPoint = Double.toString(this.getHealthPoint() / 5);
        healthPointText = new Text("Boss Health : " + healthPoint + "%");
        healthPointText.setStyle("-fx-fill: yellow; -fx-font-size: 24");
        healthPointText.setX(1000);
        healthPointText.setY(80);
        Pane pane = (Pane) WelcomePage.scene.getRoot();
        pane.getChildren().add(healthPointText);
        shooting = false;
    }
    public static Boss getInstance() {
        if (boss1 == null)
            boss1 = new Boss();
        return boss1;
    }

    public ArrayList<Image> getBossFly() {
        return bossFly;
    }

    public ArrayList<Image> getBossShoot() {
        return bossShoot;
    }

    public void setImagePattern(Image image) {
        this.setFill(new ImagePattern(image));
    }

    public void setHealthPoint(double healthPoint) {
        this.healthPoint = healthPoint;
    }

    public double getHealthPoint() {
        return healthPoint;
    }

    public Text getHealthPointText() {
        return healthPointText;
    }

    public void setHealthPointText(Text healthPointText) {
        this.healthPointText = healthPointText;
    }

    public boolean hasCollisionWithPlane(Plane plane) {
        return false;
    }

    public boolean isShooting() {
        return shooting;
    }

    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }
}
