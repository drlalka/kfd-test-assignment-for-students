package models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Book {
    private final String name;
    private final String author;
    private int id;
    private int idBorrowUser;
    private boolean borrowed;
    private LocalDate borrowDate;

    public Book(int id, String name, String author) {
        this.id = id;
        this.name = name.toLowerCase();
        this.author = author;
        this.idBorrowUser = 0;
        this.borrowDate = null;
        borrowed = false;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getIdBorrowUser() {
        return idBorrowUser;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public ErrorMess setId(int id) {
        if (id <= 0) return ErrorMess.BadRequest;
        this.id = id;
        return ErrorMess.OK;
    }

    public Book copy() {
        Book book = new Book(this.id, this.name, this.author);
        book.borrowed = this.borrowed;
        book.borrowDate = this.borrowDate;
        book.idBorrowUser = this.idBorrowUser;
        return book;
    }

    public void borrow(int userId, LocalDate date) {
        this.borrowed = true;
        this.borrowDate = date == null ? LocalDate.now() : date;
        this.idBorrowUser = userId;
    }

    public void print() {
        System.out.println("Book " + this.name + ":");
        System.out.println("\tID: " + this.id);
        System.out.println("\tAuthor: " + this.author);
        System.out.println("\tBorrowed by: " + this.idBorrowUser);
    }

    public int timeBorrowed(LocalDate date) {
        if (!borrowed) return 0;
        return (int) ChronoUnit.DAYS.between(borrowDate, date == null ? LocalDate.now() : date);
    }

    public void returnBook() {
        this.borrowed = false;
        this.borrowDate = null;
        this.idBorrowUser = 0;
    }
}
