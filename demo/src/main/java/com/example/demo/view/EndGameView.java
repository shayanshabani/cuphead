package com.example.demo.view;


import com.example.demo.WelcomePage;
import com.example.demo.controller.UserController;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Objects;

public class EndGameView {
    public void run(boolean win) throws IOException {
        Pane pane = FXMLLoader.load(Objects.requireNonNull(EndGameView.class.getResource("end_game_view.fxml")));
        Text text;
        if (win) {
            text = new Text("you won!");
        }
        else {
            text = new Text("you lost!");
        }
        text.setStyle("-fx-fill: purple; -fx-font-size: 24");
        text.setX(620);
        text.setY(20);
        pane.getChildren().add(text);
        Button button = new Button();
        button.setText("restart");
        button.setLayoutX(620);
        button.setLayoutY(100);
        button.setOnMouseEntered(mouseEvent -> WelcomePage.scene.setCursor(Cursor.HAND));
        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                WelcomePage.scene.setCursor(Cursor.DEFAULT);
            }
        });
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    new GameView().run();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        pane.getChildren().add(button);

        button = new Button();
        button.setText("Main Menu");
        button.setLayoutX(620);
        button.setLayoutY(200);
        button.setOnMouseEntered(mouseEvent -> WelcomePage.scene.setCursor(Cursor.HAND));
        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                WelcomePage.scene.setCursor(Cursor.DEFAULT);
            }
        });
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    new MainMenu().run(UserController.getInstance().getCurrentUser());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        pane.getChildren().add(button);
        WelcomePage.scene = new Scene(pane);
        WelcomePage.stage.setScene(WelcomePage.scene);
    }
}
