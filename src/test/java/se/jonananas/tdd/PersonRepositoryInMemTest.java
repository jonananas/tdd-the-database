package se.jonananas.tdd;

import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.runner.RunWith;

@RunWith(CdiRunner.class)
@AdditionalClasses({ PersonRepositoryHashMap.class })
public class PersonRepositoryInMemTest extends PersonRepositoryTest {

	@Inject
	PersonRepository repo;

	@Override
	protected PersonRepository repo() {
		return repo;
	}
}
