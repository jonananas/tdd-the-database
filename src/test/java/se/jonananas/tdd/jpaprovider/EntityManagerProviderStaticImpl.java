package se.jonananas.tdd.jpaprovider;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProviderStaticImpl implements EntityManagerProvider {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Map<String, Object> configOverrides;
	private static String persistenceProvider;

	public EntityManagerProviderStaticImpl() {
	}

	public EntityManagerProviderStaticImpl(String persistenceUnitName) {
		this(persistenceUnitName, null);
	}

	public EntityManagerProviderStaticImpl(String persistenceUnitName, Map<String, Object> configOverrides) {
		if (persistenceProvider != null && !persistenceProvider.equals(persistenceUnitName))
			throw new RuntimeException("Using more than one persistenceProvider is not supported!");
		persistenceProvider = persistenceUnitName;
		this.configOverrides = configOverrides;
	}

	protected void createEntityManagerFactory() {
		emf = Persistence.createEntityManagerFactory(persistenceProvider, configOverrides);
	}

	@Override
	public EntityManager createEntityManager() {
		if (emf == null)
			createEntityManagerFactory();
		this.em = emf.createEntityManager();
		return this.em;
	}

	@Override
	public void beginTransaction() {
		this.em.getTransaction().begin();
	}

	@Override
	public void endTransaction() {
		if (this.em != null) {
			this.em.close();
			this.em = null;
		}
	}

	@Override
	public void commitTransaction() {
		this.em.getTransaction().commit();
	}

	@Override
	public void rollbackTransaction() {
		this.em.getTransaction().rollback();
	}
}