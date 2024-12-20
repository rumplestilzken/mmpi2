package com.rumplestilzken;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main_window.fxml"));
        primaryStage.setTitle("MMPI-2");
        primaryStage.setScene(new Scene(root, 1024, 768));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.jpg")));
        primaryStage.show();
    }
}
