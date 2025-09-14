package engine;

// Program entry point. Starts the CLI with a library instance.

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        CLI cli = new CLI(library);
        cli.start();
    }
}
