package com.example.renderingandalgorithmvisualizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader fxmlLoader;

    @FXML
    protected void openMenuWindow(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MenuWindow.fxml"));
        root = fxmlLoader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setWidth(300);
        stage.setHeight(400);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    protected void openRayTracingWindow(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("RayTracingWindow.fxml"));
        root = fxmlLoader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setWidth(1024);
        stage.setHeight(768);
        stage.centerOnScreen();
        stage.setFullScreen(true);
        stage.show();
    }

    @FXML
    protected void SortingAlgorithmWindow(ActionEvent event) throws IOException
    {
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SortingAlgorithmVisualizer.fxml"));
        root = fxmlLoader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setWidth(1024);
        stage.setHeight(768);
        stage.centerOnScreen();
        stage.show();
    }


}