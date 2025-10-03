package com.gudang.controller;

import com.gudang.model.Barang;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.*;


public class BarangController {
    @FXML private TextField txtNama, txtJumlah;
    @FXML private TableView<Barang> tblBarang;
    @FXML private TableColumn<Barang, String> colNama;
    @FXML private TableColumn<Barang, Integer> colJumlah;
    
    private ObservableList<Barang> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colNama.setCellValueFactory(cell -> cell.getValue().namaProperty());
        colJumlah.setCellValueFactory(cell -> cell.getValue().jumlahProperty().asObject());
        tblBarang.setItems(data);
    }

    @FXML
    public void tambahBarang() {
        if (!txtNama.getText().isEmpty() && !txtJumlah.getText().isEmpty()) {
            try {
                data.add(new Barang(txtNama.getText(), Integer.parseInt(txtJumlah.getText())));
                txtNama.clear();
                txtJumlah.clear();
            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Kesalahan Input", "Jumlah harus berupa angka.");
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Input Kosong", "Nama Barang dan Jumlah tidak boleh kosong.");
        }
    }

    @FXML
    public void editBarang() {
        Barang selected = tblBarang.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setNama(txtNama.getText());
            selected.setJumlah(Integer.parseInt(txtJumlah.getText()));
            txtNama.clear();
            txtJumlah.clear();
            tblBarang.refresh();
        }else {
            showAlert(Alert.AlertType.WARNING, "Belum Ada Pilihan", "Pilih salah satu item barang dari tabel untuk diubah.");
        }
    }

    @FXML
    public void hapusBarang() {
        Barang selected = tblBarang.getSelectionModel().getSelectedItem();
        if (selected != null) {
            data.remove(selected);
        }else {
            showAlert(Alert.AlertType.WARNING, "Belum Ada Pilihan", "Pilih salah satu item barang dari tabel untuk dihapus.");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

