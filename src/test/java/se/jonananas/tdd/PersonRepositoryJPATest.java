package se.jonananas.tdd;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.flywaydb.core.Flyway;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.After;
import org.junit.runner.RunWith;

import se.jonananas.tdd.jpaprovider.EntityManagerProvider;
import se.jonananas.tdd.jpaprovider.EntityManagerProviderImpl;

@RunWith(CdiRunner.class)
@AdditionalClasses(PersonRepositoryJPA.class)
public class PersonRepositoryJPATest extends PersonRepositoryTest {

	protected EntityManagerProvider sessionEm;
	protected EntityManager em;

	@Inject
	// @JPAPersistence
	PersonRepository jpaRepo;
	
	@Produces
	public EntityManager provideEM() {
		if (this.em == null) {
			updateSchema();
			setupEM();
		}
		return this.em;
	}

	private void setupEM() {
		this.sessionEm = new EntityManagerProviderImpl("H2SQLPU");
		this.em = sessionEm.createEntityManager();
		this.sessionEm.beginTransaction();
	}

	private void updateSchema() {
		Flyway flyway = new Flyway();
		// TODO: From persistence.xml, maven-filter this to create common source?
		flyway.setDataSource("jdbc:h2:mem:unit-testing-jpa", "sa", "sa");
		flyway.migrate();
	}

	@After
	public void tearDown() {
		this.sessionEm.rollbackTransaction();
		this.sessionEm.endTransaction();
	}

	@Override
	protected PersonRepository repo() {
		return jpaRepo;
	}
}
