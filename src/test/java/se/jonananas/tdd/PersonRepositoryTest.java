package se.jonananas.tdd;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public abstract class PersonRepositoryTest {

	@Test
	public void shouldStorePerson() throws Exception {
		Person person = Person.create();
		repo().store(person);
	}

	@Test
	public void shouldFindPerson() throws Exception {
		Person person = Person.create();
		repo().store(person);
		Person found = repo().findById(person.getId());
		assertThat(found).isEqualTo(person);
	}

	protected abstract PersonRepository repo();
}
