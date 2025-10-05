package com.gudang.controller;

import com.gudang.model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerController {

    @FXML
    private TableView<Customer> customerTable;

    @FXML
    private TableColumn<Customer, String> colEmail; 

    @FXML
    private TableColumn<Customer, String> colId; 

    @FXML
    private TableColumn<Customer, String> colNama; 

    @FXML
    private TableColumn<Customer, String> colTelepon; 

    private ObservableList<Customer> customerData;

    @FXML
    public void initialize() {
        customerData = FXCollections.observableArrayList();

        colId.setCellValueFactory(new PropertyValueFactory<>("idCustomer"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("namaCustomer"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTelepon.setCellValueFactory(new PropertyValueFactory<>("nomorTelepon"));

        customerTable.setItems(customerData);
    }
    
    @FXML
    void tambahCustomer(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TambahCustomer.fxml"));
            VBox page = loader.load();
            TambahCustomerController controller = loader.getController();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Tambah Customer");
            dialogStage.initModality(Modality.WINDOW_MODAL); 
            
            Stage primaryStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            dialogStage.initOwner(primaryStage);
            
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            controller.setDialogStage(dialogStage);

            dialogStage.showAndWait();

            if (controller.isSimpanClicked()) {
                Customer newCustomer = controller.getNewCustomer();

                if (isIdExist(newCustomer.getIdCustomer())) {
                    showAlert(AlertType.ERROR, "Error", "ID Customer Ganda", "ID customer sudah ada!");
                    return; 
                }
                
                customerData.add(newCustomer);
                showAlert(AlertType.INFORMATION, "Sukses", "Data Ditambahkan", "Customer " + newCustomer.getNamaCustomer() + " berhasil ditambahkan!");
            }

        } catch (IOException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Error", "Gagal Memuat Form", "Tidak dapat memuat TambahCustomer.fxml.");
        }
    }

    private boolean isIdExist(String id) {
        for (Customer customer : customerData) {
            if (customer.getIdCustomer().equals(id)) {
                return true;
            }
        }
        return false;
    }

    private void showAlert(AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    @FXML
    void editCustomer(ActionEvent event) {
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();

        if (selectedCustomer != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EditCustomer.fxml"));
                VBox page = loader.load();
                TambahCustomerController controller = loader.getController();

                Stage dialogStage = new Stage();
                dialogStage.setTitle("Edit Customer");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                Stage primaryStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                dialogStage.initOwner(primaryStage);
                dialogStage.setScene(new Scene(page));

                controller.setDialogStage(dialogStage);
                controller.setCustomer(selectedCustomer); 
                dialogStage.showAndWait();

                if (controller.isSimpanClicked()) {
                    customerTable.refresh(); 
                    
                    showAlert(AlertType.INFORMATION, "Sukses", "Data Diperbarui", "Customer " + selectedCustomer.getNamaCustomer() + " berhasil diperbarui!");
                }

            } catch (IOException e) {
                e.printStackTrace();
                showAlert(AlertType.ERROR, "Error", "Gagal Memuat Form", "Tidak dapat memuat TambahCustomer.fxml untuk Edit.");
            }
        } else {
            // Tampilkan peringatan jika tidak ada baris yang dipilih
            showAlert(AlertType.WARNING, "Peringatan", "Tidak Ada Customer Dipilih", "Pilih customer dari tabel untuk diedit.");
        }
    }

    @FXML
    void hapusCustomer(ActionEvent event) {
        int selectedIndex = customerTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            Alert confirmation = new Alert(AlertType.CONFIRMATION);
            confirmation.setTitle("Konfirmasi Hapus");
            confirmation.setHeaderText("Hapus Customer");
            confirmation.setContentText("Apakah Anda yakin ingin menghapus customer " + customerTable.getSelectionModel().getSelectedItem().getNamaCustomer() + "?");
            
            confirmation.showAndWait().ifPresent(response -> {
                if (response == javafx.scene.control.ButtonType.OK) {
                    customerData.remove(selectedIndex);
                    showAlert(AlertType.INFORMATION, "Sukses", "Data Dihapus", "Customer berhasil dihapus.");
                }
            });
        } else {
            showAlert(AlertType.WARNING, "Peringatan", "Tidak Ada Customer Dipilih", "Pilih customer dari tabel untuk dihapus.");
        }
    }

}