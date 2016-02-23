package pl.spring.demo.mock;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import pl.spring.demo.authorsmapper.AuthorsMapper;
import pl.spring.demo.bookmapper.BookMapper;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entity.AuthorEntity;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.service.impl.BookServiceImpl;
import pl.spring.demo.to.BookTo;

public class BookServiceImplTest {

	@InjectMocks
	private BookServiceImpl bookService;
	@Mock
	private BookDao bookDao;
	@Mock
	private BookMapper bookMapper;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testShouldSaveBook() {
		// given
		String authors = "first last";
		List<AuthorEntity> authorsList = Arrays.asList(new AuthorEntity(1L, "first", "last"));
		String title = "title";

		BookTo bookTo = new BookTo(null, title, authors);
		BookEntity bookEntity = new BookEntity(null, title, authorsList);

		BookTo bookToExpected = new BookTo(1L, title, authors);
		BookEntity bookEntityExpected = new BookEntity(1L, title, authorsList);
		
		Mockito.when(bookMapper.bookToToBookEntity(bookTo)).thenReturn(bookEntity);
		Mockito.when(bookDao.save(bookEntity)).thenReturn(bookEntityExpected);
		Mockito.when(bookMapper.bookEntityToBookTo(bookEntityExpected)).thenReturn(bookToExpected);
		// when
		BookTo result = bookService.saveBook(bookTo);
		// then
		Mockito.verify(bookDao).save(bookEntity);
		Mockito.verify(bookMapper).bookToToBookEntity(bookTo);
		Mockito.verify(bookMapper).bookEntityToBookTo(bookEntityExpected);
		
		assertEquals(1L, result.getId().longValue());
	}
}
