package se.jonananas.tdd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

public class PersonRepositoryJDBC implements PersonRepository {

	@Inject
	public DataSource ds;

	@Override
	public void store(Person person) {
		String sql = "INSERT INTO appschema.person (ID) VALUES (?)";

		runQuery(sql.toString(), ds, statement -> {
			statement.setObject(1, person.getId());
			statement.execute();
			int updateCount = statement.getUpdateCount();
			if (updateCount != 1) {
				throw new RuntimeException("Insert failed, updateCount = " + updateCount);
			}
			return updateCount;
		});
	}

	private static <T> T runQuery(String sql, DataSource ds, SQLFunction<T> f) {
		return JDBCRunner.runQuery(sql, ds, f);
	}

	@Override
	public void findById(String id) {
		String sql = "SELECT * FROM appschema.person WHERE id = ?";

		runQuery(sql.toString(), ds, statement -> {
			statement.setObject(1, id);
			List<Person> result = new ArrayList<Person>();
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				result.add(new Person(rs.getString("id")));
			}
			return result;
		});
	}

}
