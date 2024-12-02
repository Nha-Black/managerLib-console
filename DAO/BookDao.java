package DAO;

import Models.Book;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookDao extends Dao {

    public List<Book> readBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM Books";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = Dao.connect();  
            stmt = conn.createStatement(); 
            rs = stmt.executeQuery(sql);  

            while (rs.next()) {
                Book book = new Book(
                        rs.getString("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("publisher"),
                        rs.getString("publicationYear"),
                        rs.getString("language"),
                        rs.getString("genre"),
                        rs.getInt("available"),
                        rs.getInt("pageCount")
                );
                books.add(book);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error : " + e.getMessage());
            }
        }
        return books;
    }

    public boolean addBook(Book book) {
        String sql = "INSERT INTO Books (id, title, author, publisher, publicationYear, language, genre, available, pageCount) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = Dao.connect();  
            pstmt = conn.prepareStatement(sql); 
            pstmt.setString(1, book.getId());
            pstmt.setString(2, book.getTitle());
            pstmt.setString(3, book.getAuthor());
            pstmt.setString(4, book.getPublisher());
            pstmt.setString(5, book.getPublicationYear());
            pstmt.setString(6, book.getLanguage());
            pstmt.setString(7, book.getGenre());
            pstmt.setInt(8, book.getAvailable());
            pstmt.setInt(9, book.getPageCount());
            pstmt.executeUpdate();
            return true; 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false; 
        }
    }

    public boolean updateBook(Book book) {
        String sql = "UPDATE Books SET title = ?, author = ?, publisher = ?, publicationYear = ?, language = ?, genre = ?, available = ?, pageCount = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = Dao.connect();  
            pstmt = conn.prepareStatement(sql);  
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getPublisher());
            pstmt.setString(4, book.getPublicationYear());
            pstmt.setString(5, book.getLanguage());
            pstmt.setString(6, book.getGenre());
            pstmt.setInt(7, book.getAvailable());
            pstmt.setInt(8, book.getPageCount());
            pstmt.setString(9, book.getId()); 
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; 
        } catch (SQLException e) {
            System.out.println("Error :" + e.getMessage());
            return false;
        }
    }

    public void exportBooksToJson() {
        List<Book> books = readBooks();  
        JSONArray booksArray = new JSONArray();
        for (Book book : books) {
            JSONObject bookJson = new JSONObject();
            bookJson.put("id", book.getId());
            bookJson.put("title", book.getTitle());
            bookJson.put("author", book.getAuthor());
            bookJson.put("publisher", book.getPublisher());
            bookJson.put("publicationYear", book.getPublicationYear());
            bookJson.put("language", book.getLanguage());
            bookJson.put("genre", book.getGenre());
            bookJson.put("available", book.getAvailable());
            bookJson.put("pageCount", book.getPageCount());

            booksArray.put(bookJson);
        }

        try (FileWriter file = new FileWriter("books.json")) {
            file.write(booksArray.toString(4)); 
            file.flush();
            System.out.println("Dữ liệu sách đã được xuất thành công ra file books.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        BookDao b = new BookDao();
        b.exportBooksToJson();  // Xuất danh sách sinh viên ra file students.json
    }
}
