package models;

import java.util.LinkedList;
import java.util.Map;
import java.util.LinkedHashMap;

public class BookStorage {
    private final Map<Integer, Book> booksById;
    private final Map<String, Book> booksByName;
    private int lastId;

    public BookStorage() {
        this.booksById = new LinkedHashMap<>();
        this.booksByName = new LinkedHashMap<>();
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
