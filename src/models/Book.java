package models;

import java.time.*;

public class Book {
    final private int id;
    final private String name;
    final private String author;
    private int idBorrowUser;
    private boolean borrowed;
    private LocalDate borrowDate;


    public Book(int id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.idBorrowUser = 0;
        this.borrowDate = null;
        borrowed = false;
    }

    public int getId() {
        return id;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void borrow(int userId){
        this.borrowed = true;
        this.borrowDate = LocalDate.now();
        this.idBorrowUser = userId;
    }
}
