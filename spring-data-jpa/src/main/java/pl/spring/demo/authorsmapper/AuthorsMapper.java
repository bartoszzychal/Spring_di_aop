package pl.spring.demo.authorsmapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import pl.spring.demo.entity.AuthorEntity;
import pl.spring.demo.separators.Separators;

@Component
public class AuthorsMapper {
	
	/**
	 * Map string with author separated by comma to list of AuthorEntity.
	 * Space after and between authors are skipped,
	 * so for example:
	 * "firstname lastname, firstname last name",
	 * it's the same what
	 * "firstname lastname,        firstname last name".
	 * @param authors authors(Example firstname lastname, firstname last name)
	 * @return	list of AuthorEntity or empty list list if authors doesn't exist.
	 */
	public List<AuthorEntity> stringToListOfAuthorEntity(String authors) {
		return Arrays.asList(authors.split(Separators.COMMA))
				.stream()
				.map((author) -> {
					List<String> firstAndLastname = Arrays.asList(author.trim().split(Separators.SPACE,2));
					return new AuthorEntity(1L, firstAndLastname.get(0), firstAndLastname.get(1));
				}).collect(Collectors.toList());
	}
	
	/**
	 * Map AuthorEntity to string saved as:
	 * firstname lastname, firstname last name.
	 * If is only one author string will be saved as:
	 * firstname lastname.
	 * @param authors list of AuthorEntity
	 * @return	String with authors saved as firstname lastname, firstname last name
	 * @throws NoSuchElementException if authors doesn't exist.
	 */
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
