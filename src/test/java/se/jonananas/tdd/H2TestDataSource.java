package se.jonananas.tdd;

import java.sql.SQLException;

import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;

public class H2TestDataSource {
	private static String password = "sa";
	private static String user = "sa";
	private static String url = "jdbc:h2:mem:test;MODE=DB2;DB_CLOSE_DELAY=1;";

	public static JdbcDataSource createH2FlywayDataSource() {
		JdbcDataSource ds = createH2MemDataSource();
		updateSchema(ds);
		return ds;
	}

	private static void updateSchema(JdbcDataSource ds) {
		Flyway flyway = new Flyway();
		flyway.setDataSource(ds);
		flyway.migrate();
	}
	
	private static JdbcDataSource createH2MemDataSource() {
		JdbcDataSource dataSource = new JdbcDataSource();
		dataSource.setURL(url);
		dataSource.setUser(user);
		dataSource.setPassword(password);
		return dataSource;
	}

	public static void dropAllObjects() throws SQLException {
		createH2MemDataSource().getConnection().prepareStatement("DROP ALL OBJECTS").execute();
	}
}