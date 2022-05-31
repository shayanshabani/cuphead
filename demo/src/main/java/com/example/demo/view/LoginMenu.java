package com.example.demo.view;

import com.example.demo.WelcomePage;
import com.example.demo.controller.UserController;
import com.example.demo.model.User;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static com.example.demo.WelcomePage.scene;


public class LoginMenu {
    @FXML
    private Button backButton;
    @FXML
    private Button loginButton;
    @FXML
    private TextField password;
    @FXML
    private TextField username;


    private boolean message;

    public void run() throws IOException {
        BorderPane root = FXMLLoader.load(Objects.requireNonNull(LoginMenu.class.getResource("login_menu.fxml")));
        scene.setRoot(root);
    }

    public void login(MouseEvent mouseEvent) throws IOException {
        if (message) {
            BorderPane borderPane = (BorderPane) scene.getRoot();
            borderPane.getChildren().remove(borderPane.getChildren().size() - 1);
            message = false;
        }
        if (UserController.getInstance().ifSameUsernameExists(username.getText())) {
            User user = UserController.getInstance().getUserByUsername(username.getText());
            if (user.getPassword().equals(password.getText())) {
                new MainMenu().run(user);
            }
            else {
                errorMessageLogin();
            }
        }
        else {
            errorMessageLogin();
        }
    }

    private void errorMessageLogin() {
        Text text = new Text();
        text.setText("password and username didn't match");
        text.setX(110);
        text.setY(45);
        text.setStyle("-fx-font-size: 18; -fx-fill: yellow");
        BorderPane borderPane = (BorderPane) scene.getRoot();
        borderPane.getChildren().add(text);
        message = true;
    }

    public void mouseShapeEnter(MouseEvent mouseEvent) {
        scene.setCursor(Cursor.HAND);
    }

    public void mouseShapeExit(MouseEvent mouseEvent) {
        scene.setCursor(Cursor.DEFAULT);
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        BorderPane root = FXMLLoader.load(Objects.requireNonNull(WelcomePage.class.getResource("welcome_page.fxml")));
        scene.setRoot(root);
    }
}
