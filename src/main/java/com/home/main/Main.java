package com.home.main;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import com.home.control.LoginControl;
import com.sun.jna.Library;
import com.sun.jna.Native;


public class Main {
    public interface Kernel32 extends Library {
        Kernel32 INSTANCE = Native.load("kernel32", Kernel32.class);
        boolean SetConsoleOutputCP(int codePage);
        boolean SetConsoleCP(int codePage);
    }
    
    private static void setOuput(){
        try {
            // Kiểm tra nếu hệ điều hành là Windows
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                Kernel32.INSTANCE.SetConsoleOutputCP(65001); 
                Kernel32.INSTANCE.SetConsoleCP(65001);       
            } else {
                System.out.println("Không cần thiết lập mã hóa trên hệ điều hành này.");
            }
            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8.name()));
            System.out.println("thiết lập hiển thị unicode thành công");
        } catch (Exception e) {
            System.out.println("setup fail");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        setOuput();
        new LoginControl();
        
    }
}
