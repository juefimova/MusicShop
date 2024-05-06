package com.example.music;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class ForgotPasswordController implements Initializable {

    @FXML
    private TextField forgotEmail;

    public TextField getForgotEmail() {
        return forgotEmail;
    }

    public void setForgotEmail(TextField forgotEmail) {
        this.forgotEmail = forgotEmail;
    }



    public void BtnOKClicked(ActionEvent actionEvent) {
        Mailer mailer = new Mailer();
        mailer.forgotPassword();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Context.getInstance().setForgotPasswordController(this);
    }
}

