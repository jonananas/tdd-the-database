package se.jonananas.tdd;

public interface PersonRepository {

	public void store(Person person);

	public void findById(String id);
}
