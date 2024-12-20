package com.rumplestilzken.mmpi.ui.controller;

import com.rumplestilzken.Main;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    @FXML
    AnchorPane mainActionPane;

    @FXML
    MenuItem quitItem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void nextAction(ActionEvent e) {
        mainActionPane.getChildren().clear();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/question_frame.fxml"));
            mainActionPane.getChildren().add(root);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    private void fileAction(ActionEvent e) {

    }

    @FXML
    private void quitAction(ActionEvent e){
        Platform.exit();
    }

}
