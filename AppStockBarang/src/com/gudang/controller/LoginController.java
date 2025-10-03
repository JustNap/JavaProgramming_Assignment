package com.gudang.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private Label lblStatus;

    @FXML
    private PasswordField txtPass;

    @FXML
    private TextField txtUser;

    @FXML
    void loginAction(ActionEvent event) {
        String user = txtUser.getText();
    String pass = txtPass.getText();

    if(user.equals("admin") && pass.equals("123")) {
        try {
            Stage stage = (Stage) txtUser.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Menu.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.setTitle("Menu Utama");
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    else {
        lblStatus.setText("Login gagal!");
    }
    }

}

