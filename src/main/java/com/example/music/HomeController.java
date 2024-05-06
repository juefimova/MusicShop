package com.example.music;

import DBConnection.DBHandler;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    private DBHandler dbHandler;
    private Connection connection;
    private PreparedStatement pst;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dbHandler = new DBHandler();
    }
    @FXML
    private AnchorPane holderPane;
    AnchorPane root;

    private void createPage() {
        try {
            root = FXMLLoader.load(getClass().getResource("/com/example/music/StringInfo.fxml"));
            StringInfo stringInfo = Context.getInstance().getStringInfo();
            String select = "SELECT * FROM InstrumentsInfo";
            connection = dbHandler.getDbConnection();
            try {
                pst = connection.prepareStatement(select);
                ResultSet r = pst.executeQuery();

                while (r.next()) {

                    String path = r.getString("Pic");
                    System.out.println("pic = "+path);
                    String group = r.getString("Group");
                    String description = r.getString("Description");
                    stringInfo.create(description,path);
                }
            } catch (SQLException e) {
                e.printStackTrace();

            }

            stringInfo.createList();
            setNode(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void StringClicked(ActionEvent actionEvent) throws SQLException {

        createPage();
    }

    private void setNode (Node node){
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }


    StringInfo stringInfo;

}