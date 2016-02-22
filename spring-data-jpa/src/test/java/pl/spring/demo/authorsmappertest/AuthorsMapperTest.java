package pl.spring.demo.authorsmappertest;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import pl.spring.demo.authorsmapper.AuthorsMapper;
import pl.spring.demo.entity.AuthorEntity;

public class AuthorsMapperTest {
	
	@InjectMocks
	AuthorsMapper authorMapper;
	
	@Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void shouldReturnStringForAuthorEntity(){
		//given
		List<AuthorEntity> author = Arrays.asList(new AuthorEntity(1L,"firstName","lastName"));
		//expected
		String expected = "firstName lastName";
		//when
		String actual = authorMapper.listOfAuthorEntityToString(author);
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void shouldReturnListOfAuthorEntityForString(){
		//given
		String author = "firstName lastName";
		//expected
		List<AuthorEntity> expected = Arrays.asList(new AuthorEntity(1L,"firstName","lastName"));
		//when
		List<AuthorEntity> actual = authorMapper.stringToListOfAuthorEntity(author);
		//then
		assertEquals(expected.get(0).getFirstName(), actual.get(0).getFirstName());
		assertEquals(expected.get(0).getLastName(), actual.get(0).getLastName());
	}
	
	@Test
	public void shouldReturnStringForAuthorEntities(){
		//given
		List<AuthorEntity> authors = Arrays.asList(new AuthorEntity(1L,"firstName","lastName"),new AuthorEntity(1L,"firstName","lastName") );
		//expected
		String expected = "firstName lastName, firstName lastName";
		//when
		String actual = authorMapper.listOfAuthorEntityToString(authors);
		//then
		assertEquals(expected,actual);
	}
	
	@Test
	public void shouldReturnListOfAuthorEntityForStringWithTwoAuthors(){
		//given
		String authors = "firstName lastName, firstName lastName";
		//expected
		List<AuthorEntity> expected = Arrays.asList(new AuthorEntity(1L,"firstName","lastName"),new AuthorEntity(1L,"firstName","lastName") );
		//when
		List<AuthorEntity> actual = authorMapper.stringToListOfAuthorEntity(authors);
		//then
		assertTrue(actual.size() == 2);
		assertEquals(expected.get(0).getFirstName(), actual.get(0).getFirstName());
		assertEquals(expected.get(0).getLastName(), actual.get(0).getLastName());
		assertEquals(expected.get(1).getFirstName(), actual.get(1).getFirstName());
		assertEquals(expected.get(1).getLastName(), actual.get(1).getLastName());
	}
	
}
