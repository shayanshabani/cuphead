package com.example.demo.view;

import com.example.demo.WelcomePage;
import com.example.demo.controller.GameController;
import com.example.demo.controller.UserController;
import com.example.demo.model.User;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Objects;

public class Setting {
    @FXML
    private Button muteButton;

    public void mouseShapeEnter(MouseEvent mouseEvent) {
        WelcomePage.scene.setCursor(Cursor.HAND);
    }

    public void mouseShapeExit(MouseEvent mouseEvent) {
        WelcomePage.scene.setCursor(Cursor.DEFAULT);
    }

    public void setEasy(MouseEvent mouseEvent) {
        GameController.getInstance().makeEasy();
        back(mouseEvent);
    }

    public void setMedium(MouseEvent mouseEvent) {
        GameController.getInstance().makeMedium();
        back(mouseEvent);
    }

    public void setHard(MouseEvent mouseEvent) {
        GameController.getInstance().makeHard();
        back(mouseEvent);
    }

    public void mute(MouseEvent mouseEvent) {
        if (muteButton.getText().equals("Mute")) {
            muteButton.setText("Unmute");
        }
        else {
            muteButton.setText("Mute");
        }
    }

    public void back(MouseEvent mouseEvent) {
        try {
            WelcomePage.scene.setRoot(FXMLLoader.load(Objects.requireNonNull(MainMenu.class.getResource("main_menu.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
