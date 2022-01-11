package com.example.renderingandalgorithmvisualizer.MenuButtonControllers;

import com.example.renderingandalgorithmvisualizer.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public interface MenuControllerInterface
{
    @FXML
    public default void OpenMenuWindow(ActionEvent event) throws IOException
    {
        Stage stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MenuWindow.fxml"));
        Parent root = fxmlLoader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setWidth(450);
        stage.setHeight(400);
        stage.setTitle("RAAV - Rendering and Algorithm Visualizer");
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    public default void QuitToDesktop(ActionEvent event) {

    }


}
