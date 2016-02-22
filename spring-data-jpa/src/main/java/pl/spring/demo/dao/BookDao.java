package pl.spring.demo.dao;

import java.util.List;
import java.util.Set;

import pl.spring.demo.entity.BookEntity;

public interface BookDao {

    List<BookEntity> findAll();

    List<BookEntity> findBookByTitle(String title);

    List<BookEntity> findBooksByAuthor(String author);

    BookEntity save(BookEntity book);
    
    Set<BookEntity> getALL_BOOKS();
}
