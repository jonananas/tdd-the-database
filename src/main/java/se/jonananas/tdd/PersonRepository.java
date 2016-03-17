package se.jonananas.tdd;

public interface PersonRepository {

	public void store(Person person);

	public Person findById(String id);
}
