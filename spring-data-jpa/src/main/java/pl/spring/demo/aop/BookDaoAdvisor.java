package pl.spring.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.BookTo;
import pl.spring.demo.to.IdAware;

@Component
@Aspect
public class BookDaoAdvisor {
	
	@Autowired
	private Sequence sequence;
	
	@Autowired
	private BookDao bookDao;
	

	@Pointcut("@annotation(pl.spring.demo.annotation.NullableId)")
	public void pointCutForNullableIdAnnotation() {
	}

	@Before("pointCutForNullableIdAnnotation()")
	public void beforeNullableId(JoinPoint joinPoint) {
		checkNotNullId(joinPoint.getArgs()[0]);
	}
	
	private void checkNotNullId(Object o) {
		if (o instanceof IdAware && ((IdAware) o).getId() != null) {
			throw new BookNotNullIdException();
		}
	}
	
	@Pointcut("execution(public * pl.spring.demo.dao.BookDao.save(pl.spring.demo.entity.BookEntity))")
	public void setIdForSaveBook(){
	}
	
	@Before("setIdForSaveBook()")
	public void setId(JoinPoint joinPoint){
		BookTo book = (BookTo) joinPoint.getArgs()[0];
		if (book.getId() == null) {
			book.setId(sequence.nextValue(bookDao.getALL_BOOKS()));
		}
	}

}
