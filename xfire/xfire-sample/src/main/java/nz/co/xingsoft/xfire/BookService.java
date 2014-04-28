package nz.co.xingsoft.xfire;

public interface BookService {
    public Book[] getBooks();

    public Book findBook(String isbn)
            throws BookException;
}
