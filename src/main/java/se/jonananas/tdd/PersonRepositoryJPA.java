package se.jonananas.tdd;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class PersonRepositoryJPA implements PersonRepository {
	
	@Inject
	EntityManager em;

	@Override
	public void store(Person person) {
		em.persist(person);
	}

	@Override
	public Person findById(String id) {
		return em.find(Person.class, id);
	}
}
