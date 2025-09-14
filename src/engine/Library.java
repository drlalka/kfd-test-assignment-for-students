package engine;

import models.*;

import java.time.LocalDate;
import java.util.LinkedList;

// Connects user data and book storage into a single system.
// Provides operations that require access to both parts.

public class Library {
    private final BookStorage bookStorage;
    private final UsersData usersData;

    public Library() {
        this.bookStorage = new BookStorage();
        this.usersData = new UsersData();
    }

    public ErrorMess addUser(User user) {
        return usersData.addUser(user);
    }

    public ErrorMess removeUserById(int id) {
        return usersData.deleteUser(id);
    }

    public ErrorMess removeUserByEmail(String email) {
        User user = this.getUserByEmail(email);
        if (user == null) {
            return ErrorMess.NotFound;
        }
        return this.removeUserById(user.getId());
    }

    public User getUserById(int id) {
        User user = usersData.getById(id);
        return user != null ? user.copy() : null;
    }

    public User getUserByEmail(String email) {
        User user = usersData.getByEmail(email);
        return user != null ? user.copy() : null;
    }

    public ErrorMess addBook(Book book) {
        return bookStorage.addBook(book);
    }

    public ErrorMess removeBookById(int id) {
        return bookStorage.removeBookById(id);
    }

    public ErrorMess removeBookByName(String name) {
        return bookStorage.removeBookByName(name);
    }

    public Book getBookById(int id) {
        Book book = bookStorage.searchBookById(id);
        return book != null ? book.copy() : null;
    }

    public Book getBookByName(String name) {
        Book book = bookStorage.searchBookByName(name);
        return book != null ? book.copy() : null;
    }

    public LinkedList<Book> getBorrowedBooks() {
        LinkedList<Book> original = bookStorage.getBorrowedBooks();
        LinkedList<Book> copy = new LinkedList<>();
        for (Book b : original) {
            copy.add(b.copy());
        }
        return copy;
    }

    public ErrorMess borrowBook(int userId, int bookId, LocalDate date) {
        Book book = bookStorage.searchBookById(bookId);
        if (book == null) return ErrorMess.NotFound;
        if (book.isBorrowed()) return ErrorMess.Doubl;

        User user = usersData.getById(userId);
        if (user == null) return ErrorMess.NotFound;

        ErrorMess error = user.borrowBook(bookId);
        if (error != ErrorMess.OK) return error;

        book.borrow(userId, date);
        return ErrorMess.OK;
    }


    public double returnBook(int userId, int bookId, LocalDate date) {
        Book book = bookStorage.searchBookById(bookId);
        if (book == null) return -1;
        if (!book.isBorrowed()) return -2;
        User user = usersData.getById(userId);
        if (user == null) return -3;
        ErrorMess error = user.returnBook(bookId);
        if (error != ErrorMess.OK) return -4;
        LocalDate returnDate = date != null ? date : LocalDate.now();
        int daysBorrowed = book.timeBorrowed(returnDate);
        book.returnBook();
        double fine = (daysBorrowed - user.getBorrowDays()) * user.getFinePerDay();
        return fine > 0 ? fine : 0;
    }


    double getFine(int userId, int bookId, LocalDate date) {
        Book book = bookStorage.searchBookById(bookId);
        if (book == null) return -1;
        if (!book.isBorrowed()) {
            return -2;
        }
        User user = this.getUserById(userId);
        if (user == null) return -3;
        double fine = (book.timeBorrowed(date) - user.getBorrowDays()) * user.getFinePerDay();
        if (fine <= 0) return 0;
        return fine;
    }

    public LinkedList<Book> getBooks() {
        return this.bookStorage.getBooks();
    }

    public LinkedList<User> getUser() {
        return this.usersData.getUsers();
    }
}
