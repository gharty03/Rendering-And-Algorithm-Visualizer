package com.example.renderingandalgorithmvisualizer.MenuButtonControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.IOException;

public class BresenhamsLineAlgorithmController implements MenuControllerInterface
{
    private int gridSize, numCols, numRows;

    public void initBresenhamsLineAlgorithmWindow()
    {
        gridSize = 5;
        //numCols = (int)canvas.getWidth() / gridSize;
        //numRows = (int)canvas.getHeight() / gridSize;
        initCanvas();
    }

    protected void initCanvas()
    {
        /*colorArray = new Color[numCols][numRows];
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

         */
    }


    @FXML
    @Override
    public void OpenMenuWindow(ActionEvent event) throws IOException
    {
        MenuControllerInterface.super.OpenMenuWindow(event);
    }

    @FXML
    @Override
    public void QuitToDesktop(ActionEvent event)
    {
        MenuControllerInterface.super.QuitToDesktop(event);
    }

    @FXML
    public void onCanvasClicked(MouseEvent mouseEvent)
    {

    }
}
