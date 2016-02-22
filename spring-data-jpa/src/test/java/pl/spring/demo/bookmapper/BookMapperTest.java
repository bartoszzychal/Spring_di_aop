package pl.spring.demo.bookmapper;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import pl.spring.demo.authormapper.AuthorsMapper;
import pl.spring.demo.entity.AuthorEntity;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.BookTo;


public class BookMapperTest {

	@InjectMocks
	BookMapper bookMapper;
	@Mock
	AuthorsMapper authorsMapper;
		
	@Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
		
	@Test
	public void shouldReturnBookToForMapBookEntityToBookTo() {
		//given
		String authors = "firstName lastName";
		List<AuthorEntity> authorsList = Arrays.asList(new AuthorEntity(1L,"firstName","lastName"));
		
		BookEntity bookEntity = new BookEntity(1L,"title", authorsList);
		BookTo bookToExpected = new BookTo(1L, "title", authors);
		
		Mockito.when(authorsMapper.listOfAuthorEntityToString(authorsList)).thenReturn("firstName lastName");
		//when
		BookTo actual = bookMapper.bookEntityToBookTo(bookEntity);
		//then
		Mockito.verify(authorsMapper).listOfAuthorEntityToString(authorsList);
		assertEquals(bookToExpected.getId(), actual.getId());
		assertEquals(bookToExpected.getAuthors(), actual.getAuthors());
		assertEquals(bookToExpected.getTitle(), actual.getTitle());
	}


	@Test
	public void shouldReturnBookEnityForMapBookToToBookEntity() {
		//given
		String authors = "firstName von lastName";
		
		List<AuthorEntity> authorsList = Arrays.asList(new AuthorEntity(1L,"firstName","von lastName"));
		BookTo bookTo = new BookTo(1L, "title", "firstName von lastName");
		
		BookEntity bookEntityExpected = new BookEntity(1L,"title",authorsList);
		Mockito.when(authorsMapper.stringToListOfAuthorEntity(authors)).thenReturn(authorsList);
		//when
		BookEntity actual = bookMapper.bookToToBookEntity(bookTo);
		//then
		Mockito.verify(authorsMapper).stringToListOfAuthorEntity(authors);
		assertEquals(bookEntityExpected.getId(), actual.getId());
		assertEquals(bookEntityExpected.getTitle(), actual.getTitle());
		assertEquals(bookEntityExpected.getAuthors().get(0).getFirstName(), actual.getAuthors().get(0).getFirstName());
		assertEquals(bookEntityExpected.getAuthors().get(0).getLastName(), actual.getAuthors().get(0).getLastName());
		assertEquals(bookEntityExpected.getAuthors().get(0).getId(), actual.getAuthors().get(0).getId());
	}

}
