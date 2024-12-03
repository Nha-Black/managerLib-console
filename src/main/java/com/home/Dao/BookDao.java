package com.home.dao;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.models.Book;

public class BookDao {
	public static void write(List<Book> books){

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Ghi danh sách vào file JSON
            objectMapper.writerWithDefaultPrettyPrinter()
                        .writeValue(new File("./src/main/resources/books.json"), books);
        } catch (IOException e) {
            e.printStackTrace();
        }

	}

	public static List<Book> read(){ 
		ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Đọc danh sách Book từ file JSON
            List<Book> books = objectMapper.readValue(
                    new File("./src/main/resources/books.json"), 
                    new TypeReference<List<Book>>() {}
            );
			return books;
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
	}
    public static void main(String[] args) {
       	read();
        
    }
}
