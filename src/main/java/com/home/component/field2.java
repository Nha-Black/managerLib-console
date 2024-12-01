package com.home.component;
import java.io.IOException;

public class field2 {
    private int x;               // Vị trí x (cột)
    private int y;               // Vị trí y (dòng)
    private int width;           // Độ rộng tối đa của trường
    private StringBuilder content; // Nội dung đang nhập
    private int cursor;          // Vị trí con trỏ trong nội dung

    public field2(int x, int y, int width) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.content = new StringBuilder();
        this.cursor = 0;
    }

    // Hiển thị trường nhập trên console
    public void render() {
        // Di chuyển con trỏ đến vị trí x, y
        System.out.print("\033[" + y + ";" + x + "H"); // Mã ANSI: Di chuyển con trỏ
        String display = content.toString();
        if (display.length() > width) {
            display = display.substring(display.length() - width); // Cắt chuỗi nếu dài hơn độ rộng
        }
        System.out.print(display); // Hiển thị nội dung
        // Thêm khoảng trống nếu nội dung ngắn hơn độ rộng
        for (int i = display.length(); i < width; i++) {
            System.out.print(" ");
        }
        // Đưa con trỏ về đúng vị trí
        System.out.print("\033[" + y + ";" + (x + cursor) + "H");
    }

    // Xử lý nhập liệu từ người dùng
    public void handleInput() throws IOException {
        while (true) {
            render(); // Cập nhật giao diện
            int key = System.in.read(); // Đọc phím

            if (key == 13) { // Enter
                break;
            } else if (key == 127 || key == 8) { // Backspace
                if (cursor > 0) {
                    content.deleteCharAt(cursor - 1);
                    cursor--;
                }
            } else if (key == 27) { // Phím điều hướng (ESC + [)
                System.in.read(); // Bỏ qua '['
                int direction = System.in.read();
                if (direction == 67 && cursor < content.length()) { // Mũi tên phải
                    cursor++;
                } else if (direction == 68 && cursor > 0) { // Mũi tên trái
                    cursor--;
                }
            } else if (key >= 32 && key <= 126) { // Các ký tự hiển thị được
                if (content.length() < width) {
                    content.insert(cursor, (char) key);
                    cursor++;
                }
            }
        }
    }

    // Lấy giá trị nhập
    public String getValue() {
        return content.toString();
    }

    // Đặt giá trị mặc định
    public void setValue(String value) {
        this.content = new StringBuilder(value);
        this.cursor = value.length();
    }
    public static void main(String[] args) throws IOException {
        // Tạo một trường nhập với vị trí (x=5, y=3) và độ rộng 20 ký tự
        field2 field = new field2(5, 3, 20);
        System.out.print("\033[H\033[2J");
        System.out.println("Nhập dữ liệu:"); // Hướng dẫn người dùng
        field.handleInput(); // Xử lý nhập liệu từ người dùng

        // Lấy giá trị sau khi nhập xong
        String value = field.getValue();
        System.out.println("\nBạn đã nhập: " + value);
    }
}
