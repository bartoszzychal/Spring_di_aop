package pl.spring.demo.bookmapper;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import pl.spring.demo.entity.AuthorEntity;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.BookTo;

@Component
public class BookMapper {
	
	private final String REGEX_FIRSTNAME_LASTNAME = " ";
	private final String REGEX_AUTHOR_AUTHOR = ",";
		
	public BookEntity bookToToBookEntity(BookTo bookTo){
		BookEntity bookEntity = new BookEntity();
		bookEntity.setId(bookTo.getId());
		bookEntity.setTitle(bookTo.getTitle());

		List<AuthorEntity> authors = Arrays.asList(bookTo.getAuthors().split(REGEX_AUTHOR_AUTHOR))
				.stream()
				.map((author) -> {
					List<String> firstLastName = Arrays.asList(author.split(REGEX_FIRSTNAME_LASTNAME));
					return new AuthorEntity(1L, firstLastName.get(0).trim(), firstLastName.get(1).trim());
				}).collect(Collectors.toList());
		
		bookEntity.setAuthors(authors);
		
		return bookEntity;
	}
	
	public BookTo bookEntityToBookTo(BookEntity bookEntity){
		BookTo bookTo = new BookTo();
		bookTo.setId(bookEntity.getId());
		bookTo.setTitle(bookEntity.getTitle());
		
		bookTo.setAuthors(
				bookEntity
				.getAuthors()
				.stream()
				.map(author->author.getFirstName()+REGEX_FIRSTNAME_LASTNAME+author.getLastName())
				.reduce((s1,s2)-> s1+ REGEX_AUTHOR_AUTHOR + s2).get());
				
		return bookTo;
	}
}
