package pl.spring.demo.bookmapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.spring.demo.authormapper.AuthorsMapper;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.BookTo;

@Service
public class BookMapper {
	
	@Autowired
	AuthorsMapper authorsMapper;
	
	public BookEntity bookToToBookEntity(BookTo bookTo){
		BookEntity bookEntity = new BookEntity();
		bookEntity.setId(bookTo.getId());
		bookEntity.setTitle(bookTo.getTitle());		
		bookEntity.setAuthors(authorsMapper.stringToListOfAuthorEntity(bookTo.getAuthors()));
		return bookEntity;
	}
	
	public BookTo bookEntityToBookTo(BookEntity bookEntity){
		BookTo bookTo = new BookTo();
		bookTo.setId(bookEntity.getId());
		bookTo.setTitle(bookEntity.getTitle());
		bookTo.setAuthors(authorsMapper.listOfAuthorEntityToString(bookEntity.getAuthors()));
		return bookTo;
	}

}
