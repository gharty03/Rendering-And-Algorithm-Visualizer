package com.example.renderingandalgorithmvisualizer.MenuButtonControllers;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.LinkedList;

public class BresenhamsLineAlgorithmController implements MenuControllerInterface
{
    @FXML
    Canvas canvas;
    @FXML
    Label labelPointA, labelPointB;
    private int gridSize, aX, bX, aY, bY;
    private boolean slowed, pointALocked, pointBLocked, printReversed;
    private LinkedList<int[]> pointQueue;
    BLATimer blaTimer;

    public void initBresenhamsLineAlgorithmWindow()
    {
        gridSize = 5;
        pointQueue = new LinkedList<>();
        initCanvas();
    }

    protected void initCanvas()
    {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0,1000,1000);
        gc.setFill(Color.web("#ffffff"));
        gc.fillRect(0,0,1000,1000);
    }

    private void plotLine(int x0, int y0, int x1, int y1)
    {
        if (Math.abs(y1 - y0) < Math.abs(x1 - x0))
        {
            if (x0 > x1)
                plotLineLow(x1, y1, x0, y0);
            else
                plotLineLow(x0, y0, x1, y1);
        }
        else
        {
            if (y0 > y1)
                plotLineHigh(x1, y1, x0, y0);
            else
                plotLineHigh(x0, y0, x1, y1);
        }

        if (slowed)
            drawLineSlowed();
    }

    private void drawLineSlowed()
    {
        if (pointQueue.peek() != null)
            printReversed = (pointQueue.peek()[0] == aX && pointQueue.peek()[1] == aY);
        else
            throw new RuntimeException("pointQueue is empty during call to drawLineSlowed()");

        if (blaTimer != null)
            blaTimer.stop();
        blaTimer = new BLATimer();
        blaTimer.start();
    }

    private void plotLineLow(int x0, int y0, int x1, int y1)
    {
        int deltaX = x1 - x0;
        int deltaY = y1 - y0;
        int yi = 1;
        int D,y;

        if (deltaY < 0)
        {
            yi = -1;
            deltaY = -deltaY;
        }

        D = (2 * deltaY) - deltaX;
        y = y0;

        for (int x = x0; x <= x1; x++)
        {
            if (!slowed)
                drawPointOnCanvas(x, y);
            else
                pointQueue.add(new int[]{x, y});

            if (D > 0)
            {
                y = y + yi;
                D = D + (2 * (deltaY - deltaX));
            }
            else
                D = D + (2 * deltaY);
        }
    }

    private void plotLineHigh(int x0, int y0, int x1, int y1)
    {
        int deltaX = x1 - x0;
        int deltaY = y1 - y0;
        int xi = 1;
        int D,x;

        if (deltaX < 0)
        {
            xi = -1;
            deltaX = -deltaX;
        }

        D = (2 * deltaX) - deltaY;
        x = x0;

        for (int y = y0; y <= y1; y++)
        {
            if (!slowed)
                drawPointOnCanvas(x, y);
            else
                pointQueue.add(new int[]{x, y});

            if (D > 0)
            {
                x = x + xi;
                D = D + (2 * (deltaX - deltaY));
            }
            else
                D = D + (2 * deltaX);
        }
    }

    private void drawPointOnCanvas(int x, int y)
    {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect((x * gridSize),(y * gridSize), gridSize, gridSize);
    }

    @FXML
    protected void onCanvasClicked(MouseEvent event)
    {
        MouseButton clicked = event.getButton();
        int cellPosX = (int)event.getX() / gridSize;
        int cellPosY = (int)event.getY() / gridSize;

        if (clicked.name().equals("PRIMARY"))
        {
            if (pointALocked && pointBLocked)
            {
                clearPoints();
            }

            if (pointALocked)
            {
                pointBLocked = true;
                bX = cellPosX;
                bY = cellPosY;
                labelPointB.setText(String.format("Point B: (%d, %d)", bX, bY));
                plotLine(aX, aY, bX, bY);
            }
            else
            {
                pointALocked = true;
                aX = cellPosX;
                aY = cellPosY;
                labelPointA.setText(String.format("Point A: (%d, %d)", aX, aY));
            }
        }
        else if (clicked.name().equals("SECONDARY"))
        {
            clearPoints();
        }
    }

    private void clearPoints()
    {
        pointQueue = new LinkedList<>();
        labelPointA.setText("Point A: (?, ?)");
        labelPointB.setText("Point B: (?, ?)");
        pointALocked = false;
        pointBLocked = false;
        if (blaTimer != null)
            blaTimer.stop();
    }

    @FXML
    protected void onCanvasMouseDragged(MouseEvent event)
    {
        // todo
    }

    @FXML
    public void onSlowedCheckBoxChanged(ActionEvent event)
    {
        slowed = ((CheckBox)event.getSource()).isSelected();
    }

    @FXML
    protected void onResetCanvasButtonClicked()
    {
        clearPoints();
        initCanvas();
        if (blaTimer != null)
            blaTimer.stop();
    }

    @FXML
    @Override
    public void OpenMenuWindow(ActionEvent event) throws IOException
    {
        if (blaTimer != null)
            blaTimer.stop();
        MenuControllerInterface.super.OpenMenuWindow(event);
    }

    @FXML
    @Override
    public void QuitToDesktop(ActionEvent event)
    {
        if (blaTimer != null)
            blaTimer.stop();
        MenuControllerInterface.super.QuitToDesktop(event);
    }

    private class BLATimer extends AnimationTimer
    {
        @Override
        public void handle(long currentTime)
        {
            if (pointQueue.size() > 0)
            {
                int[] temp;
                if (printReversed)
                    temp = pointQueue.removeFirst();
                else
                    temp = pointQueue.removeLast();
                drawPointOnCanvas(temp[0], temp[1]);
            }
            else
                blaTimer.stop();
        }
    }
}
