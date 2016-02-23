package pl.spring.demo.bookmapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.spring.demo.authorsmapper.AuthorsMapper;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.BookTo;

@Component
public class BookMapper {
	
	@Autowired
	AuthorsMapper authorsMapper;
	
	/**
	 * Map BookTo to BookEntity.
	 * To map authors use AuthorsMapper @see AuthorsMapper
	 * @param bookTo object to map
	 * @return BookEntity
	 */
	public BookEntity bookToToBookEntity(BookTo bookTo){
		BookEntity bookEntity = new BookEntity();
		bookEntity.setId(bookTo.getId());
		bookEntity.setTitle(bookTo.getTitle());		
		bookEntity.setAuthors(authorsMapper.stringToListOfAuthorEntity(bookTo.getAuthors()));
		return bookEntity;
	}
	
	/**
	 * Map BookEntity to BookTo.
	 * To map authors use AuthorsMapper @see AuthorsMapper
	 * @param bookEntity object to map
	 * @return BookTo
	 */
	public BookTo bookEntityToBookTo(BookEntity bookEntity){
		BookTo bookTo = new BookTo();
		bookTo.setId(bookEntity.getId());
		bookTo.setTitle(bookEntity.getTitle());
		bookTo.setAuthors(authorsMapper.listOfAuthorEntityToString(bookEntity.getAuthors()));
		return bookTo;
	}

}
