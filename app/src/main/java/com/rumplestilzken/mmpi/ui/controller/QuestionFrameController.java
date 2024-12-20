package com.rumplestilzken.mmpi.ui.controller;

import com.rumplestilzken.mmpi.data.QuestionData;
import com.rumplestilzken.mmpi.data.Storage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class QuestionFrameController implements Initializable {
    @FXML
    RadioButton maleRadioButton;

    @FXML
    RadioButton femaleRadioButton;

    @FXML
    RadioButton shortFormRadioButton;

    @FXML
    RadioButton longFormRadioButton;

    @FXML
    VBox questionVBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private void maleRadioButtonAction(ActionEvent e) {
        Storage.setIsMale(true);
        femaleRadioButton.setSelected(false);
    }

    @FXML
    private void femaleRadioButtonAction(ActionEvent e) {
        Storage.setIsMale(false);
        maleRadioButton.setSelected(false);
    }

    @FXML
    private void shortFormRadioButtonAction(ActionEvent e) {
        Storage.setIsLong(false);
        longFormRadioButton.setSelected(false);
        questionVBox.getChildren().clear();
        for(QuestionData.Question q : new QuestionData().getShortFormQuestions()){
            try {
                VBox root = getScrollItemFromQuestion(q);
                questionVBox.getChildren().add(root);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @FXML
    private void longFormRadioButtonAction(ActionEvent e) {
        Storage.setIsLong(true);
        shortFormRadioButton.setSelected(false);
        questionVBox.getChildren().clear();
        for(QuestionData.Question q : new QuestionData().getQuestions()){
            try {
                VBox root = getScrollItemFromQuestion(q);
                questionVBox.getChildren().add(root);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private VBox getScrollItemFromQuestion(QuestionData.Question q) throws IOException {
        VBox root = FXMLLoader.load(getClass().getResource("/fxml/question_item.fxml"));
        root.setId("VBox_question_" + q.getIndex());
        AnchorPane qAPane = (AnchorPane) root.getChildren().get(0);
        TextArea text = (TextArea) qAPane.getChildren().stream().filter(i -> i instanceof TextArea).findFirst().get();
        text.setText(q.getIndex() + "." + q.getText());
        TilePane tPane = (TilePane)qAPane.getChildren().stream().filter(i -> i instanceof TilePane).findFirst().get();
        ToggleGroup tGroup = new ToggleGroup();
        Stream<Node> radioButtonsStream = tPane.getChildren().stream().filter(i -> i instanceof RadioButton);
        List<RadioButton> radioButtonsList = radioButtonsStream.map(r -> (RadioButton)r).collect(Collectors.toList());
        RadioButton tRadioButton = radioButtonsList.stream().filter(i -> i.getId().equals("trueRadioButton")).findFirst().get();
        RadioButton uaRadioButton = radioButtonsList.stream().filter(i -> i.getId().equals("unansweredRadioButton")).findFirst().get();
        RadioButton fRadioButton = radioButtonsList.stream().filter(i -> i.getId().equals("falseRadioButton")).findFirst().get();
        tRadioButton.setToggleGroup(tGroup);
        uaRadioButton.setToggleGroup(tGroup);
        fRadioButton.setToggleGroup(tGroup);
        tGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if(tRadioButton.isSelected()){
                    fRadioButton.setSelected(false);
                    uaRadioButton.setSelected(false);
                }
                if(fRadioButton.isSelected())
                {
                    tRadioButton.setSelected(false);
                    uaRadioButton.setSelected(false);
                }
                if(uaRadioButton.isSelected()){
                    tRadioButton.setSelected(false);
                    fRadioButton.setSelected(false);
                }
            }
        });
        return root;
    }

}
