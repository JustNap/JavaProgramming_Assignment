package com.uts.controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private void openBooks() throws Exception {
        Stage stage = (Stage) new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Books.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Manajemen Buku");
        stage.show();
    }

    @FXML
    private void openMembers() throws Exception {
        Stage stage = (Stage) new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Members.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Manajemen Anggota");
        stage.show();
    }

    @FXML
    private void openTransactions() throws Exception {
        Stage stage = (Stage) new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Transactions.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Manajemen Buku");
        stage.show();
    }

}
