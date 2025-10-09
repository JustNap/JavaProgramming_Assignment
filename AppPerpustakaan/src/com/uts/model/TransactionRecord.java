package com.uts.model;

import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class TransactionRecord {
    
    private Member member;
    private Book book;
    private LocalDate borrowDate;

    private final StringProperty memberName;
    private final StringProperty bookTitle;
    private final StringProperty dateString;

    public TransactionRecord(Member member, Book book, LocalDate borrowDate) {
        this.member = member;
        this.book = book;
        this.borrowDate = borrowDate;
        
        this.memberName = new SimpleStringProperty(member.getName());
        this.bookTitle = new SimpleStringProperty(book.getTitle());
        this.dateString = new SimpleStringProperty(borrowDate.toString()); 
    }


    public StringProperty memberNameProperty() {
        return memberName;
    }

    public StringProperty bookTitleProperty() {
        return bookTitle;
    }

    public StringProperty dateStringProperty() {
        return dateString;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
        this.memberName.set(member.getName());
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
        this.bookTitle.set(book.getTitle());
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate date) {
        this.borrowDate = date;
        this.dateString.set(date.toString());
    }
}