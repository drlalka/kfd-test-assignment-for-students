package models;

public class Guest extends User {
    public Guest(int userId, String name, String email) {
        super(userId, name, email);
    }
    
    @Override
    public int getMaxBooks() {
        return 1;
    }

    @Override
    public int getBorrowDays() {
        return 7;
    }

    @Override
    public double getFinePerDay() {
        return 5;
    }

    public User copy(){
        return new Guest(this.getId(), this.getName(), this.getEmail());
    }
}
