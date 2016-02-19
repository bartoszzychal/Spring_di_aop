package pl.spring.demo.mock;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

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

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShouldSaveBook() {
        // given
        BookTo book = new BookTo(null, "title", "author");
        BookEntity bookEntity = new BookEntity(null, "title", Arrays.asList(new AuthorEntity(1L,"author","")));
        Mockito.when(bookDao.save(bookEntity)).thenReturn(new BookEntity(1L, "title", Arrays.asList(new AuthorEntity(1L,"author",""))));
        // when
        BookTo result = bookService.saveBook(book);
        // then
        Mockito.verify(bookDao).save(bookEntity);
        assertEquals(1L, result.getId().longValue());
    }
}
