package pl.spring.demo.service;

import pl.spring.demo.dao.BookDao;
import pl.spring.demo.to.BookTo;

import java.util.List;

public interface BookService {

	/**
	 * Use {@link BookDao} function to find all books and map them to list of {@link BookTo} objects.
	 * @see BookDao
	 * @see BookTo
	 * @return List of all book
	 */
    List<BookTo> findAllBooks();
    
    /**
	 * Use {@link BookDao} function to find all by title and map them to list of {@link BookTo} objects.
	 * @see BookDao
	 * @see BookTo
	 * @param title prefix of title
	 * @return List of book found by title
	 */
    List<BookTo> findBooksByTitle(String title);
    
    /**
	 * Use {@link BookDao} function to find all by author and map them to list of {@link BookTo} objects.
	 * @see BookDao
	 * @see BookTo
	 * @param author prefix of author
	 * @return List of book found by author
	 */
    List<BookTo> findBooksByAuthor(String author);

    /**
     * Saved book to database.
     * @param book to save
     * @return	saved book
     */
    BookTo saveBook(BookTo book);
}
