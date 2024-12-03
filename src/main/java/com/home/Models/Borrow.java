package com.home.models;

import java.time.LocalDate;

public class Borrow {
    private String id;
    private LocalDate date;
    public Borrow() {
    }
    public Borrow(String id, LocalDate date) {
        this.id = id;
        this.date = date;
    }
    public String getId() {
        return id;
    }
    public LocalDate getDate() {
        return date;
    }
    @Override
    public String toString() {
        return "Borrow{" +
                "id='" + id + '\'' +
                ", date=" + date +
                '}';
    }
    
}
