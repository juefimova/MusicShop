package com.example.music;

import DBConnection.DBHandler;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomepageController implements Initializable {
    @FXML
    private Circle circleHome;

    public Circle getCircleHome() {
        return circleHome;
    }

    public void setCircleHome(Circle circleHome) {
        this.circleHome = circleHome;
    }

    @FXML
    private MenuButton menuButton;
    @FXML
    private Button contact;
    @FXML
    private Button btnHome;
    @FXML
    private ImageView ImageView;
    @FXML
    AnchorPane holderPane;
    AnchorPane home;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createPage();
        menuButton.setGraphic(ImageView);
        Context.getInstance().setHomepageController(this);
        dbHandler = new DBHandler();
        connection = dbHandler.getDbConnection();
    }

    private DBHandler dbHandler;
    private Connection connection;
    private PreparedStatement pst;
    private void createPage() {
        try {
            home = FXMLLoader.load(getClass().getResource("/com/example/music/Home.fxml"));
            setNode(home);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node)node);
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    LoginMainController loginMainController;

    public void UpdateInfo(String path) {
        loginMainController = Context.getInstance().getLoginMainController();
        String emailTxt = loginMainController.getEmail().getText();
        String update = "UPDATE personInfo SET Path=\"" + path + "\" WHERE Email=\"" + emailTxt + "\"";
        try {
            pst = connection.prepareStatement(update);
            pst.executeUpdate();
            System.out.println("Запись в бд home");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void ChoosePic(MouseEvent mouseEvent) throws SQLException {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(circleHome.getScene().getWindow());
        String path = file.getAbsolutePath();
        ImageView imv = new ImageView("file:\\" + path);
        circleHome.setFill(new ImagePattern(imv.getImage()));
        UpdateInfo(path);
    }

    public void toLogin(ActionEvent actionEvent) {
        try {
            Stage login = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/music/LoginMain.fxml"));


            Scene scene = new Scene(root);
            login.setScene(scene);
            login.show();
            login.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnHomeClick(MouseEvent mouseEvent) {
        Duration duration = Duration.millis(2500);
        TranslateTransition translateTransition = new TranslateTransition(duration, btnHome);
        translateTransition.setByY(btnHome.getLayoutY() + 100);
        translateTransition.setByX(btnHome.getLayoutX() + 100);
        translateTransition.setAutoReverse(true);
        translateTransition.setCycleCount(2);
        translateTransition.play();
    }

    public void ConnectClick(MouseEvent mouseEvent) {
        Duration duration = Duration.millis(1500);
        RotateTransition rotateTransition = new RotateTransition(duration, contact);
        rotateTransition.setByAngle(360);
        rotateTransition.play();
    }

    public void toExit(ActionEvent actionEvent) {
        System.exit(0);
    }
}


