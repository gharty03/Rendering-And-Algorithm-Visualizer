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
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PathTracingAlgorithmController implements MenuControllerInterface {

    @FXML
    private AnchorPane AlgorithmWindow;
    @FXML
    private Button QuitApp, ReturnToMenu;
    private AnimationTimer gt;
    private Stage stage;
    private Parent root;
    private FXMLLoader fxmlLoader;
    @FXML
    private Canvas canvas;



    @FXML
    public void QuitToDesktop(ActionEvent event){
        javafx.application.Platform.exit();
    }


    public void OpenMenuWindow(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MenuWindow.fxml"));
        root = fxmlLoader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setWidth(450);
        stage.setHeight(400);
        stage.setTitle("RAAV - Rendering and Algorithm Visualizer");
        stage.centerOnScreen();
        stage.show();
        if (gt != null) {gt.stop();}

    }

    
}
