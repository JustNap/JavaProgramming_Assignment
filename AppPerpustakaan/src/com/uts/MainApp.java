package com.uts;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent; 

import com.uts.controllers.BooksController;
import com.uts.controllers.MembersController;
import com.uts.controllers.TransactionsController;
import com.uts.controllers.MainController; 

public class MainApp extends Application{
    private BooksController booksControllerInstance;
    private MembersController membersControllerInstance;
    private TransactionsController transactionsControllerInstance;

    private Parent booksView;
    private Parent membersView;
    private Parent transactionsView;
    
    private MainController mainController; 

    @Override
    public void start(Stage primaryStage) {
        try {
            loadAllViewsAndControllers(); // Method ini menginisialisasi mainController
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Main.fxml"));
            
            loader.setController(mainController); 

            Scene scene = new Scene(loader.load()); 

            scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

            primaryStage.setScene(scene);
            primaryStage.setTitle("Sistem Manajemen Perpustakaan Mini");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAllViewsAndControllers() throws IOException {
        
        FXMLLoader memberLoader = new FXMLLoader(getClass().getResource("/view/Members.fxml"));
        membersView = memberLoader.load(); // Simpan View
        membersControllerInstance = memberLoader.getController();

        FXMLLoader bookLoader = new FXMLLoader(getClass().getResource("/view/Books.fxml"));
        booksView = bookLoader.load(); // Simpan View
        booksControllerInstance = bookLoader.getController();


        FXMLLoader transLoader = new FXMLLoader(getClass().getResource("/view/Transactions.fxml"));
        transactionsView = transLoader.load(); // Simpan View
        transactionsControllerInstance = transLoader.getController();
        
        transactionsControllerInstance.setExternalData(
            membersControllerInstance.getMemberList(),
            booksControllerInstance.getBookList()
        );
        
        System.out.println("Data List berhasil di-injeksi ke TransactionsController.");
        
        mainController = new MainController(); 
        mainController.setAllControllers(
            booksControllerInstance, membersControllerInstance, transactionsControllerInstance,
            booksView, membersView, transactionsView
        );
    }

    public static void main(String[] args) {
        launch(args);
    }
}