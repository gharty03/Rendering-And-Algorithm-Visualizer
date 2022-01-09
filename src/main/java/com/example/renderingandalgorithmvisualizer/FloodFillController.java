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
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class FloodFillController
{
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader fxmlLoader;
    @FXML
    private Canvas canvas;
    @FXML
    private Button backButton;
    @FXML
    private ColorPicker paintbrushColorPicker, floodFillColorPicker;
    int brushSize, numCols, numRows;
    Color[][] colorArray;

    protected void initFloodFillWindow()
    {
        // todo
        brushSize = 5;
        numCols = (int)canvas.getWidth() / brushSize;
        numRows = (int)canvas.getHeight() / brushSize;
        initCanvas();
    }

    protected void initCanvas()
    {
        // todo
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
        System.out.println("onCanvasClicked");
        MouseButton clicked = event.getButton();
        int cellPosX = (int)event.getX() / brushSize;
        int cellPosY = (int)event.getY() / brushSize;
        if (clicked.name().equals("PRIMARY"))
        {
            colorArray[cellPosX][cellPosY] = paintbrushColorPicker.getValue();
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.setFill(paintbrushColorPicker.getValue());
            gc.fillRect((cellPosX * brushSize),(cellPosY * brushSize), brushSize, brushSize);
        }



    }

    @FXML
    protected void onCanvasMouseDragged(MouseEvent event)
    {
        System.out.println("onCanvasMouseDragged");
        /*MouseButton clicked = event.getButton();
        int cellPosX = (int)event.getX() / 25;
        int cellPosY = (int)event.getY() / 25;
        if (cellPosX < 0 || cellPosX >= numCols || cellPosY < 0 || cellPosY >= numRows)
            return;
        if (clicked.name().equals("PRIMARY"))
            gameArray[cellPosX][cellPosY] = 1;
        else if (clicked.name().equals("SECONDARY"))
            gameArray[cellPosX][cellPosY] = 0;
        paintSquaresOnCanvas();*/
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
    }
}
