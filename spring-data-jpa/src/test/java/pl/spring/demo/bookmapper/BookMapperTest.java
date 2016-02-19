package pl.spring.demo.bookmapper;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import pl.spring.demo.entity.AuthorEntity;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.BookTo;


public class BookMapperTest {

	@InjectMocks
	BookMapper bookMapper;
	
	@Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void shouldReturnBookToForMapBookEntityToBookTo() {
		//given
		BookEntity bookEntity = new BookEntity(1L,"title",Arrays.asList(new AuthorEntity(1L,"firstName","lastName")));
		BookTo bookToExpected = new BookTo(1L, "title", "firstName lastName");
		//when
		BookTo actual = bookMapper.bookEntityToBookTo(bookEntity);
		//then
		assertEquals(bookToExpected.getId(), actual.getId());
		assertEquals(bookToExpected.getAuthors(), actual.getAuthors());
		assertEquals(bookToExpected.getTitle(), actual.getTitle());
	}

	@Test
	public void shouldReturnBookEnityForMapBookToToBookEntity() {
		//given
		BookTo bookTo = new BookTo(1L, "title", "firstName lastName,firstName lastName");
		BookEntity bookEntityExpected = new BookEntity(1L,"title",Arrays.asList(new AuthorEntity(1L,"firstName","lastName"),new AuthorEntity(1L,"firstName","lastName")));
		//when
		BookEntity actual = bookMapper.bookToToBookEntity(bookTo);
		//then
		assertEquals(bookEntityExpected.getId(), actual.getId());
		assertEquals(bookEntityExpected.getTitle(), actual.getTitle());
		assertEquals(bookEntityExpected.getAuthors().get(0).getFirstName(), actual.getAuthors().get(0).getFirstName());
		assertEquals(bookEntityExpected.getAuthors().get(0).getLastName(), actual.getAuthors().get(0).getLastName());
		assertEquals(bookEntityExpected.getAuthors().get(0).getId(), actual.getAuthors().get(0).getId());
		assertEquals(bookEntityExpected.getAuthors().get(1).getFirstName(), actual.getAuthors().get(1).getFirstName());
		assertEquals(bookEntityExpected.getAuthors().get(1).getLastName(), actual.getAuthors().get(1).getLastName());
		assertEquals(bookEntityExpected.getAuthors().get(1).getId(), actual.getAuthors().get(1).getId());
	}

}
