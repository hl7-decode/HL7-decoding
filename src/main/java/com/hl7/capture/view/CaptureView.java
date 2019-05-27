package com.hl7.capture.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CaptureView extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        String resource = "view-config/capture.fxml";
        Parent root = FXMLLoader.load(CaptureView.class.getClassLoader().getResource(resource));
        primaryStage.setTitle("HL7 数据嗅探");
        primaryStage.setScene(new Scene(root, 931, 618));
        primaryStage.show();
    }

    @Override
    public void stop(){
        System.exit(0);
    }
}
