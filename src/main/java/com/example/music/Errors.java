package com.example.music;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Errors {
    String message;



    ErrorController errorController;
    boolean flag = true;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void call() {
        Stage stage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Error.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 400, 340);
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setScene(scene);
        stage.setTitle("Oops");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setMaximized(false);
        stage.show();
        errorController = Context.getInstance().getErrorController();
        errorController.setMessage(this.message);
        flag = false;
    }
}
