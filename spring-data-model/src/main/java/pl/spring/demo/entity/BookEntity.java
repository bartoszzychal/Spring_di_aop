package pl.spring.demo.entity;

import java.util.List;

import pl.spring.demo.to.IdAware;

public class BookEntity implements IdAware {
	
    private Long id;
    private String title;
    private List<AuthorEntity> authors;
    
    public BookEntity() {
	}

    public BookEntity(Long id, String title, List<AuthorEntity> authors) {
        this.id = id;
        this.title = title;
        this.authors = authors;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

	public List<AuthorEntity> getAuthors() {
		return authors;
	}

	public void setAuthors(List<AuthorEntity> authors) {
		this.authors = authors;
	}

}
