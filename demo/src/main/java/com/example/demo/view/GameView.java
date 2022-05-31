package com.example.demo.view;

import com.example.demo.WelcomePage;
import com.example.demo.controller.GameController;
import com.example.demo.model.Bomb;
import com.example.demo.model.Boss;
import com.example.demo.model.Bullet;
import com.example.demo.model.Plane;
import com.example.demo.model.animation.BombAnimation;
import com.example.demo.model.animation.BossAnimation;
import com.example.demo.model.animation.BulletAnimation;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.w3c.dom.events.MutationEvent;

import java.io.IOException;
import java.util.Objects;
import static com.example.demo.WelcomePage.scene;

public class GameView {
    @FXML
    private Button muteButton;
    public void run() throws IOException {

        Pane pane = FXMLLoader.load(Objects.requireNonNull(GameView.class.getResource("game_view.fxml")));
        //play music
        GameController.getInstance().playMusic();
        //set plane
        Plane plane = GameController.getInstance().buildPlane(pane);
        pane.getChildren().add(plane);
        //set logo
        ImageView imageView = GameController.getInstance().createLogo(plane.isBomb());
        pane.getChildren().add(imageView);
        //set boss
        Boss boss = GameController.getInstance().buildBoss();
        pane.getChildren().add(boss);
        //background
        GameController.getInstance().buildBackGround(pane);
        //boss health point
        GameController.getInstance().bossHealthPointText(boss, pane);
        //set pane
        scene.setRoot(pane);
        //focus on plane
        pane.getChildren().get(0).requestFocus();
    }
}
