package se.jonananas.tdd;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class PersonTest {

	@Test
	public void shouldCreateUniquePerson() {
		Person person = Person.create();
		Person other = Person.create();
		assertThat(person).isNotEqualTo(other);
		assertThat(person.getId()).isNotEqualTo(other.getId());
	}
	
	@Test
	public void shouldBeEqualWhenIDsEqual() {
		Person person = Person.create();
		Person same = Person.create();
		same.id = person.id;
		Person other = Person.create();
		assertThat(person).isEqualTo(same).isNotEqualTo(other).isNotEqualTo(new Object()).isNotEqualTo(null);
		assertThat(person.hashCode()).isEqualTo(same.hashCode()).isNotEqualTo(other.hashCode()).isNotEqualTo(new Object().hashCode());
	}

}
