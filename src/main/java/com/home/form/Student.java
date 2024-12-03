package com.home.form;

import java.time.LocalDate;
import java.util.List;

public class Student {
    private String id;
    private String name;
    private LocalDate birthDay;
    private String gender;
    private String phone;
    private String department;
    private String email;
    private List<String> borrowBook;
    private int borrowLimit = 5;

    public Student(String id, String name, LocalDate birthDay, String gender, String phone, String department, String email, List<String> borrowBook) {
        this.id = id;
        this.name = name;
        this.birthDay = birthDay;
        this.gender = gender;
        this.phone = phone;
        this.department = department;
        this.email = email;
        this.borrowBook = borrowBook;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getBirthDay() { return birthDay; }
    public void setBirthDay(LocalDate birthDay) { this.birthDay = birthDay; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<String> getBorrowBook() { return borrowBook; }
    public void setBorrowBook(List<String> borrowBook) { this.borrowBook = borrowBook; }

    public int getBorrowLimit() { return borrowLimit; }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", birthDay=" + birthDay +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", department='" + department + '\'' +
                ", email='" + email + '\'' +
                ", borrowBook=" + borrowBook +
                ", borrowLimit=" + borrowLimit +
                '}';
    }
}
