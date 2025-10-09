package com.uts.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Book {
    
    private final StringProperty id;
    private final StringProperty title;
    private final StringProperty author;
    private final IntegerProperty year;

    public Book(String id, String title, String author, Integer year) {
        this.id = new SimpleStringProperty(id);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author != null ? author : ""); 
        this.year = new SimpleIntegerProperty(year != null ? year : 0); 
    }
    

    public StringProperty idProperty() {
        return id;
    }

    public StringProperty titleProperty() {
        return title;
    }

    public StringProperty authorProperty() {
        return author;
    }

    public IntegerProperty yearProperty() {
        return year;
    }
    
    
    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getAuthor() {
        return author.get();
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public Integer getYear() {
        return year.get();
    }

    public void setYear(Integer year) {
        this.year.set(year);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id.get() + '\'' +
                ", title='" + title.get() + '\'' +
                ", author='" + author.get() + '\'' +
                ", year=" + year.get() +
                '}';
    }
}