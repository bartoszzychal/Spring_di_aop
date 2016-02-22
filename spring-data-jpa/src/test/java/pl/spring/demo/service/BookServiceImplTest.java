package pl.spring.demo.service;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.BookTo;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class BookServiceImplTest {

    @Autowired
    private BookService bookService;
    @Autowired
    private CacheManager cacheManager;

    @Before
    public void setUp() {
        cacheManager.getCache("booksCache").clear();
    }

    @Test
    public void testShouldFindAllBooks() {
        // when
        List<BookTo> allBooks = bookService.findAllBooks();
        // then
        assertNotNull(allBooks);
        assertFalse(allBooks.isEmpty());
        assertEquals(6, allBooks.size());
    }

    @Test
    public void testShouldFindAllBooksByTitle() {
        // given
        final String title = "Opium w rosole";
        // when
        List<BookTo> booksByTitle = bookService.findBooksByTitle(title);
        // then
        assertNotNull(booksByTitle);
        assertFalse(booksByTitle.isEmpty());
       assertEquals(title, booksByTitle.get(0).getTitle());
    }
    @Test
    
    public void testShouldFindAllBooksByTitlePrefix() {
    	// given
    	final String title_prefix = "Opium";
    	// when
    	List<BookTo> booksByTitle = bookService.findBooksByTitle(title_prefix);
    	// then
    	assertNotNull(booksByTitle);
    	assertFalse(booksByTitle.isEmpty());
    	assertTrue(booksByTitle.get(0).getTitle().startsWith(title_prefix));
    }
    
    @Test
    public void testShouldFindAllBooksByAuthor() {
    	// given
    	final String author = "Wiliam Szekspir";
    	// when
    	List<BookTo> booksByAuthor = bookService.findBooksByAuthor(author);
    	// then
    	assertNotNull(booksByAuthor);
    	assertFalse(booksByAuthor.isEmpty());
    	assertEquals(author, booksByAuthor.get(0).getAuthors().substring(0, author.length()));
    }
    
    @Test
    public void testShouldFindAllBooksByAuthorPrefix() {
    	// given
    	final String author = "Wiliam";
    	// when
    	List<BookTo> booksByAuthor = bookService.findBooksByAuthor(author);
    	// then
    	assertNotNull(booksByAuthor);
    	assertFalse(booksByAuthor.isEmpty());
    	assertEquals(author, booksByAuthor.get(0).getAuthors().substring(0, author.length()));
    }

    @Test
    public void testShouldFindAllBooksByAuthorFirstNameAndLastNamePrefix() {
    	// given
    	final String author = "Wiliam S";
    	// when
    	List<BookTo> booksByAuthor = bookService.findBooksByAuthor(author);
    	// then
    	assertNotNull(booksByAuthor);
    	assertFalse(booksByAuthor.isEmpty());
    	assertEquals(author, booksByAuthor.get(0).getAuthors().substring(0, author.length()));
    }

    @Test(expected = BookNotNullIdException.class)
    public void testShouldThrowBookNotNullIdException() {
        // given
        final BookTo bookToSave = new BookTo();
        bookToSave.setAuthors("first last");
        bookToSave.setTitle("title");
        bookToSave.setId(22L);
        // when
        bookService.saveBook(bookToSave);
        // then
        fail("test should throw BookNotNullIdException");
    }
}
