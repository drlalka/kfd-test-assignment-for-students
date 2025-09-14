package test;

import engine.CLI;
import engine.Library;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Test {
    public static void main(String[] args) {
        Library library = new Library();
        String[] inputLines = {
                "1",
                "1", "Clean Code", "Robert Martin",
                "1", "Effective Java", "Joshua Bloch",
                "1", "ClouFare", "Kolka",
                "4",
                "0",
                "2",
                "1", "1", "Alice Smith", "alice@example.com", "A01-001",
                "1", "2", "Prof. Clark", "clark@example.com", "CS Department",
                "1", "3", "Kolkaa the writer", "kolks@jirmiy.com",
                "4",
                "0",
                "3",
                "1", "1", "1", "2005-03-09",
                "3",
                "0",
                "1",
                "3", "1", "1",
                "0",
                "3",
                "2", "1", "1", "2005-06-09",
                "0",
                "1",
                "3", "1", "1",
                "0",
                "0"
        };

        String input = String.join("\n", inputLines);


        InputStream originalIn = System.in;

        try {
            System.setIn(new ByteArrayInputStream(input.getBytes()));

            CLI cliTest = new CLI(library);
            cliTest.start();

        } finally {
            System.setIn(originalIn);
        }

        System.out.println("\nTest end");
    }
}
