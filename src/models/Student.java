package models;

public class Student extends User {
    public Student(String userId, String name, String email) {
        super(userId, name, email);
        this.type = UserType.STUDENT;
    }

    @Override
    public int getMaxBooks() {
        return 3;
    }

    @Override
    public int getBorrowDays() {
        return 14;
    }

    @Override
    public double getMaxDay() {
        return 0;
    }
}
