package com.rumplestilzken.mmpi.ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class QuestionFrameController implements Initializable {

    @FXML
    ToggleGroup sexToggleGroup;

    @FXML
    ToggleGroup formToggleGroup;

    @FXML
    RadioButton maleRadioButton;

    @FXML
    RadioButton femaleRadioButton;

    @FXML
    RadioButton shortFormRadioButton;

    @FXML
    RadioButton longFormRadioButtion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       Toggle t = sexToggleGroup.getSelectedToggle();
        System.out.println("Hello World.");
    }

    @FXML
    private void maleRadioButtonAction(ActionEvent e) {
        femaleRadioButton.setSelected(false);
    }

    @FXML
    private void femaleRadioButtonAction(ActionEvent e) {
        maleRadioButton.setSelected(false);
    }

    @FXML
    private void shortFormRadioButtonAction(ActionEvent e) {
        longFormRadioButtion.setSelected(false);
    }

    @FXML
    private void longFormRadioButtonAction(ActionEvent e) {
        shortFormRadioButton.setSelected(false);
    }
}
