package com.example.renderingandalgorithmvisualizer.MenuButtonControllers;

import com.example.renderingandalgorithmvisualizer.Main;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;

public class FloodFillController implements MenuControllerInterface
{
    @FXML
    private Stage stage;
    @FXML
    private Canvas canvas;
    @FXML
    private ColorPicker paintbrushColorPicker, floodFillColorPicker;
    private int gridSize, numCols, numRows;
    private Color[][] colorArray;
    private boolean slowed;
    private FFTimer ffTimer;
    private LinkedList<FFCoordinate> queue;
    private Color targetColor, replacementColor;

    public void initFloodFillWindow()
    {
        gridSize = 5;
        numCols = (int)canvas.getWidth() / gridSize;
        numRows = (int)canvas.getHeight() / gridSize;
        slowed = false;
        queue = null;
        targetColor = null;
        replacementColor = null;
        initCanvas();
    }

    protected void initCanvas()
    {
        colorArray = new Color[numCols][numRows];
        for (int a = 0; a < numCols; a++)
        {
            for (int b = 0; b < numRows; b++)
            {
                colorArray[a][b] = Color.web("#ffffff");
            }
        }

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0,1000,1000);
        gc.setFill(Color.web("#ffffff"));
        gc.fillRect(0,0,1000,1000);
    }

    @FXML
    protected void onCanvasClicked(MouseEvent event)
    {
        MouseButton clicked = event.getButton();
        int cellPosX = (int)event.getX() / gridSize;
        int cellPosY = (int)event.getY() / gridSize;

        if (clicked.name().equals("PRIMARY"))
        {
            colorArray[cellPosX][cellPosY] = paintbrushColorPicker.getValue();
            paintSquareOnCanvas(cellPosX, cellPosY, paintbrushColorPicker.getValue());
        }
        else if (clicked.name().equals("SECONDARY"))
        {
            if (slowed)
                floodFillSlowed(cellPosX, cellPosY, colorArray[cellPosX][cellPosY], floodFillColorPicker.getValue());
            else
                floodFill(cellPosX, cellPosY, colorArray[cellPosX][cellPosY], floodFillColorPicker.getValue());
        }
    }

    private void floodFill(int xCol, int yRow, Color targetColor, Color replacementColor)
    {
        if (targetColor.equals(replacementColor))
            return;

        this.targetColor = targetColor;
        this.replacementColor = replacementColor;

        queue = new LinkedList<>();
        queue.add(new FFCoordinate(xCol, yRow));

        while (!queue.isEmpty())
        {
            FFCoordinate n = queue.removeFirst();
            if (colorArray[n.x][n.y].equals(targetColor))
            {
                colorArray[n.x][n.y] = replacementColor;
                paintSquareOnCanvas(n.x, n.y, replacementColor);

                if (n.x > 0)
                    queue.add(new FFCoordinate(n.x - 1, n.y));
                if (n.x < numCols - 1)
                    queue.add(new FFCoordinate(n.x + 1, n.y));
                if (n.y > 0)
                    queue.add(new FFCoordinate(n.x, n.y - 1));
                if (n.y < numRows - 1)
                    queue.add(new FFCoordinate(n.x, n.y + 1));
            }
        }
    }

    private void floodFillSlowed(int xCol, int yRow, Color targetColor, Color replacementColor)
    {
        if (targetColor.equals(replacementColor))
            return;

        queue = new LinkedList<>();
        queue.add(new FFCoordinate(xCol, yRow));

        this.targetColor = targetColor;
        this.replacementColor = replacementColor;

        if (ffTimer != null)
            ffTimer.stop();
        ffTimer = new FFTimer();
        ffTimer.start();
    }

    private void floodFillSlowedProcessNeighborNodes()
    {
        if (!queue.isEmpty())
        {
            FFCoordinate n = queue.removeFirst();
            if (colorArray[n.x][n.y].equals(targetColor))
            {
                colorArray[n.x][n.y] = replacementColor;
                paintSquareOnCanvas(n.x, n.y, replacementColor);

                if (n.x > 0)
                    queue.add(new FFCoordinate(n.x - 1, n.y));
                if (n.x < numCols - 1)
                    queue.add(new FFCoordinate(n.x + 1, n.y));
                if (n.y > 0)
                    queue.add(new FFCoordinate(n.x, n.y - 1));
                if (n.y < numRows - 1)
                    queue.add(new FFCoordinate(n.x, n.y + 1));
            }
        }
        else
            ffTimer.stop();
    }

    private void paintSquareOnCanvas(int cellPosX, int cellPosY, Color color)
    {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(color);
        gc.fillRect((cellPosX * gridSize),(cellPosY * gridSize), gridSize, gridSize);
    }

    @FXML
    protected void onCanvasMouseDragged(MouseEvent event)
    {
        MouseButton clicked = event.getButton();
        int cellPosX = (int)event.getX() / gridSize;
        int cellPosY = (int)event.getY() / gridSize;
        if (cellPosX < 0 || cellPosX >= numCols || cellPosY < 0 || cellPosY >= numRows)
            return;
        if (clicked.name().equals("PRIMARY"))
        {
            colorArray[cellPosX][cellPosY] = paintbrushColorPicker.getValue();
            paintSquareOnCanvas(cellPosX, cellPosY, paintbrushColorPicker.getValue());
        }
    }

    @FXML
    protected void onResetCanvasButtonClicked()
    {
        initCanvas();
        if (ffTimer != null)
            ffTimer.stop();
    }

    @FXML
    protected void onSlowedCheckBoxChanged(ActionEvent event)
    {
        slowed = ((CheckBox)event.getSource()).isSelected();
    }

    @FXML
    @Override
    public void OpenMenuWindow(ActionEvent event) throws IOException
    {
        MenuControllerInterface.super.OpenMenuWindow(event);
        if (ffTimer != null)
            ffTimer.stop();
    }

    @FXML
    public void QuitToDesktop(ActionEvent event)
    {
        // TODO: Implement quit to desktop button.
    }

    private class FFTimer extends AnimationTimer
    {
        @Override
        public void handle(long currentTime)
        {
            floodFillSlowedProcessNeighborNodes();
        }
    }

    private static class FFCoordinate
    {
        public int x, y;

        FFCoordinate(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }
}
