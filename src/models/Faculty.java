package models;

public class Faculty extends User {
    String department;
    public Faculty(int userId, String name, String email, String department) {
        super(userId, name, email);
        this.department = department;
    }
    @Override
    public int getMaxBooks() {
        return 10;
    }

    @Override
    public int getBorrowDays() {
        return 30;
    }

    @Override
    public double getFinePerDay() {
        return 1;
    }

    public String getDepartment() {return this.department;}

    public User copy(){
        return new Faculty(this.getId(), this.getName(), this.getEmail(), this.department);
    }

    @Override
    public void print() {
        super.print();
        System.out.println("\tgroup: " + this.getDepartment());
    }
}
