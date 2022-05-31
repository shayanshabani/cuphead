module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.sql;
    requires javafx.graphics;
    requires javafx.media;

    opens com.example.demo.model to com.google.gson;
    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    opens com.example.demo.view to javafx.fxml;
    exports com.example.demo.view;
    exports com.example.demo.controller;
    exports com.example.demo.model;
    opens com.example.demo.controller to com.google.gson;
}