package com.home.component;

public class Color {
    public static final String RESET = "\033[0m";   // Đặt lại màu về mặc định
    public static final String RED = "\033[31m";   // Màu đỏ
    public static final String GREEN = "\033[32m"; // Màu xanh lá
    public static final String YELLOW = "\033[33m";// Màu vàng
    public static final String BLUE = "\033[34m";  // Màu xanh dương
    public static final String PURPLE = "\033[35m";// Màu tím
    public static final String CYAN = "\033[36m";  // Màu xanh cyan
    public static String apply(String color, String text){
        return color+text+RESET;
    }
}
