package se.jonananas.tdd.jpaprovider;

import javax.persistence.EntityManager;

public interface EntityManagerProvider {

	public EntityManager createEntityManager();

	public void endTransaction();

	public void beginTransaction();

	public void commitTransaction();

	public void rollbackTransaction();
}