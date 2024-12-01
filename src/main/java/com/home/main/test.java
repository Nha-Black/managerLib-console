package com.home.main;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in, "UTF-8");

        // Nhập đường dẫn tệp và nội dung
        

        System.out.println("Nhập nội dung UTF-8 để ghi vào tệp:");
        String content = scanner.nextLine();

        // Ghi nội dung vào tệp
        try (FileWriter writer = new FileWriter("data.txt", false)) {
            writer.write(content);
            System.out.println("Ghi vào tệp thành công!");
        } catch (IOException e) {
            System.err.println("Lỗi khi ghi vào tệp: " + e.getMessage());
        }

        scanner.close();
    }
}
