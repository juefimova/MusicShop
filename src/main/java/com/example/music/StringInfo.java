package com.example.music;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class StringInfo implements Initializable {
    HomeController homeController;
    @FXML
    private ListView<Instruments> lvStringInfo;
    private ObservableList<Instruments> lvInstruments = FXCollections.observableArrayList();
    Instruments[] instruments = new Instruments[10];
    int i = 0;
    public void create(String description, String path) {
        instruments[i] = new Instruments();
        instruments[i].setButtonText("More");
        instruments[i].setLbDescription(description);
        String path_ = null;
        URL u = LoginMainController.class.getResource(path);
        try {
            path_ = "file:///" + Paths.get(u.toURI()).toFile().getAbsolutePath();
        } catch(NullPointerException | URISyntaxException np) {
            File f = new File(description);

            path_ ="file:///" + Paths.get(f.getAbsolutePath());

        }

        instruments[i].setImagePath(path_);
        instruments[i].setLbMore(description);
        i++;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("here");
        Context.getInstance().setStringInfo(this);
        lvStringInfo.setCellFactory(new Callback<ListView<Instruments>, ListCell<Instruments>>() {
            @Override
            public ListCell<Instruments> call(ListView<Instruments> param) {
                return InstrumentsCell.newInstance();
            }
        });




        System.out.println("len = " + instruments.length + instruments[0]);

    }

    public void createList() {
        lvInstruments.addAll(instruments);
        lvStringInfo.setItems(lvInstruments);
    }
}
