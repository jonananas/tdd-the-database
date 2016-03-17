package se.jonananas.tdd;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CdiRunner.class)
@AdditionalClasses({ PersonRepositoryJDBC.class })
public class PersonRepositoryJDBCTest {

	@Produces
	public DataSource provideDataSource() {
		return H2TestDataSource.createH2MemDataSource("create_db.sql");
	}

	@Inject
	PersonRepository repo;

	@Before
	public void setup() {
	}

	@After
	public void teardown() throws SQLException {
		H2TestDataSource.dropAllObjects();
	}
	
	@Test
	public void shouldStorePerson() throws Exception {
		Person person = Person.create();
		repo.store(person);
	}
	
	@Test
	public void shouldFindPerson() throws Exception {
		Person person = Person.create();
		repo.store(person);
		Person found = repo.findById(person.getId());
		assertThat(found).isEqualTo(person);
	}
}
