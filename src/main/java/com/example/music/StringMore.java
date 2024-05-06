package com.example.music;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class StringMore implements Initializable {
    @FXML
    private Label lbMore;
    public Label getLbMore() {
        return lbMore;
    }

    public void setLbMore(Label lbMore) {
        this.lbMore = lbMore;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Context.getInstance().setStringMore(this);
    }
}
