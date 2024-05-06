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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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

public class SignUpController implements Initializable {
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPassword;
    @FXML
    private Button btnSign;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfSurname;
    @FXML
    private TextField tfNumber;
    @FXML
    private TextField tfRepeat;
    @FXML
    private Label signLb;


    private DBHandler dbHandler;
    private Connection connection;
    private PreparedStatement pst;


    Res manager;
    Locale current = Locale.getDefault();
    Locale rus = new Locale("ru", "RU");
    Locale en = new Locale("en", "EN");

    @FXML
    public void selectLanguage() {


        if(Locale.getDefault()==Locale.ENGLISH) {
            ResourceBundle rb = ResourceBundle.getBundle(Res.INSTANCE.getResourseName(), en);
            tfEmail.setText(rb.getString("TextField.tfEmail"));
            tfPassword.setText(rb.getString("TextField.tfPassword"));
            btnSign.setText(rb.getString("Button.btnSign"));
            tfName.setText(rb.getString("TextField.tfName"));
            tfSurname.setText(rb.getString("TextField.tfSurname"));
            tfNumber.setText(rb.getString("TextField.tfNumber"));
            tfRepeat.setText(rb.getString("TextField.tfRepeat"));
            signLb.setText(rb.getString("Label.signLb"));
        }
        if(Locale.getDefault()==rus) {
            ResourceBundle rb = ResourceBundle.getBundle(Res.INSTANCE.getResourseName(), rus);
            tfEmail.setText(rb.getString("TextField.tfEmail"));
            tfPassword.setText(rb.getString("TextField.tfPassword"));
            btnSign.setText(rb.getString("Button.btnSign"));
            tfName.setText(rb.getString("TextField.tfName"));
            tfSurname.setText(rb.getString("TextField.tfSurname"));
            tfNumber.setText(rb.getString("TextField.tfNumber"));
            tfRepeat.setText(rb.getString("TextField.tfRepeat"));
            signLb.setText(rb.getString("Label.signLb"));
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Locale.setDefault(en);
        selectLanguage();
        dbHandler = new DBHandler();
    }





    public void btnSignUpClick(ActionEvent actionEvent) {
        boolean flag = true;
        String select = "SELECT * FROM personInfo";
        connection = dbHandler.getDbConnection();
        try {
            pst = connection.prepareStatement(select);
            ResultSet r = pst.executeQuery();

            while (r.next()) {
                String k = r.getString(5);
                System.out.println(k + tfEmail.getText());



                if (tfEmail.getText().equals(k)) {
                    errors.setMessage("You've already used this email");
                    errors.call();
                    flag = false;
                    break;
                }
            }
            if (flag == true) {

                String insert = "INSERT INTO personInfo(Name, Surname, PhoneNumber, Email, Password, Path) Values (?,?,?,?,?,?)";
                connection = dbHandler.getDbConnection();
                try {
                    pst = connection.prepareStatement(insert);

                    if (tfPassword.getText().equals(tfRepeat.getText())) {
                        pst.setString(5, tfPassword.getText());

                        pst.setString(1, tfName.getText());
                        pst.setString(2, tfSurname.getText());
                        pst.setString(3, tfNumber.getText());
                        pst.setString(4, tfEmail.getText());
                        pst.setString(6,"/Images/profilePic.png");


                        if (tfName.getText().isEmpty() || tfSurname.getText().isEmpty() || tfNumber.getText().isEmpty() || tfEmail.getText().isEmpty() || tfPassword.getText().isEmpty() || tfRepeat.getText().isEmpty()) {
                            errors.setMessage("Check the entered data");
                            errors.call();
                        } else {
                            pst.executeUpdate();
                            System.out.println("Запись в бд");
                        }

                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

        Errors errors = new Errors();
    }
