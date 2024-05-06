package com.example.music;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ErrorController implements Initializable {
    @FXML
    Button OK;
    @FXML
    Label message;


    public Label getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message.setText(message);
    }

    public void btnOKClicked(MouseEvent mouseEvent) {
        message.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Context.getInstance().setErrorController(this);
    }
}
