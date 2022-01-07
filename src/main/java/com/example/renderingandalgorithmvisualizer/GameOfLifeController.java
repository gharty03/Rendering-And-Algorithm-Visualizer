package com.example.renderingandalgorithmvisualizer;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
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
    @FXML
    private Slider speedSlider;
    private AnimationTimer gt;
    private long timerSpeed;
    private long timerBaseSpeed = 5000000L;

    protected void initGameOfLifeWindow()
    {
        //todo
        speedSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldNum, Number newNum) {
                timerSpeed = (int)Math.pow(2, (10 - newNum.intValue())) * timerBaseSpeed;
            }
        });


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

    protected void onSpeedSliderValueChanged(ActionEvent event)
    {
        //todo
    }

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

    private class GOLTimer extends AnimationTimer
    {
        private double curX,curY;
        private long lastUpdatedTime;

        @Override
        public void handle(long currentTime)
        {
            if (currentTime - lastUpdatedTime >= timerSpeed)
            {
                lastUpdatedTime = currentTime;

                GraphicsContext gc = canvas.getGraphicsContext2D();
                gc.clearRect(0,0,1000,1000);
                gc.setFill(Color.AQUA);
                gc.fillOval(curX += 20, curY,40,40);
            }
        }
    }
}
