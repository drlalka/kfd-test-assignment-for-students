package models;

import java.util.ArrayList;
import java.util.List;

// Represents a user with personal data and their borrowed books.

public abstract class User {
    private int userId;
    private final String name;
    private final String email;

    // we know that it cant have more than const books, so its more important to have fast insert and delete -> List
    private final List<Integer> borrowedBooks = new ArrayList<>();

    public abstract int getMaxBooks();

    public abstract int getBorrowDays();

    public abstract double getFinePerDay();

    public abstract User copy();

    protected User(int userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public ErrorMess setId(int userId) {
        if (userId <= 0) {
            return ErrorMess.BadRequest;
        }
        this.userId = userId;
        return ErrorMess.OK;
    }

    public List<Integer> getBorrowedBooks() {
        return new ArrayList<>(borrowedBooks);
    }

    public ErrorMess borrowBook(int bookId) {
        if (bookId <= 0) {
            return ErrorMess.BadRequest;
        }
        if (borrowedBooks.contains(bookId)) {
            return ErrorMess.Doubl;
        }
        if (borrowedBooks.size() >= getMaxBooks()) {
            return ErrorMess.Aborted;
        }
        borrowedBooks.add(bookId);
        return ErrorMess.OK;
    }

    public ErrorMess returnBook(int bookId) {
        boolean removed = borrowedBooks.remove(Integer.valueOf(bookId));
        return removed ? ErrorMess.OK : ErrorMess.NotFound;
    }

    public void print() {
        System.out.println("User:" + getId() + "\n\t" + getName() + "\n\t" + getEmail() + "\n");
    }
}
