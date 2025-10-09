package com.uts.controllers; 

import com.uts.model.Member;
import com.uts.model.Book;
import com.uts.model.TransactionRecord;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDate;

public class TransactionsController {

    private static final int MAX_BORROWED_BOOKS = 3;

    @FXML private ComboBox<Member> cbMember;
    @FXML private ComboBox<Book> cbBook;
    @FXML private TableView<TransactionRecord> tableTransactions;
    
    @FXML private TableColumn<TransactionRecord, String> colMember;
    @FXML private TableColumn<TransactionRecord, String> colBook;
    @FXML private TableColumn<TransactionRecord, String> colDate;

    private ObservableList<TransactionRecord> transactionList = FXCollections.observableArrayList();
    
    private ObservableList<Member> allMembers;
    private ObservableList<Book> allBooks;

    @FXML
    public void initialize() {

        cbMember.setCellFactory(_ -> new ListCell<Member>() {
            @Override
            protected void updateItem(Member item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "Pilih Anggota" : item.getName() + " (" + item.getId() + ")");
            }
        });
        cbBook.setCellFactory(_ -> new ListCell<Book>() {
            @Override
            protected void updateItem(Book item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "Pilih Buku" : item.getTitle() + " (" + item.getId() + ")");
            }
        });
        
        colMember.setCellValueFactory(cellData -> cellData.getValue().memberNameProperty());
        colBook.setCellValueFactory(cellData -> cellData.getValue().bookTitleProperty());
        colDate.setCellValueFactory(cellData -> cellData.getValue().dateStringProperty());

        tableTransactions.setItems(transactionList);
    }
    
    public void setExternalData(ObservableList<Member> members, ObservableList<Book> books) {
        this.allMembers = members;
        this.allBooks = books;
        
        cbMember.setItems(this.allMembers);
        cbBook.setItems(this.allBooks);
        
    }

    @FXML
    public void onBorrow() {
        Member selectedMember = cbMember.getSelectionModel().getSelectedItem();
        Book selectedBook = cbBook.getSelectionModel().getSelectedItem();

        if (selectedMember == null || selectedBook == null) {
            showError("Pilih Anggota dan Buku terlebih dahulu.");
            return;
        }

        long currentBorrows = transactionList.stream()
            .filter(tr -> tr.getMember().getId().equals(selectedMember.getId()))
            .count();

        if (currentBorrows >= MAX_BORROWED_BOOKS) {
            showError("Anggota " + selectedMember.getName() + 
                    " telah mencapai batas pinjaman maksimal (" + MAX_BORROWED_BOOKS + " buku).");
            return;
        }

        boolean alreadyBorrowed = transactionList.stream()
            .anyMatch(tr -> tr.getMember().getId().equals(selectedMember.getId()) && 
                        tr.getBook().getId().equals(selectedBook.getId()));

        if (alreadyBorrowed) {
            showError("Anggota " + selectedMember.getName() + 
                    " sudah meminjam buku '" + selectedBook.getTitle() + "'.");
            return;
        }
    
        LocalDate borrowDate = LocalDate.now();
        TransactionRecord newRecord = new TransactionRecord(selectedMember, selectedBook, borrowDate);
        transactionList.add(newRecord);

        showInfo("Peminjaman berhasil!", 
                "Anggota " + selectedMember.getName() + " meminjam buku " + selectedBook.getTitle());
        cbMember.getSelectionModel().clearSelection();
        cbBook.getSelectionModel().clearSelection();
    }
    
    private void showError(String msg) {
        showAlert(Alert.AlertType.ERROR, "Kesalahan Peminjaman", msg);
    }

    private void showInfo(String title, String message) {
        showAlert(Alert.AlertType.INFORMATION, title, message);
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}