package com.gudang.controller;

import com.gudang.model.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TambahCustomerController {

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNama;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtTelepon;

    private Stage dialogStage;
    private Customer newCustomer; 
    private boolean isSimpanClicked = false; 
    
    private Customer customerToEdit;


    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Customer getNewCustomer() {
        return newCustomer;
    }

    public boolean isSimpanClicked() {
        return isSimpanClicked;
    }

    public void setCustomer(Customer customer) {
        this.customerToEdit = customer;
        
        if (customer != null) {
            txtId.setText(customer.getIdCustomer());
            txtNama.setText(customer.getNamaCustomer());
            txtEmail.setText(customer.getEmail());
            txtTelepon.setText(customer.getNomorTelepon());
            
            txtId.setEditable(false); 
            dialogStage.setTitle("Edit Customer"); 
        } else {
            txtId.setEditable(true);
            dialogStage.setTitle("Tambah Customer"); 
        }
    }
    

    @FXML
    private void handleSimpan() {
        if (isInputValid()) {
            
            if (customerToEdit != null) {
                customerToEdit.setNamaCustomer(txtNama.getText());
                customerToEdit.setEmail(txtEmail.getText());
                customerToEdit.setNomorTelepon(txtTelepon.getText());
                
                newCustomer = customerToEdit; 
            } else {
                newCustomer = new Customer(
                    txtId.getText(),
                    txtNama.getText(),
                    txtEmail.getText(),
                    txtTelepon.getText() 
                );
            }

            isSimpanClicked = true;
            dialogStage.close(); 
        }
    }


    private boolean isInputValid() {
        String id = txtId.getText();
        String nama = txtNama.getText();
        String email = txtEmail.getText();
        
        String errorMessage = "";

        if (id == null || id.isEmpty() || nama == null || nama.isEmpty() || email == null || email.isEmpty()) {
            errorMessage += "Field Id, Nama, dan Email wajib diisi!\n";
        }
        
        if (!email.isEmpty() && !email.contains("@")) {
            errorMessage += "Email tidak valid.\n";
        }

        if (errorMessage.isEmpty()) {
            return true;
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Error", errorMessage);
            return false;
        }
    }

    private void showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}