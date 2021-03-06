package com.example.renderingandalgorithmvisualizer.MenuButtonControllers;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.IOException;

public class GameOfLifeController implements MenuControllerInterface
{
    @FXML
    private Canvas canvas;
    @FXML
    private Button startButton, pauseButton, stopButton, resumeButton;
    @FXML
    private Slider speedSlider;
    private AnimationTimer gt;
    private long timerSpeed;
    private final long timerBaseSpeed = 5000000L;
    private final int numCols = 37, numRows = 26;
    private int[][] gameArray;

    public void initGameOfLifeWindow()
    {
        speedSlider.valueProperty().addListener((observableValue, oldNum, newNum) -> timerSpeed = (int)Math.pow(2, (10 - newNum.intValue())) * timerBaseSpeed);
        initGame();
    }

    protected void initGame()
    {
        gameArray = new int[numCols][numRows];
        timerSpeed = (int)Math.pow(2, (10 - (int)speedSlider.getValue())) * timerBaseSpeed;
        initGameCanvas();
    }

    protected void initGameCanvas()
    {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0,1000,1000);
        gc.setFill(Color.web("#09e0fc"));
        gc.fillRect(0,0,1000,1000);
        paintSquaresOnCanvas();
    }

    protected void processTick()
    {
        int[][] tempArray = new int[numCols][numRows];
        for (int x = 0; x < numCols; x++)
        {
            for (int y = 0; y < numRows; y++)
            {
                int neighborCount = 0;
                if (x > 0)
                    neighborCount += gameArray[x - 1][y];
                if (x < numCols - 1)
                    neighborCount += gameArray[x + 1][y];
                if (y > 0)
                    neighborCount += gameArray[x][y - 1];
                if (y < numRows - 1)
                    neighborCount += gameArray[x][y + 1];
                if (x > 0 && y > 0)
                    neighborCount += gameArray[x - 1][y - 1];
                if (x > 0 && y < numRows - 1)
                    neighborCount += gameArray[x - 1][y + 1];
                if (x < numCols - 1 && y > 0)
                    neighborCount += gameArray[x + 1][y - 1];
                if (x < numCols - 1 && y < numRows - 1)
                    neighborCount += gameArray[x + 1][y + 1];

                if (gameArray[x][y] == 1)
                {
                    if (neighborCount < 2 || neighborCount > 3)
                        tempArray[x][y] = 0;
                    else
                        tempArray[x][y] = 1;
                }
                else
                {
                    if (neighborCount == 3)
                        tempArray[x][y] = 1;
                }
            }
        }

        gameArray = tempArray;
    }

    protected void paintSquaresOnCanvas()
    {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        for (int x = 0; x < numCols; x++)
        {
            for (int y = 0; y < numRows; y++)
            {
                if (gameArray[x][y] == 0)
                    gc.setFill(Color.web("#9c9c9c"));
                else
                    gc.setFill(Color.web("#96cc6a"));

                gc.fillRect((x * 25),(y * 25), 24,24);
            }
        }
    }

    @FXML
    protected void onStartButtonPressed()
    {
        pauseButton.setDisable(false);
        stopButton.setDisable(false);
        startButton.setDisable(true);
        gt = new GOLTimer();
        gt.start();
    }

    @FXML
    protected void onCanvasClicked(MouseEvent event)
    {
        MouseButton clicked = event.getButton();
        int cellPosX = (int)event.getX() / 25;
        int cellPosY = (int)event.getY() / 25;
        if (clicked.name().equals("PRIMARY"))
            gameArray[cellPosX][cellPosY] = 1;
        else if (clicked.name().equals("SECONDARY"))
            gameArray[cellPosX][cellPosY] = 0;
        paintSquaresOnCanvas();
    }

    @FXML
    protected void onCanvasMouseDragged(MouseEvent event)
    {
        MouseButton clicked = event.getButton();
        int cellPosX = (int)event.getX() / 25;
        int cellPosY = (int)event.getY() / 25;
        if (cellPosX < 0 || cellPosX >= numCols || cellPosY < 0 || cellPosY >= numRows)
            return;
        if (clicked.name().equals("PRIMARY"))
            gameArray[cellPosX][cellPosY] = 1;
        else if (clicked.name().equals("SECONDARY"))
            gameArray[cellPosX][cellPosY] = 0;
        paintSquaresOnCanvas();
    }

    @FXML
    protected void onResumeButtonPressed()
    {
        pauseButton.setDisable(false);
        stopButton.setDisable(false);
        startButton.setDisable(true);
        resumeButton.setDisable(true);
        gt.start();
    }

    @FXML
    protected void onPauseButtonPressed()
    {
        pauseButton.setDisable(true);
        resumeButton.setDisable(false);
        gt.stop();
    }

    @FXML
    protected void onStopButtonPressed()
    {
        initGame();
        pauseButton.setDisable(true);
        resumeButton.setDisable(true);
        stopButton.setDisable(true);
        startButton.setDisable(false);
        gt.stop();
    }

    @FXML
    @Override
    public void OpenMenuWindow(ActionEvent event) throws IOException
    {
        MenuControllerInterface.super.OpenMenuWindow(event);
        if (gt != null) {gt.stop();}
    }

    @FXML
    @Override
    public void QuitToDesktop(ActionEvent event)
    {
        if (gt != null)
            gt.stop();
        MenuControllerInterface.super.QuitToDesktop(event);
    }

    private class GOLTimer extends AnimationTimer
    {
        private long lastUpdatedTime;

        @Override
        public void handle(long currentTime)
        {
            if (currentTime - lastUpdatedTime >= timerSpeed)
            {
                lastUpdatedTime = currentTime;
                processTick();
                paintSquaresOnCanvas();
            }
        }
    }
}
