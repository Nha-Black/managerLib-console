
package Models;

public class Book {
	private String id;
	private String title;
	private String author;
	private String publisher;
	private String publicationYear;
	private String language;
	private String genre;
	private int available;
	private int pageCount;

	public Book(String id, String title, String author, String publisher, String publicationYear, String language,
			String genre, int available, int pageCount) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.publicationYear = publicationYear;
		this.language = language;
		this.genre = genre;
		this.available = available;
		this.pageCount = pageCount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(String publicationYear) {
		this.publicationYear = publicationYear;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	@Override
	public String toString() {
		return "Book " + id + ", " + title + ", " + author + ", " + publisher + ", " + publicationYear + ", " + language
				+ ", " + genre + ", " + available + ", " + pageCount;
	}
}
