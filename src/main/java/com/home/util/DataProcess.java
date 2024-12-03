package com.home.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DataProcess {
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static LocalDate strToDate(String date) {
        try{
            return LocalDate.parse(date, formatter);
        }catch(DateTimeParseException e){
            return null;
        }
    }
    public static String dateToStr(LocalDate date) {
        
        return date.format(formatter);
    }
    public static String strProcess(String str){
        return str.trim();
    }

    public static void main(String[] args) {
        String date = "40-06-2005";
        LocalDate time = LocalDate.now();
    }
}
