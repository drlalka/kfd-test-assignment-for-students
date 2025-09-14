package models;

public class Student extends User {
    private String group;

    public Student(int userId, String name, String email, String group) {
        super(userId, name, email);
        this.group = group;
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
    public double getFinePerDay() {
        return 0.50;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public User copy() {
        return new Student(this.getId(), this.getName(), this.getEmail(), this.group);
    }

    @Override
    public void print() {
        super.print();
        System.out.println("\tgroup: " + this.getGroup());
    }
}
