package com.example.demo.view;

import com.example.demo.WelcomePage;
import com.example.demo.controller.UserController;
import com.example.demo.model.User;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import static com.example.demo.WelcomePage.scene;

public class Profile {
    private User user;
    public void run(User user) throws IOException {
        this.user = user;
        Pane borderPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("profile.fxml")));

        ImageView imageView = new ImageView(new Image(String.valueOf(WelcomePage.class.getResource("image/Profile/" + user.getProfilePicture()))));

        imageView.setLayoutX(50);
        imageView.setLayoutY(10);
        imageView.setFitWidth(70);
        imageView.setFitHeight(70);

        borderPane.getChildren().add(imageView);

        Text text = new Text();
        text.setText("Profile");
        text.setX(110);
        text.setY(80);
        text.setStyle("-fx-fill: yellow; -fx-font-size: 20");
        borderPane.getChildren().add(text);

        text = new Text();
        text.setText("Username: " + user.getUsername());
        text.setX(110);
        text.setY(130);
        text.setStyle("-fx-font-size: 18; -fx-fill: yellow");
        borderPane.getChildren().add(text);

        text = new Text();
        text.setText("Score: " + user.getScore());
        text.setX(110);
        text.setY(180);
        text.setStyle("-fx-font-size: 18; -fx-fill: yellow");
        borderPane.getChildren().add(text);

        text = new Text();
        String mode;
        text.setText("Mode: " + user.getMode());
        text.setX(110);
        text.setY(230);
        text.setStyle("-fx-font-size: 18; -fx-fill: yellow");
        borderPane.getChildren().add(text);

        Button button = new Button();
        button.setText("change username");
        button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mouseShapeEnter(mouseEvent);
            }
        });
        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mouseShapeExit(mouseEvent);
            }
        });
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    changeUsername(mouseEvent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        button.setLayoutX(620);
        button.setLayoutY(200);
        borderPane.getChildren().add(button);

        button = new Button();
        button.setText("change password");
        button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mouseShapeEnter(mouseEvent);
            }
        });
        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mouseShapeExit(mouseEvent);
            }
        });
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    changePassword(mouseEvent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        button.setLayoutX(620);
        button.setLayoutY(300);
        borderPane.getChildren().add(button);

        button = new Button();
        button.setText("delete account");
        button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mouseShapeEnter(mouseEvent);
            }
        });
        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mouseShapeExit(mouseEvent);
            }
        });
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    deleteAccount(mouseEvent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        button.setLayoutX(620);
        button.setLayoutY(400);
        borderPane.getChildren().add(button);

        button = new Button();
        button.setText("Back");
        button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mouseShapeEnter(mouseEvent);
            }
        });
        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mouseShapeExit(mouseEvent);
            }
        });
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                back();
            }
        });
        button.setLayoutX(620);
        button.setLayoutY(500);
        borderPane.getChildren().add(button);

        scene.setRoot(borderPane);

    }

    public void changeUsername(MouseEvent mouseEvent) throws IOException {
        Pane borderPane = FXMLLoader.load(Objects.requireNonNull(Profile.class.getResource("profile.fxml")));
        Label label = new Label();
        label.setText("new username");
        label.setLayoutX(620);
        label.setLayoutY(100);
        label.setStyle("-fx-font-size: 18; -fx-fill: yellow");
        TextField textField = new TextField();
        textField.setPromptText("Enter new username");
        textField.setLayoutX(620);
        textField.setLayoutY(200);
        Button button = new Button();
        button.setText("submit");
        button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                scene.setCursor(Cursor.HAND);
            }
        });
        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                scene.setCursor(Cursor.DEFAULT);
            }
        });
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String username = textField.getText();
                if (username.length() > 0 && !user.getUsername().equals(username)) {
                    textField.setStyle("-fx-border-width: 0");
                    user.setUsername(username);
                    try {
                        run(user);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    textField.setStyle("-fx-border-color: red; -fx-border-width: 3");
                }
            }
        });
        button.setLayoutX(630);
        button.setLayoutY(400);
        borderPane.getChildren().add(label);
        borderPane.getChildren().add(textField);
        borderPane.getChildren().add(button);
        scene.setRoot(borderPane);
    }

    public void changePassword(MouseEvent mouseEvent) throws IOException {
        Pane borderPane = FXMLLoader.load(Objects.requireNonNull(Profile.class.getResource("profile.fxml")));
        Label label = new Label();
        label.setText("current password");
        label.setLayoutX(620);
        label.setLayoutY(100);
        label.setStyle("-fx-font-size: 18; -fx-fill: yellow");
        borderPane.getChildren().add(label);
        TextField textField = new TextField();
        textField.setPromptText("enter current password");
        textField.setLayoutX(620);
        textField.setLayoutY(200);
        borderPane.getChildren().add(textField);
        label = new Label();
        label.setText("new password");
        label.setLayoutX(620);
        label.setLayoutY(300);
        label.setStyle("-fx-font-size: 18; -fx-fill: yellow");
        borderPane.getChildren().add(label);
        TextField textField2 = new TextField();
        textField2.setPromptText("enter new password");
        textField2.setLayoutX(620);
        textField2.setLayoutY(400);
        borderPane.getChildren().add(textField2);

        Button button = new Button();
        button.setText("submit");
        button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mouseShapeEnter(mouseEvent);
            }
        });
        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mouseShapeExit(mouseEvent);
            }
        });
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String currentPass = textField.getText();
                String newPass = textField2.getText();
                if (currentPass.equals(user.getPassword())) {
                    if (!newPass.equals("")) {
                        textField.setStyle("-fx-border-width: 0");
                        textField2.setStyle("-fx-border-width: 0");
                        user.setPassword(newPass);
                        try {
                            run(user);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                    else {
                        textField2.setStyle("-fx-border-color: red; -fx-border-width: 5");
                    }
                }
                else {
                    textField.setStyle("-fx-border-color: red; -fx-border-width: 5");
                }
            }
        });
        button.setLayoutX(620);
        button.setLayoutY(500);
        borderPane.getChildren().add(button);
        scene.setRoot(borderPane);
    }

    public void deleteAccount(MouseEvent mouseEvent) throws IOException {
        Pane borderPane = FXMLLoader.load(Objects.requireNonNull(Profile.class.getResource("profile.fxml")));
        Label label = new Label();
        label.setText("username");
        label.setLayoutX(620);
        label.setLayoutY(100);
        label.setStyle("-fx-font-size: 18; -fx-fill: yellow");
        borderPane.getChildren().add(label);

        TextField textField = new TextField();
        textField.setPromptText("enter your username");
        textField.setLayoutX(620);
        textField.setLayoutY(200);
        borderPane.getChildren().add(textField);

        label = new Label();
        label.setText("password");
        label.setLayoutX(620);
        label.setLayoutY(300);
        label.setStyle("-fx-font-size: 18; -fx-fill: yellow");
        borderPane.getChildren().add(label);

        TextField textField1 = new TextField();
        textField1.setPromptText("enter your password");
        textField1.setLayoutX(620);
        textField1.setLayoutY(400);
        borderPane.getChildren().add(textField1);

        Button button = new Button();
        button.setLayoutX(620);
        button.setLayoutY(500);
        button.setText("submit");
        button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mouseShapeEnter(mouseEvent);
            }
        });
        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mouseShapeExit(mouseEvent);
            }
        });
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String username = textField.getText();
                String password = textField1.getText();
                if (user.getUsername().equals(username)) {
                    if (user.getPassword().equals(password)) {
                        UserController.getInstance().removeUser(username);
                        try {
                            new WelcomePage().start(WelcomePage.stage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        textField1.setStyle("-fx-border-color: red; -fx-border-width: 5");
                    }
                }
                else {
                    textField.setStyle("-fx-border-color: red; -fx-border-width: 5");
                }
            }
        });
        borderPane.getChildren().add(button);
        scene.setRoot(borderPane);
    }

    public void mouseShapeExit(MouseEvent mouseEvent) {
        scene.setCursor(Cursor.DEFAULT);
    }

    public void mouseShapeEnter(MouseEvent mouseEvent) {
        scene.setCursor(Cursor.HAND);
    }
    private void back() {
        try {
            scene.setRoot(FXMLLoader.load(Objects.requireNonNull(MainMenu.class.getResource("main_menu.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
