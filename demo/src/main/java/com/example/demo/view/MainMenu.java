package com.example.demo.view;

import com.example.demo.WelcomePage;
import com.example.demo.controller.UserController;
import com.example.demo.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static com.example.demo.WelcomePage.scene;

public class MainMenu {
    @FXML
    private Button newGameButton;
    @FXML
    private Button resumeGameButton;
    @FXML
    private Button profileButton;
    @FXML
    private Button scoreButton;
    @FXML
    private Button backButton;

    public static User user;

    public void run(User user) throws IOException {
        MainMenu.user = user;
        UserController.getInstance().setCurrentUser(user);
        BorderPane borderPane = FXMLLoader.load(Objects.requireNonNull(MainMenu.class.getResource("main_menu.fxml")));
        scene.setRoot(borderPane);
    }

    public void newGame(MouseEvent mouseEvent) {
        try {
            new GameView().run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resumeGame(MouseEvent mouseEvent) {
        //todo load and start the game
    }

    public void profile(MouseEvent mouseEvent) throws IOException {
        new Profile().run(user);
    }

    public void scoreboard(MouseEvent mouseEvent) {
        try {
            new ScoreBoard().run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        BorderPane root = FXMLLoader.load(Objects.requireNonNull(WelcomePage.class.getResource("welcome_page.fxml")));
        scene.setRoot(root);
    }

    public void mouseShapeEnter(MouseEvent mouseEvent) {
        scene.setCursor(Cursor.HAND);
    }

    public void mouseShapeExit(MouseEvent mouseEvent) {
        scene.setCursor(Cursor.DEFAULT);
    }

    public void setting(MouseEvent mouseEvent) {
        try {
            scene.setRoot(FXMLLoader.load(Objects.requireNonNull(Setting.class.getResource("setting.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
