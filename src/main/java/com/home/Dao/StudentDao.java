package com.home.dao;
import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.home.models.Student;

public class StudentDao {
	public static void write(List<Student> students){
		ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Đăng ký module JSR310

        try {
            objectMapper.writerWithDefaultPrettyPrinter()
                        .writeValue(new File("./src/main/resources/students.json"), students);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	public static List<Student> read(){
		ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Đăng ký module JSR310

        try {
            // Đọc danh sách Student từ file JSON
            List<Student> students = objectMapper.readValue(
                    new File("./src/main/resources/students.json"),
                    new TypeReference<List<Student>>() {}
            );
			return students;
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
	}
    public static void main(String[] args) {
        System.out.println(read().size());
    }

}
