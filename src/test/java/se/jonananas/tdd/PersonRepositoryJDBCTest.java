package se.jonananas.tdd;

import java.sql.SQLException;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

@RunWith(CdiRunner.class)
@AdditionalClasses({ PersonRepositoryJDBC.class })
public class PersonRepositoryJDBCTest extends PersonRepositoryTest {

	@Produces
	public DataSource provideDataSource() {
		return H2TestDataSource.createH2FlywayDataSource();
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

	@Override
	protected PersonRepository repo() {
		return repo;
	}
}
