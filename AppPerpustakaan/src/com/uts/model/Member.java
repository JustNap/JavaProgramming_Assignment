package com.uts.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Member {
    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty address;
    private final StringProperty phone;

    public Member(String id, String name, String address, String phone) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address != null ? address : "");
        this.phone = new SimpleStringProperty(phone);
    }
    

    public StringProperty idProperty() {
        return id;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty addressProperty() {
        return address;
    }

    public StringProperty phoneProperty() {
        return phone;
    }
    

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }
    
    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }
    
    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    @Override
    public String toString() {
        return String.format("Member [ID=%s, Name=%s, Phone=%s]", id.get(), name.get(), phone.get());
    }
}