package models;

public abstract class User {
    private final String userId;
    private String name;
    private final UserType type;
    private List<int> borrowedBooks = new ArrayList<>();
    public abstract int getMaxBooks();
    public abstract int getBorrowDays();
    public abstract double getMaxDay();

    protected User(String userId, UserType type, String name) {
        this.userId = userId;
        this.name = name;
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public UserType getType() {
        return type;
    }
}
