package nz.co.xingsoft.xfire;

import org.codehaus.xfire.aegis.type.java5.XmlType;

@XmlType(name = "Book", namespace = "urn:xfire:book")
public class Book {

    private String title;
    private String isbn;
    private String author;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(final String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book [title=" + title + ", isbn=" + isbn + ", author=" + author + "]";
    }

}
