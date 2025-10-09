package com.uts.controllers; 

import com.uts.model.Book;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BooksController {
    @FXML private TextField tfId;
    @FXML private TextField tfTitle;
    @FXML private TextField tfAuthor;
    @FXML private TextField tfYear;
    @FXML private Button btnAdd;

    @FXML private TableView<Book> tableBooks;
    @FXML private TableColumn<Book, String> colId;
    @FXML private TableColumn<Book, String> colTitle;
    @FXML private TableColumn<Book, String> colAuthor;
    @FXML private TableColumn<Book, Integer> colYear;

    private ObservableList<Book> bookList = FXCollections.observableArrayList();
    private Book editing = null;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        colTitle.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        colAuthor.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        colYear.setCellValueFactory(cellData -> cellData.getValue().yearProperty().asObject()); 

        tableBooks.setItems(bookList);
    }
    @FXML
    public void onAddOrUpdate() {
        String id = tfId.getText().trim();
        String title = tfTitle.getText().trim();
        String author = tfAuthor.getText().trim();
        String yearText = tfYear.getText().trim();
        
        if (id.isEmpty() || title.isEmpty()) {
            showError("ID Buku dan Judul Buku wajib diisi.");
            return;
        }

        try {
            Integer year = null;
            if (!yearText.isEmpty()) {
                year = Integer.parseInt(yearText);
            }
            
            if (editing == null) {
                if (findBookById(id) != null) {
                    showError("Buku dengan ID '" + id + "' sudah ada.");
                    return;
                }
                
                Book newBook = new Book(id, title, author, year);
                bookList.add(newBook);
                
            } else {
                editing.setTitle(title);
                editing.setAuthor(author);
                editing.setYear(year != null ? year : 0);
                
                tableBooks.refresh(); 
                
                editing = null; 
                btnAdd.setText("Tambah");
                tfId.setDisable(false);
            }
            
            clearForm();
            tableBooks.getSelectionModel().clearSelection();
            
        } catch (NumberFormatException e) {
            showError("Tahun Terbit harus berupa angka.");
        }
    }

    @FXML
    public void onEdit() {
        Book selected = tableBooks.getSelectionModel().getSelectedItem();
        if (selected != null) {
            editing = selected;
            
            tfId.setText(selected.getId());
            tfTitle.setText(selected.getTitle());
            tfAuthor.setText(selected.getAuthor());
            tfYear.setText(selected.getYear() > 0 ? String.valueOf(selected.getYear()) : "");
            
            tfId.setDisable(true); 
            btnAdd.setText("Update");
            
        } else {
            showAlert(Alert.AlertType.WARNING, "Belum Ada Pilihan", "Pilih salah satu buku dari tabel untuk diubah.");
        }
    }

    @FXML
    public void onDelete() {
        Book selected = tableBooks.getSelectionModel().getSelectedItem();
        if (selected != null) {
            bookList.remove(selected);
            clearForm(); 
        } else {
            showAlert(Alert.AlertType.WARNING, "Belum Ada Pilihan", "Pilih salah satu buku dari tabel untuk dihapus.");
        }
    }

    @FXML 
    public void clearForm() {
        tfId.clear();
        tfTitle.clear();
        tfAuthor.clear();
        tfYear.clear();
        tfId.setDisable(false); 
        editing = null;
        btnAdd.setText("Tambah");
        tableBooks.getSelectionModel().clearSelection();
    }
    
    private void showError(String msg) {
        showAlert(Alert.AlertType.ERROR, "Kesalahan Input", msg);
    }
    

    public ObservableList<Book> getBookList() {
        return bookList;
    }
    

    private Book findBookById(String id) {
        for (Book book : bookList) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null;
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}