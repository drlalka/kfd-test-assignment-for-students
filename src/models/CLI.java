package models;
import java.util.Scanner;

public class CLI {
    static public void start() {

    }

    private static void printMenu() {
        System.out.println("\n<Head>\n");
        System.out.println("\nChoose an option:\n");
        System.out.println("1. Book Management");
        System.out.println("2. User Management");
        System.out.println("3. Borrowing Operations");
        System.out.println("0. Exit");
    }

    private static void printBookManagement() {
        System.out.println("\n<Books>\n");
        System.out.println("\nChoose an option:\n");
        System.out.println("1. Add");
        System.out.println("2. Remove");
        System.out.println("3. Search");
        System.out.println("0. Exit");
    }

    private static void printUserManagement() {
        System.out.println("\n<Users>\n");
        System.out.println("\nChoose an option:\n");
        System.out.println("1. Add");
        System.out.println("2. Remove");
        System.out.println("3. Search");
        System.out.println("0. Exit");
    }

    private static void printBorrowingOperations() {
        System.out.println("\n<Borrowing>\n");
        System.out.println("\nChoose an option:\n");
        System.out.println("1. Borrow");
        System.out.println("2. Return");
        System.out.println("3. Track overdue");
        System.out.println("0. Exit");
    }

    private static void handleUserChoice() {

    }

    private static void handleBookChoice() {

    }

    private static void handleBorrowChoice() {

    }

}
