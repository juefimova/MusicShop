package com.example.music;

import DBConnection.DBHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginMainController implements Initializable {
    @FXML
    private Label loginLb;
    @FXML
    private TextField password;
    @FXML
    private TextField email;
    @FXML
    private Button btnLog;
    @FXML
    private Button SignUp;
    @FXML
    private Button forgot;
    @FXML
    MenuButton menu;

    public TextField getEmail() {
        return email;
    }

    public void setEmail(TextField email) {
        this.email = email;
    }

    public void SignUpClick(ActionEvent actionEvent) {
        try {
            Stage login = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
            Scene scene = new Scene(root);
            login.setScene(scene);
            login.show();
            login.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private DBHandler dbHandler;
    private Connection connection;
    private PreparedStatement pst;
    Image imv;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbHandler = new DBHandler();
        connection = dbHandler.getDbConnection();
        Context.getInstance().setLoginMainController(this);
    }

    public void btnLogClick(ActionEvent actionEvent) {

        String emailTxt = email.getText();
        String select = "SELECT * FROM personInfo";

        try {
            pst = connection.prepareStatement(select);
            ResultSet r = pst.executeQuery();

            while (r.next()) {
                String k = r.getString(5);
                System.out.println(k + emailTxt);

                if (emailTxt.equals(k)) {
                    System.out.println("Вы ввели верную почту!!");

                    String i = r.getString("Password");
                    String p = r.getString("Path");
                    System.out.println("password = " + i + " path = " + p);


                    URL u = LoginMainController.class.getResource(p);
                    try {
                        imv = new Image("file:///" + Paths.get(u.toURI()).toFile().getAbsolutePath());
                    } catch(NullPointerException np) {
                        File f = new File(p);

                        imv = new Image("file:///" + Paths.get(f.getAbsolutePath()));
                        System.out.println(imv);
                    }




                    if (password.getText().equals(i)) {
                        System.out.println("Вы успешно вошли в систему!!!!!");
                        try {
                            Stage login = new Stage();
                            Parent root = FXMLLoader.load(getClass().getResource("/com/example/music/Homepage.fxml"));
                            homepageController = Context.getInstance().getHomepageController();
                            homepageController.getCircleHome().setFill(new ImagePattern(imv));
                            Scene scene = new Scene(root);
                            login.setScene(scene);
                            login.show();
                            login.setResizable(false);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        errors.setMessage("Check the entered data");
                        errors.call();
                    }


                    break;
                }

            }
            connection.close();


        } catch (SQLException | URISyntaxException throwables) {
            throwables.printStackTrace();
        }



    }
    Errors errors = new Errors();
    HomepageController homepageController = new HomepageController();

    public void forgotClicked(ActionEvent actionEvent) {
        try {
            Stage login = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("ForgotPassword.fxml"));
            Scene scene = new Scene(root);
            login.setScene(scene);
            login.show();
            login.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    Res manager;
    Locale rus = new Locale("ru", "RU");
    Locale en = new Locale("en", "EN");

    public void engSelected(ActionEvent actionEvent) {
        ResourceBundle rb = ResourceBundle.getBundle(Res.INSTANCE.getResourseName(), en);
        Locale.setDefault(Locale.ENGLISH);
        email.setText(rb.getString("TextField.email"));
        password.setText(rb.getString("TextField.password"));
        btnLog.setText(rb.getString("Button.btnLog"));
        SignUp.setText(rb.getString("Button.SignUp"));
        menu.setText(rb.getString("MenuButton.menu"));
        loginLb.setText(rb.getString("Label.loginLb"));
        forgot.setText(rb.getString("Button.forgot"));
    }

    public void rusSelected(ActionEvent actionEvent) {
        ResourceBundle rb = ResourceBundle.getBundle(Res.INSTANCE.getResourseName(), rus);
        Locale.setDefault(rus);
        email.setText(rb.getString("TextField.email"));
        password.setText(rb.getString("TextField.password"));
        btnLog.setText(rb.getString("Button.btnLog"));
        SignUp.setText(rb.getString("Button.SignUp"));
        menu.setText(rb.getString("MenuButton.menu"));
        loginLb.setText(rb.getString("Label.loginLb"));
        forgot.setText(rb.getString("Button.forgot"));
    }
}
