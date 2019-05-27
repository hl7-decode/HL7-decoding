package com.hl7.sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        String resource = "view-config/sample.fxml";
        Parent root = FXMLLoader.load(Main.class.getClassLoader().getResource(resource));
        primaryStage.setTitle("HL7 数据服务端");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    @Override
    public void stop(){
        System.exit(0);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
