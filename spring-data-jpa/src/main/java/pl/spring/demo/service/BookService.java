package pl.spring.demo.service;

import pl.spring.demo.bookmapper.BookMapper;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.to.BookTo;

import java.util.List;

public interface BookService {

	/**
	 * Use {@link BookDao#findAll()} method to find all books and map them 
	 * to list of {@link BookTo} objects by 
	 * {@link BookMapper#bookEntityToBookTo(pl.spring.demo.entity.BookEntity)}.
	 * @see BookDao#findAll()
	 * @see BookTo
	 * @return List of all book or empty list
	 */
    List<BookTo> findAllBooks();
    
    /**
	 * Use {@link BookDao#findBookByTitle(String)} method to find all by title and map them 
	 * to list of {@link BookTo} objects by 
	 * {@link BookMapper#bookEntityToBookTo(pl.spring.demo.entity.BookEntity)}.
	 * @see BookDao#findBookByTitle(String)
	 * @see BookTo
	 * @param title prefix of title
	 * @return List of book found by title or empty list
	 */
    List<BookTo> findBooksByTitle(String title);
    
    /**
	 * Use {@link BookDao#findBooksByAuthor(String)} function to find all by author and map them 
	 * to list of {@link BookTo} objects by 
	 * {@link BookMapper#bookEntityToBookTo(pl.spring.demo.entity.BookEntity)}.
	 * @see BookDao#findBooksByAuthor(String)
	 * @see BookTo
	 * @param author prefix of author
	 * @return List of book found by author or empty list
	 */
    List<BookTo> findBooksByAuthor(String author);

    /**
     * Saved book to database.
     * @param book to save
     * @return	saved book
     */
    BookTo saveBook(BookTo book);
}
