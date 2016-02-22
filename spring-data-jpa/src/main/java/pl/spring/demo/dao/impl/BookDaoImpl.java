package pl.spring.demo.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entity.AuthorEntity;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.separators.Separators;

@Component
public class BookDaoImpl implements BookDao {
	
	private final Set<BookEntity> ALL_BOOKS = new HashSet<>();

	public BookDaoImpl() {
		addTestBooks();
	}

	@Override
	public List<BookEntity> findAll() {
		return new ArrayList<>(ALL_BOOKS);
	}

	@Override
	public List<BookEntity> findBookByTitle(String title) {
		return	ALL_BOOKS
				.stream()
				.filter((bookEntity)->bookEntity
						.getTitle()
						.toLowerCase()
						.startsWith(title.toLowerCase()))
        		.collect(Collectors.toList());
	}

	@Override
	public List<BookEntity> findBooksByAuthor(String author) {
		return ALL_BOOKS
				.stream()
				.filter((bookEntity)->bookEntity.getAuthors().stream()
					.anyMatch((authorEntity)->
						new StringBuilder()
						.append(authorEntity.getFirstName())
						.append(Separators.SPACE)
						.append(authorEntity.getLastName())
						.toString()
						.toLowerCase()
						.startsWith(author.toLowerCase())))
        		.collect(Collectors.toList());
	}

	@Override
	@NullableId
	public BookEntity save(BookEntity book) {
		ALL_BOOKS.add(book);
		return book;
	}

	@Override
	public Set<BookEntity> getALL_BOOKS() {
		return ALL_BOOKS;
	}

	private void addTestBooks() {
		ALL_BOOKS.add(new BookEntity(1L, "Romeo i Julia", Arrays.asList(new AuthorEntity(1L,"Wiliam", "Szekspir"))));
		ALL_BOOKS.add(new BookEntity(2L, "Opium w rosole", Arrays.asList(new AuthorEntity(2L,"Hanna", "Ożogowska"))));
		ALL_BOOKS.add(new BookEntity(3L, "Przygody Odyseusza", Arrays.asList(new AuthorEntity(3L,"Jan", "Parandowski"))));
		ALL_BOOKS.add(new BookEntity(4L, "Awantura w Niekłaju", Arrays.asList(new AuthorEntity(4L,"Edmund", "Niziurski"))));
		ALL_BOOKS.add(new BookEntity(5L, "Pan Samochodzik i Fantomas", Arrays.asList(new AuthorEntity(5L,"Zbigniew", "Nienacki"))));
		ALL_BOOKS.add(new BookEntity(6L, "Zemsta", Arrays.asList(new AuthorEntity(6L,"Aleksander", "Fredro"))));
	}
}
