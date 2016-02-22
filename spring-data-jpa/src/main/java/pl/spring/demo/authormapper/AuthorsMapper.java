package pl.spring.demo.authormapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import pl.spring.demo.entity.AuthorEntity;
import pl.spring.demo.separators.Separators;

@Service
public class AuthorsMapper {
	
	public List<AuthorEntity> stringToListOfAuthorEntity(String authors) {
		return Arrays.asList(authors.split(Separators.COMMA))
				.stream()
				.map((author) -> {
					List<String> firstAndLastname = Arrays.asList(author.trim().split(Separators.SPACE,2));
					return new AuthorEntity(1L, firstAndLastname.get(0), firstAndLastname.get(1));
				}).collect(Collectors.toList());
	}
	
	public String listOfAuthorEntityToString(List<AuthorEntity> authors) {
		return authors
			.stream()
			.map(author-> new StringBuilder()
					.append(author.getFirstName())
					.append(Separators.SPACE)
					.append(author.getLastName())
					.toString())
			.reduce((author1,author2)-> new StringBuilder()
					.append(author1)
					.append(Separators.COMMA)
					.append(Separators.SPACE)
					.append(author2)
					.toString())
			.get();
	}
}
