package engine;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        CLI cli = new CLI(library);
        cli.start();
    }
}
