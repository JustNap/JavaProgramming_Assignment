package com.gudang.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Barang {
    private StringProperty nama = new SimpleStringProperty();
    private IntegerProperty jumlah = new SimpleIntegerProperty();

    public Barang(String nama, int jumlah) {
        this.nama.set(nama);
        this.jumlah.set(jumlah);
    }

    public String getNama() { return nama.get(); }
    public void setNama(String value) { nama.set(value); }
    public StringProperty namaProperty() { return nama; }

    public int getJumlah() { return jumlah.get(); }
    public void setJumlah(int value) { jumlah.set(value); }
    public IntegerProperty jumlahProperty() { return jumlah; }
}
