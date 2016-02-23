package pl.spring.demo.dao;

import java.util.List;
import java.util.Set;

import pl.spring.demo.entity.BookEntity;

public interface BookDao {

	/**
	 * 
	 * @return all books
	 */
    List<BookEntity> findAll();

    /**
     * Return all books, which title start with prefix get like a parameter prefix_title.
     * 
     * Example:
     * prefix_title - "Rom"
     * find books with title start with prefix "Rom":
     * "Romeo i Julia"
     * "Rome"
     * 
     * Ignore the font size.
     * @param prefix_title prefix to find book
     * @return	list of BookEntity, which title start with prefix
     */
    List<BookEntity> findBookByTitle(String prefix_title);

    /**
     * Return all book, which some authors start with prefix get like a parameter prefix_author.
     * The first name and last name are concat with space separator.
     * 
     * Example 1:
     * prefix_author - "Wili"
     * find books with some author start with "Wili"
     * "Wiliam Szekspir"
     * "William Boyd"
     * 
     * Example 2:
     * prefix_author - "Wiliam S"
     * find books with some author start with "Wiliam S"
     * "Wiliam Szekspir"
     * 
     * Ignore the font size.
     * @param prefix_author prefix to find book
     * @return	list of BookEntity, which some author start witj prefix
     */
    List<BookEntity> findBooksByAuthor(String prefix_author);

    /**
     * Save book in datebase.
     * @param book book to save
     * @return	saved book
     */
    BookEntity save(BookEntity book);
    
    /**
     * 
     * @return All books saved in database
     */
    Set<BookEntity> getALL_BOOKS();
}
