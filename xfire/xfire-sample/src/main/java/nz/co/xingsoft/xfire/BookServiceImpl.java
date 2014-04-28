package nz.co.xingsoft.xfire;

public class BookServiceImpl
        implements BookService {

    private Book onlyBook;

    public BookServiceImpl() {
        onlyBook = new Book();
        onlyBook.setIsbn("AA");
        onlyBook.setAuthor("Xing");
        onlyBook.setTitle("New Story");
    }

    @Override
    public Book[] getBooks() {
        return new Book[] { onlyBook };
    }

    @Override
    public Book findBook(final String isbn) throws BookException {

        if (isbn.equals(onlyBook.getIsbn())) {
            return onlyBook;
        }

        throw new BookException("Book not exists");
    }
}
