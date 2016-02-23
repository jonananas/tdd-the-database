package se.jonananas.tdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.persistence.PersistenceException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Runs a query, and closes all resources if anything goes wrong.
 */
public class JDBCRunner {

	private final static Logger log = LoggerFactory.getLogger(JDBCRunner.class.getName());

	public static <T> T runQuery(String sql, DataSource ds, SQLFunction<T> f) {
		log.debug(sql);
		try (Connection connection = ds.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
			return f.apply(statement);
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}
}