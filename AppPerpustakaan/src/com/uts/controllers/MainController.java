package com.uts.controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent; 

public class MainController {


    private BooksController booksControllerInstance;
    private MembersController membersControllerInstance;
    private TransactionsController transactionsControllerInstance;

    private Parent booksView;
    private Parent membersView;
    private Parent transactionsView;

    public void setAllControllers(
        BooksController bc, MembersController mc, TransactionsController tc,
        Parent bv, Parent mv, Parent tv) {
        
        this.booksControllerInstance = bc;
        this.membersControllerInstance = mc;
        this.transactionsControllerInstance = tc;
        
        this.booksView = bv;
        this.membersView = mv;
        this.transactionsView = tv;
    }

    @FXML
    private void openBooks() throws Exception {
        Stage stage = new Stage();
        Scene scene = new Scene(this.booksView); 
        stage.setScene(scene);
        stage.setTitle("Manajemen Buku");
        stage.show();
    }

    @FXML
    private void openMembers() throws Exception {
        Stage stage = new Stage();
        Scene scene = new Scene(this.membersView);
        stage.setScene(scene);
        stage.setTitle("Manajemen Anggota");
        stage.show();
    }

    @FXML
    private void openTransactions() throws Exception {
        Stage stage = new Stage();
        Scene scene = new Scene(this.transactionsView);
        stage.setScene(scene);
        stage.setTitle("Peminjaman Buku");
        stage.show();
    }

}