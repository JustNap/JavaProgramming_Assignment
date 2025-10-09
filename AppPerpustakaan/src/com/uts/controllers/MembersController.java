package com.uts.controllers; 

import com.uts.model.Member; 

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MembersController {

    @FXML private TextField tfId;
    @FXML private TextField tfName;
    @FXML private TextField tfAddress;
    @FXML private TextField tfPhone;
    @FXML private Button btnAdd;

    @FXML private TableView<Member> tableMembers;
    @FXML private TableColumn<Member, String> colId;
    @FXML private TableColumn<Member, String> colName;
    @FXML private TableColumn<Member, String> colAddress;
    @FXML private TableColumn<Member, String> colPhone;


    private ObservableList<Member> memberList = FXCollections.observableArrayList();
    private Member editing = null;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        colName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        colAddress.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        colPhone.setCellValueFactory(cellData -> cellData.getValue().phoneProperty()); 

        tableMembers.setItems(memberList);
    }

    @FXML
    public void onAddOrUpdate() {
        String id = tfId.getText().trim();
        String name = tfName.getText().trim();
        String address = tfAddress.getText().trim(); 
        String phone = tfPhone.getText().trim();
        
        if (id.isEmpty() || name.isEmpty() || phone.isEmpty()) {
            showError("ID Anggota, Nama, dan Nomor Telepon wajib diisi.");
            return;
        }
        
        if (!phone.matches("\\d+")) {
            showError("Nomor Telepon hanya boleh mengandung angka.");
            return;
        }

        if (editing == null) {
            if (findMemberById(id) != null) {
                showError("Anggota dengan ID '" + id + "' sudah terdaftar.");
                return;
            }
            
            Member newMember = new Member(id, name, address, phone);
            memberList.add(newMember);
            
        } else {
            editing.setName(name);
            editing.setAddress(address);
            editing.setPhone(phone);
            
            tableMembers.refresh();
        }
        
        clearForm();
    }

    @FXML
    public void onEdit() {
        Member selected = tableMembers.getSelectionModel().getSelectedItem();
        if (selected != null) {
            editing = selected;
            
            tfId.setText(selected.getId());
            tfName.setText(selected.getName());
            tfAddress.setText(selected.getAddress());
            tfPhone.setText(selected.getPhone());
            
            tfId.setDisable(true); 
            btnAdd.setText("Update");
            
        } else {
            showAlert(Alert.AlertType.WARNING, "Belum Ada Pilihan", "Pilih salah satu anggota dari tabel untuk diubah.");
        }
    }

    @FXML
    public void onDelete() {
        Member selected = tableMembers.getSelectionModel().getSelectedItem();
        if (selected != null) {
            memberList.remove(selected);
            clearForm(); 
        } else {
            showAlert(Alert.AlertType.WARNING, "Belum Ada Pilihan", "Pilih salah satu anggota dari tabel untuk dihapus.");
        }
    }

    @FXML
    public void clearForm() {
        tfId.clear();
        tfName.clear();
        tfAddress.clear();
        tfPhone.clear();
        tfId.setDisable(false); 
        editing = null;
        btnAdd.setText("Tambah");
        tableMembers.getSelectionModel().clearSelection();
    }
    
    private void showError(String msg) {
        showAlert(Alert.AlertType.ERROR, "Kesalahan Input", msg);
    }
    
    public ObservableList<Member> getMemberList() {
        return memberList;
    }
    

    private Member findMemberById(String id) {
        for (Member member : memberList) {
            if (member.getId().equals(id)) {
                return member;
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
