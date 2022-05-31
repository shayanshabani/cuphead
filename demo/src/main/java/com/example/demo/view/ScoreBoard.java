package com.example.demo.view;

import com.example.demo.WelcomePage;
import com.example.demo.controller.UserController;
import com.example.demo.model.User;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Comparator;
import java.util.Objects;

public class ScoreBoard {
    public void run() throws IOException {
        int i = 0;
        UserController.getInstance().getUsers().sort(Comparator.comparing(User::getScore).reversed());
        Pane pane = FXMLLoader.load(Objects.requireNonNull(ScoreBoard.class.getResource("score_board.fxml")));
        for (User user : UserController.getInstance().getUsers()) {
            if (i == 10)
                break;

            Text text = new Text();
            text.setText(user.getUsername() + " : " + user.getScore());
            text.setStyle("-fx-font-size: 18; -fx-fill: yellow;");
            text.setX(50);
            text.setY((i + 1) * 70);
            pane.getChildren().add(text);
            i++;
        }
        Button button = new Button();
        button.setText("Back");
        button.setLayoutX(620);
        button.setLayoutY(390);
        button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                WelcomePage.scene.setCursor(Cursor.HAND);
            }
        });
        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                WelcomePage.scene.setCursor(Cursor.DEFAULT);
            }
        });
        final boolean[] back = {false};
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    WelcomePage.scene.setRoot(FXMLLoader.load(Objects.requireNonNull(MainMenu.class.getResource("main_menu.fxml"))));
                    back[0] = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        pane.getChildren().add(button);
        if (!back[0])
            WelcomePage.scene.setRoot(pane);
    }
}
