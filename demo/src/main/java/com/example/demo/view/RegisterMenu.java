package com.example.demo.view;

import com.example.demo.WelcomePage;
import com.example.demo.controller.UserController;
import com.example.demo.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Random;

public class RegisterMenu {
    @FXML
    private Button backButton;
    @FXML
    private TextField username;
    @FXML
    private Button registerButton;
    @FXML
    private TextField firstPassword;
    @FXML
    private TextField secondPassword;

    private boolean message = false;

    public void run() throws IOException {
        BorderPane root = FXMLLoader.load(Objects.requireNonNull(RegisterMenu.class.getResource("register_menu.fxml")));
        WelcomePage.scene.setRoot(root);
    }


    public void register(MouseEvent mouseEvent) throws IOException {
        if (message) {
            BorderPane borderPane = (BorderPane) WelcomePage.scene.getRoot();
            borderPane.getChildren().remove(borderPane.getChildren().size() - 1);
            message = false;
        }
        if (!UserController.getInstance().ifSameUsernameExists(username.getText())) {
            if (firstPassword.getText().equals(secondPassword.getText())) {
                Random random = new Random();
                int index = random.nextInt(7) + 1;
                User user = new User(username.getText(), firstPassword.getText(), index + ".png");
                UserController.getInstance().addUser(user);
                new MainMenu().run(user);
            }
            else {
                Text text = new Text();
                text.setText("first and second passwords are not the same!");
                text.setX(110);
                text.setY(45);
                text.setStyle("-fx-font-size: 18; -fx-fill: yellow");
                BorderPane borderPane = (BorderPane) WelcomePage.scene.getRoot();
                borderPane.getChildren().add(text);
                message = true;
            }
        }
        else {
            Text text = new Text();
            text.setText("a user exists with this username -" + username.getText() + "- !!!");
            text.setX(110);
            text.setY(45);
            text.setStyle("-fx-font-size: 18; -fx-fill: yellow");
            BorderPane borderPane = (BorderPane) WelcomePage.scene.getRoot();
            borderPane.getChildren().add(text);
            message = true;
        }
    }

    public void mouseShapeEnter(MouseEvent mouseEvent) {
        WelcomePage.scene.setCursor(Cursor.HAND);
    }

    public void mouseShapeExit(MouseEvent mouseEvent) {
        WelcomePage.scene.setCursor(Cursor.DEFAULT);
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        BorderPane root = FXMLLoader.load(Objects.requireNonNull(WelcomePage.class.getResource("welcome_page.fxml")));
        WelcomePage.scene.setRoot(root);
    }
}
