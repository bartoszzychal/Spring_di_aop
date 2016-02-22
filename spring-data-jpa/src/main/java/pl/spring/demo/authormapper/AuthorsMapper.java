package pl.spring.demo.authormapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import pl.spring.demo.entity.AuthorEntity;

@Service
public class AuthorsMapper {
	
	private final String REGEX_SPACE_SEPARATOR = " ";
	private final String REGEX_COMMA_SEPARATOR = ",";
	
	public List<AuthorEntity> stringToListOfAuthorEntity(String authors) {
		return Arrays.asList(authors.split(REGEX_COMMA_SEPARATOR))
				.stream()
				.map((author) -> {
					List<String> firstAndLastname = Arrays.asList(author.trim().split(REGEX_SPACE_SEPARATOR,2));
					return new AuthorEntity(1L, firstAndLastname.get(0), firstAndLastname.get(1));
				}).collect(Collectors.toList());
	}
	
	public String listOfAuthorEntityToString(List<AuthorEntity> authors) {
		return authors
			.stream()
			.map(author-> new StringBuilder()
					.append(author.getFirstName())
					.append(REGEX_SPACE_SEPARATOR)
					.append(author.getLastName())
					.toString())
			.reduce((author1,author2)-> new StringBuilder()
					.append(author1)
					.append(REGEX_COMMA_SEPARATOR)
					.append(REGEX_SPACE_SEPARATOR)
					.append(author2)
					.toString())
			.get();
	}
}
