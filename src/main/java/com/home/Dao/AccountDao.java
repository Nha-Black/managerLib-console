package com.home.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.models.Account;

public class AccountDao {
    
    public static List<Account> read(){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            List<Account> accounts = objectMapper.readValue(
                    new File("./src/main/resources/accounts.json"),
                    new TypeReference<List<Account>>() {} 
            );

            return accounts;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void write(){
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account("codetieutu", "19062005"));
        accounts.add(new Account("kimhien", "28032005"));
        accounts.add(new Account("anhngoc", "01012005"));

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("./src/main/resources/accounts.json"), accounts);

            System.out.println("Dữ liệu đã được ghi vào file accounts.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        write();
    }
}
