package com.example.music;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SplashScreen.fxml"));
        try {
            System.out.println("попали");
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            scene.setFill(Color.TRANSPARENT);
            stage.show();
            PauseTransition delay = new PauseTransition(Duration.seconds(5));
            delay.setOnFinished(actionEvent -> {
                stage.close();
                try {
                    programLaunch();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            delay.play();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public void programLaunch() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoginMain.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 740, 580);
        stage.setMaxWidth(740);
        stage.setMaxHeight(580);
        stage.setMinWidth(740);
        stage.setMinHeight(580);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}