package se.jonananas.tdd;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

@RunWith(CdiRunner.class)
@AdditionalClasses(PersonRepositoryJPA.class)
public class PersonRepositoryJPATest extends PersonRepositoryTest {

		protected EntityManagerProvider sessionEm;
		protected EntityManager em;

		@Inject
//		@JPAPersistence
		PersonRepository jpaRepo;

		@Before
		public void setup() {
			this.sessionEm = new EntityManagerProviderImpl("H2SQLPU");
			this.em = sessionEm.createEntityManager();
			this.sessionEm.beginTransaction();
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
