package Dao;

import Models.Book;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.*;

public class DaoBook {
	private static final String FILE_PATH = "Books.txt";
	private static final String JSON_FILE_PATH = "Books.json";

	public List<Book> readBooks() {
		List<Book> books = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",\\s*");
				if (data.length == 9) {
					Book book = new Book(data[0], data[1], data[2], data[3], data[4], data[5], data[6],
							Integer.parseInt(data[7].trim()), Integer.parseInt(data[8].trim()));
					books.add(book);
				}
			}
		} catch (IOException e) {
			System.out.println("Đọc file lỗi: " + e.getMessage());
		}
		return books;
	}

	public boolean addBook(Book book) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
			writer.write(book.getId() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.getPublisher() + ","
					+ book.getPublicationYear() + "," + book.getLanguage() + "," + book.getGenre() + ","
					+ book.getAvailable() + "," + book.getPageCount());
			writer.newLine();
			return true;
		} catch (IOException e) {
			System.out.println("Lỗi khi ghi file: " + e.getMessage());
			return false;
		}
	}

	public boolean updateBook(Book updatedBook) {
		List<Book> books = readBooks();
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getId().equals(updatedBook.getId())) {
				books.set(i, updatedBook);
				writeBooksToFile(books);
				return true;
			}
		}
		return false;
	}

	private void writeBooksToFile(List<Book> books) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
			for (Book book : books) {
				writer.write(book.getId() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.getPublisher()
						+ "," + book.getPublicationYear() + "," + book.getLanguage() + "," + book.getGenre() + ","
						+ book.getAvailable() + "," + book.getPageCount());
				writer.newLine();
			}
		} catch (IOException e) {
			System.out.println("Lỗi khi ghi file: " + e.getMessage());
		}
	}

	public void exportBooksToJson() {
		List<Book> books = readBooks();
		if (books.isEmpty()) {
			System.out.println("Không có sách để xuất.");
			return;
		}

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

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(JSON_FILE_PATH))) {
			writer.write(booksArray.toString(4));
			System.out.println("Dữ liệu sách đã được xuất ra file JSON thành công.");
		} catch (IOException e) {
			System.out.println("Lỗi khi xuất dữ liệu ra file JSON: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
