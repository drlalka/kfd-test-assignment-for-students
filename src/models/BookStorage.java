package models;

import java.util.LinkedList;
import java.util.Map;
import java.util.LinkedHashMap;

// Stores and manages books in the library.
// Responsible for adding, removing, searching, and tracking overdue books.
// Overdue is tracked from the book side (not the user side), because
// each user can have multiple books, and in most libraries the count of books
// is about the same or larger than the count of users.
// of borrowed elements, while preserving order and providing quick access by ID.


public class BookStorage {
    private final Map<Integer, Book> booksById;
    private final Map<String, Book> booksByName;
    private int lastId;

    public BookStorage() {
        this.booksById = new LinkedHashMap<>();// LinkedHashMap is chosen to have fast iteration and add/search/remove
        this.booksByName = new LinkedHashMap<>(); // Fast way to find object not only by id (it could be lost)
        this.lastId = 0;
    }

    public ErrorMess addBook(Book book) {
        if (book == null || book.getId() < 0 || book.getName() == null) {
            return ErrorMess.BadRequest;
        }
        if (book.getId() == 0) {
            book.setId(++lastId);
        }
        if (booksById.containsKey(book.getId()) || booksByName.containsKey(book.getName().toLowerCase())) {
            return ErrorMess.Doubl;
        }
        booksById.put(book.getId(), book);
        booksByName.put(book.getName().toLowerCase(), book);
        return ErrorMess.OK;
    }

    public ErrorMess removeBookById(int id) {
        if (id <= 0) return ErrorMess.BadRequest;
        Book removed = booksById.remove(id);
        if (removed != null) {
            booksByName.remove(removed.getName().toLowerCase());
            return ErrorMess.OK;
        }
        return ErrorMess.NotFound;
    }

    public ErrorMess removeBookByName(String name) {
        if (name == null || name.isBlank()) return ErrorMess.BadRequest;
        Book removed = booksByName.remove(name.toLowerCase());
        if (removed != null) {
            booksById.remove(removed.getId());
            return ErrorMess.OK;
        }
        return ErrorMess.NotFound;
    }

    public Book searchBookById(int id) {
        return booksById.get(id);
    }

    public Book searchBookByName(String name) {
        if (name == null) return null;
        return booksByName.get(name.toLowerCase());
    }

    public LinkedList<Book> getBorrowedBooks() {
        LinkedList<Book> borrowedBooks = new LinkedList<>();
        for (Book book : booksById.values()) {
            if (book.isBorrowed()) {
                borrowedBooks.addFirst(book);
            }
        }
        return borrowedBooks;
    }

    public LinkedList<Book> getBooks() {
        return new LinkedList<>(booksById.values());
    }
}
