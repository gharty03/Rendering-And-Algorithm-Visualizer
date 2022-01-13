package com.example.renderingandalgorithmvisualizer.MenuButtonControllers;

import com.example.renderingandalgorithmvisualizer.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    @FXML
    private Label TitleText;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader fxmlLoader;

    @FXML
    protected void openGameOfLifeWindow(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource("GameOfLifeWindow.fxml"));
        root = fxmlLoader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setWidth(1024);
        stage.setHeight(785);
        stage.setTitle("RAAV - Conway's Game of Life");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
        ((GameOfLifeController)fxmlLoader.getController()).initGameOfLifeWindow();
    }

    @FXML
    protected void openFloodFillWindow(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource("FloodFillWindow.fxml"));
        root = fxmlLoader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setWidth(1024);
        stage.setHeight(768);
        stage.setTitle("RAAV - Flood Fill Algorithm");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
        ((FloodFillController)fxmlLoader.getController()).initFloodFillWindow();
    }

    @FXML
    protected void SortingAlgorithmWindow(ActionEvent event) throws IOException
    {
        fxmlLoader = new FXMLLoader(Main.class.getResource("PathTracingAlgorithmVisualizer.fxml"));
        root = fxmlLoader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setWidth(1024);
        stage.setHeight(768);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    protected void openBresenhamsLineAlgorithmWindow(ActionEvent event) throws IOException
    {
        fxmlLoader = new FXMLLoader(Main.class.getResource("BresenhamsLineAlgorithmWindow.fxml"));
        root = fxmlLoader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setWidth(1024);
        stage.setHeight(768);
        stage.setTitle("RAAV - Bresenham's Line Algorithm");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
        ((BresenhamsLineAlgorithmController)fxmlLoader.getController()).initBresenhamsLineAlgorithmWindow();
    }

}


