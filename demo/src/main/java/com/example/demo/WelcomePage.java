package com.example.demo;

import com.example.demo.controller.UserController;
import com.example.demo.model.User;
import com.example.demo.view.LoginMenu;
import com.example.demo.view.MainMenu;
import com.example.demo.view.RegisterMenu;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.effect.Effect;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class WelcomePage extends Application {
    @FXML
    private Button exitButton;
    @FXML
    private Button guestButton;
    @FXML
    private Button registerButton;
    @FXML
    private Button loginButton;
    public static Scene scene;
    public static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = FXMLLoader.load(Objects.requireNonNull(WelcomePage.class.getResource("welcome_page.fxml")));
        Scene scene = new Scene(root);
        WelcomePage.scene = scene;
        stage.setScene(scene);
        UserController.getInstance().readFromJson();
        WelcomePage.stage = stage;
        stage.show();
    }

    public void login(MouseEvent mouseEvent) throws IOException {
        new LoginMenu().run();
    }

    public void register(MouseEvent mouseEvent) throws IOException {
        new RegisterMenu().run();
    }

    public void mouseShapeEnter(MouseEvent mouseEvent) {
        scene.setCursor(Cursor.HAND);
    }

    public void mouseShapeExit(MouseEvent mouseEvent) {
        scene.setCursor(Cursor.DEFAULT);
    }

    public void guest(MouseEvent mouseEvent) {
        String username = "guest";
        String password = "1234";
        Random random = new Random();
        int index = random.nextInt(7) + 1;
        User user = new User(username, password, index + ".png");
        try {
            new MainMenu().run(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exit(MouseEvent mouseEvent) {
        UserController.getInstance().writeToJson();
        System.exit(0);
    }
}
