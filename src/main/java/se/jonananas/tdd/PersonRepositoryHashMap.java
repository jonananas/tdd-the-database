package se.jonananas.tdd;

public class PersonRepositoryHashMap implements PersonRepository {

	private Person person;

	@Override
	public void store(Person person) {
		this.person = person;
	}

	@Override
	public Person findById(String id) {
		return person;
	}

}
