package engine;

import models.*;

import java.time.LocalDate;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// Handles interaction with the user via the command line.
// Collects input, passes it to the library engine, and shows results.

public class CLI {
    private final Library library;
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private boolean exitFlag;

    public CLI(Library library) {
        this.library = library;
    }

    public void start() {
        while (!exitFlag) {
            printMenu();
            String choice;
            try {
                choice = reader.readLine();
            } catch (IOException e) {
                System.out.println("Error reading input: " + e.getMessage());
                continue;
            }

            switch (choice) {
                case "1":
                    while (true) {
                        printBookManagement();
                        try {
                            choice = reader.readLine();
                        } catch (IOException e) {
                            System.out.println("Error reading input: " + e.getMessage());
                            continue;
                        }
                        if (handleBookChoice(choice)) break;
                    }
                    break;
                case "2":
                    while (true) {
                        printUserManagement();
                        try {
                            choice = reader.readLine();
                        } catch (IOException e) {
                            System.out.println("Error reading input: " + e.getMessage());
                            continue;
                        }
                        if (handleUserChoice(choice)) break;
                    }
                    break;
                case "3":
                    while (true) {
                        printBorrowingOperations();
                        try {
                            choice = reader.readLine();
                        } catch (IOException e) {
                            System.out.println("Error reading input: " + e.getMessage());
                            continue;
                        }
                        if (handleBorrowChoice(choice)) break;
                    }
                    break;
                case "0":
                    System.out.println("Exiting...");
                    exitFlag = true;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    private void printMenu() {
        System.out.println("\n<Head>\n");
        System.out.println("\nChoose an option:\n");
        System.out.println("1. Book Management");
        System.out.println("2. User Management");
        System.out.println("3. Borrowing Operations");
        System.out.println("0. Exit");
        System.out.print("> ");
    }

    private void printBookManagement() {
        System.out.println("\n<Books>\n");
        System.out.println("\nChoose an option:\n");
        System.out.println("1. Add");
        System.out.println("2. Remove");
        System.out.println("3. Search");
        System.out.println("4. Show all");
        System.out.println("0. Exit");
        System.out.print("> ");
    }

    private void printUserManagement() {
        System.out.println("\n<Users>\n");
        System.out.println("\nChoose an option:\n");
        System.out.println("1. Add");
        System.out.println("2. Remove");
        System.out.println("3. Search");
        System.out.println("4. Show all");
        System.out.println("0. Exit");
        System.out.print("> ");
    }


    private void printBorrowingOperations() {
        System.out.println("\n<Borrowing>\n");
        System.out.println("\nChoose an option:\n");
        System.out.println("1. Borrow");
        System.out.println("2. Return");
        System.out.println("3. Track overdue");
        System.out.println("0. Back to Main Menu");
        System.out.print("> ");
    }

    private boolean handleBookChoice(String choice) {
        switch (choice) {
            case "1":
                addBook();
                break;
            case "2":
                removeBook();
                break;
            case "3":
                searchBook();
                break;
            case "4":
                showAllBooks();
                break;
            case "0":
                return true;
            default:
                System.out.println("Invalid choice");
                break;
        }
        return false;
    }

    private boolean handleUserChoice(String choice) {
        switch (choice) {
            case "1":
                addUser();
                break;
            case "2":
                removeUser();
                break;
            case "3":
                searchUser();
                break;
            case "4":
                showAllUsers();
                break;
            case "0":
                return true;
            default:
                System.out.println("Invalid choice");
                break;
        }
        return false;
    }


    private boolean handleBorrowChoice(String choice) {
        switch (choice) {
            case "1":
                borrowBook();
                break;
            case "2":
                returnBook();
                break;
            case "3":
                trackOverdue();
                break;
            case "0":
                return true;
            default:
                System.out.println("Invalid choice");
                break;
        }
        return false;
    }

    private String prompt(String message, String regex) throws IOException {
        while (true) {
            System.out.print(message + " > ");
            String input = reader.readLine();
            if (regex == null || input.matches(regex)) {
                return input;
            }
            System.out.println("Invalid input");
        }
    }

    private void addUser() {
        try {
            System.out.println("\nAdding new user:");
            String typeChoice = prompt("choose (1 - Student; 2 - Faculty; 3 - Guest)", "[123]");
            String name = prompt("write name", "[A-Za-z\\s'.-]{2,50}");
            String email = prompt("write email", "^[A-Za-z0-9+_.-]+@(.+)$");

            User user;
            switch (typeChoice) {
                case "1":
                    String group = prompt("Type group", "[A-Z]\\d{2}-\\d{3}");
                    user = new Student(0, name, email, group);
                    break;
                case "2":
                    String department = prompt("Type dep", "[A-Za-z0-9\\s&.,()-]{2,100}");
                    user = new Faculty(0, name, email, department);
                    break;
                case "3":
                    user = new Guest(0, name, email);
                    break;
                default:
                    System.out.println("Invalid choice");
                    return;
            }

            ErrorMess result = library.addUser(user);
            System.out.println(result == ErrorMess.OK ? "Success. ID is " + user.getId() : "Error: " + result);

        } catch (IOException e) {
            System.out.println("Type Error: " + e.getMessage());
        }
    }

    private void removeUser() {
        System.out.println("\nDelete user:");
        try {
            String typeChoice = prompt("choose (1 - rm by id; 2 - rm by email)", "[12]");
            ErrorMess result;
            if (typeChoice.equals("1")) {
                String idS = prompt("Type id", "\\d+");
                int id = Integer.parseInt(idS.trim());
                result = library.removeUserById(id);
            } else {
                String email = prompt("write email", "^[A-Za-z0-9+_.-]+@(.+)$");
                result = library.removeUserByEmail(email);
            }
            System.out.println(result == ErrorMess.OK ? "Success" : "Error: " + result);

        } catch (IOException e) {
            System.out.println("Input Error: " + e.getMessage());
        }
    }

    private void searchUser() {
        System.out.println("\nSearch user:");
        try {
            String typeChoice = prompt("choose (1 - find by id; 2 - find by email)", "[12]");
            User user;
            if (typeChoice.equals("1")) {
                String idS = prompt("Type id", "\\d+");
                int id = Integer.parseInt(idS.trim());
                user = library.getUserById(id);
            } else {
                String email = prompt("write email", "^[A-Za-z0-9+_.-]+@(.+)$");
                user = library.getUserByEmail(email);
            }
            if (user == null) {
                System.out.println("Not found");
                return;
            }

            user.print();
            List<Integer> userBooksID = user.getBorrowedBooks();
            List<String> books = new ArrayList<>();
            for (int bookId : userBooksID) {
                Book buff = library.getBookById(bookId);
                if (buff != null) books.add(buff.getName());
            }
            System.out.println(books.isEmpty() ? "Borrowed books: none" : "Borrowed books: " + books);

        } catch (IOException e) {
            System.out.println("Input Error: " + e.getMessage());
        }
    }

    private void addBook() {
        try {
            System.out.println("\nAdding new book:");
            String name = prompt("write name", ".+");
            String author = prompt("write author", ".+");
            Book book = new Book(0, name, author);
            ErrorMess result = library.addBook(book);
            System.out.println(result == ErrorMess.OK ? "Success" : "Error: " + result);
        } catch (IOException e) {
            System.out.println("Type Error: " + e.getMessage());
        }
    }

    private void removeBook() {
        System.out.println("\nDelete book:");
        try {
            String typeChoice = prompt("choose (1 - rm by id; 2 - rm by name)", "[12]");
            ErrorMess result;
            if (typeChoice.equals("1")) {
                String idS = prompt("Type id", "\\d+");
                int id = Integer.parseInt(idS.trim());
                result = library.removeBookById(id);
            } else {
                String name = prompt("write name", ".+");
                result = library.removeBookByName(name);
            }
            System.out.println(result == ErrorMess.OK ? "Success" : "Error: " + result);

        } catch (IOException e) {
            System.out.println("Input Error: " + e.getMessage());
        }
    }

    private void searchBook() {
        System.out.println("\nSearch book:");
        try {
            String typeChoice = prompt("choose (1 - find by id; 2 - find by name)", "[12]");
            Book book;
            if (typeChoice.equals("1")) {
                String idS = prompt("Type id", "\\d+");
                int id = Integer.parseInt(idS.trim());
                book = library.getBookById(id);
            } else {
                String name = prompt("write name", ".+");
                book = library.getBookByName(name);
            }
            if (book == null) {
                System.out.println("Not found");
                return;
            }
            book.print();

        } catch (IOException e) {
            System.out.println("Input Error: " + e.getMessage());
        }
    }

    private void borrowBook() {
        System.out.println("\nBorrow book:");
        try {
            String idS = prompt("Type user id", "\\d+");
            int userId = Integer.parseInt(idS.trim());
            idS = prompt("Type book id", "\\d+");
            int bookId = Integer.parseInt(idS.trim());
            String dateS = prompt("Type borrow date (yyyy-MM-dd) or press Enter", ".*");
            LocalDate date = dateS.trim().isEmpty() ? null : LocalDate.parse(dateS.trim());
            ErrorMess result = library.borrowBook(userId, bookId, date);
            System.out.println(result == ErrorMess.OK ? "Success" : "Error: " + result);
        } catch (IOException e) {
            System.out.println("Input Error: " + e.getMessage());
        }
    }

    private void returnBook() {
        System.out.println("\nReturn book:");
        try {
            String idS = prompt("Type user id", "\\d+");
            int userId = Integer.parseInt(idS.trim());
            idS = prompt("Type book id", "\\d+");
            int bookId = Integer.parseInt(idS.trim());
            String dateS = prompt("Type borrow date (yyyy-MM-dd) or press Enter", ".*");
            LocalDate date = dateS.trim().isEmpty() ? null : LocalDate.parse(dateS.trim());
            double result = library.returnBook(userId, bookId, date);
            if (result >= 0) {
                System.out.println("Success: fine is " + result);
                return;
            }
            switch ((int) result) {
                case -1:
                    System.out.println("Book not found");
                    break;
                case -2:
                    System.out.println("Book is not borrowed");
                    break;
                case -3:
                    System.out.println("user not found");
                    break;
                case -4:
                    System.out.println("user cant return");
                    break;
            }
        } catch (IOException e) {
            System.out.println("Input Error: " + e.getMessage());
        }
    }

    private void trackOverdue() {
        System.out.println("\nTrack overdue:");
        List<Book> books = library.getBorrowedBooks();
        for (Book book : books) {
            if (book == null) continue;
            System.out.println("Book " + book.getId() + ": " + book.getName());
            double fine = library.getFine(book.getIdBorrowUser(), book.getId(), null);
            System.out.println("\t by User: " + book.getIdBorrowUser() +
                    " | days: " + book.timeBorrowed(null) + " | fine: " + (fine < 0 ? 0 : fine));
        }
    }

    private void showAllBooks() {
        System.out.println("\nAll books:");
        List<Book> books = library.getBooks();
        if (books == null || books.isEmpty()) {
            System.out.println("No books available");
            return;
        }
        for (Book book : books) {
            if (book != null) System.out.println(book.getId() + ": " + book.getName());
        }
    }

    private void showAllUsers() {
        System.out.println("\nAll users:");
        List<User> users = library.getUser();
        if (users == null || users.isEmpty()) {
            System.out.println("No users available");
            return;
        }
        for (User user : users) {
            if (user != null) System.out.println(user.getId() + ": " + user.getName());
        }
    }

}
