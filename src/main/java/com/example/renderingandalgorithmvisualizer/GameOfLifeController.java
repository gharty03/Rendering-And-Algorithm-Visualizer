package com.example.renderingandalgorithmvisualizer;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class GameOfLifeController
{
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader fxmlLoader;
    @FXML
    private Canvas canvas;
    @FXML
    private Button startButton, pauseButton, stepButton, stopButton, backButton, resumeButton;
    private AnimationTimer gt;

    @FXML
    protected void openMenuWindow(ActionEvent event) throws IOException
    {
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
        if (gt != null) {gt.stop();}
    }

    @FXML
    protected void onStartButtonPressed(ActionEvent event)
    {
        pauseButton.setDisable(false);
        stopButton.setDisable(false);
        startButton.setDisable(true);
        gt = new GOLTimer();
        gt.start();
    }

    @FXML
    protected void onResumeButtonPressed(ActionEvent event)
    {
        pauseButton.setDisable(false);
        stopButton.setDisable(false);
        startButton.setDisable(true);
        resumeButton.setDisable(true);
        gt.start();
    }

    @FXML
    protected void onPauseButtonPressed(ActionEvent event)
    {
        pauseButton.setDisable(true);
        resumeButton.setDisable(false);
        gt.stop();
    }

    @FXML
    protected void onStopButtonPressed(ActionEvent event)
    {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0,1000,1000);
        pauseButton.setDisable(true);
        resumeButton.setDisable(true);
        stopButton.setDisable(true);
        startButton.setDisable(false);
        gt.stop();
    }

    @FXML
    protected void onStepButtonPressed(ActionEvent event)
    {
        // todo
    }

    private class GOLTimer extends AnimationTimer
    {
        private double curX,curY;

        @Override
        public void handle(long l)
        {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.clearRect(0,0,1000,1000);
            gc.setFill(Color.AQUA);
            gc.fillOval(curX += 1, curY,40,40);
        }
    }
}
