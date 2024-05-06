package com.example.music;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InstrumentsCell extends ListCell<Instruments> implements Initializable {
    @FXML
    private Button btnMore;
    @FXML
    private Label lbDescription;
    @FXML
    private ImageView imvString;

    public GridPane getRoot() {
        return root;
    }

    @FXML
    private GridPane root;
    private Instruments model;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateSelected(false);
        for (Node node : getRoot().getChildrenUnmodifiable()) {
            node.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (!newValue) {
                        commitEdit(model);
                    }
                }
            });
        }
        setGraphic(root);
    }


    public static InstrumentsCell newInstance() {
        FXMLLoader loader = new FXMLLoader(InstrumentsCell.class.getResource("/com/example/music/InstrumentsCell.fxml"));
        try {
            loader.load();
            return loader.getController();
        } catch (IOException ex) {
            return null;
        }
    }

    @Override
    public void commitEdit(Instruments newValue) {
        newValue = (newValue == null) ? this.model : newValue;
        super.commitEdit(newValue);
        newValue.setLbDescription(lbDescription.textProperty().get());
        newValue.setButtonText(btnMore.textProperty().get());
    }

    @Override
    protected void updateItem(Instruments item, boolean empty) {
        super.updateItem(item, empty);
        getRoot().getChildrenUnmodifiable().forEach(c -> c.setVisible(!empty));
        if (!empty && item != null && !item.equals(this.model)) {
            imvString.imageProperty().set(new Image(item.getImagePath()));
            System.out.println(item.getImagePath());
            btnMore.textProperty().set(item.getButtonText());
            lbDescription.textProperty().set(item.getLbDescription());
            btnMore.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent actionEvent) {
                    System.out.println("зашли");
                    try {
                        call(item.getLbMore());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

        }

        this.model = item;
    }

    @Override
    public void updateSelected(boolean selected) {
        super.updateSelected(selected);
        getRoot().getChildrenUnmodifiable().forEach(c -> {
            c.setMouseTransparent(!selected);
            c.setFocusTraversable(selected);
        });
        if (selected) {
            startEdit();
        } else {
            if (model != null) {
                commitEdit(model);
            }
        }
    }

    StringMore stringMore;

    public void call(String text) throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(StringMore.class.getResource("/com/example/music/StringMore.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stringMore = Context.getInstance().getStringMore();
        stringMore.getLbMore().setText(text);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
        stringMore = loader.getController();
        System.out.println("зашли1");
    }

}
