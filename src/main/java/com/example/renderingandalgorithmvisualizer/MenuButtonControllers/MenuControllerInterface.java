package com.example.renderingandalgorithmvisualizer.MenuButtonControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public interface MenuControllerInterface
{
    @FXML
    public void OpenMenuWindow(ActionEvent event) throws IOException;

    @FXML
    public default void QuitToDesktop(ActionEvent event) {

    }


}
