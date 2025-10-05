package com.gudang.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Customer {
    private final StringProperty idCustomer;
    private final StringProperty namaCustomer;
    private final StringProperty email;
    private final StringProperty nomorTelepon;

    public Customer(String idCustomer, String namaCustomer, String email, String nomorTelepon) {
        this.idCustomer = new SimpleStringProperty(idCustomer);
        this.namaCustomer = new SimpleStringProperty(namaCustomer);
        this.email = new SimpleStringProperty(email);
        this.nomorTelepon = new SimpleStringProperty(nomorTelepon != null ? nomorTelepon : "");
    }

    public StringProperty idCustomerProperty() {
        return idCustomer;
    }

    public StringProperty namaCustomerProperty() {
        return namaCustomer;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty nomorTeleponProperty() {
        return nomorTelepon;
    }

    public String getIdCustomer() {
        return idCustomer.get();
    }

    public String getNamaCustomer() {
        return namaCustomer.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getNomorTelepon() {
        return nomorTelepon.get();
    }

    public void setNamaCustomer(String namaCustomer) {
        this.namaCustomer.set(namaCustomer);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon.set(nomorTelepon != null ? nomorTelepon : "");
    }
}