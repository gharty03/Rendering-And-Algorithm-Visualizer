package com.example.renderingandalgorithmvisualizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

import java.io.IOException;

public class GameOfLifeController
{
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader fxmlLoader;
    private Canvas canvas;

    @FXML
    protected void openMenuWindow(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MenuWindow.fxml"));
        root = fxmlLoader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setWidth(450);
        stage.setHeight(400);
        stage.setTitle("RAAV - Rendering and Algorithm Visualizer");
        stage.centerOnScreen();
        stage.show();
    }
}
