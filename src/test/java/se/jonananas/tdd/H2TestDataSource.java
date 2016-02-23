package se.jonananas.tdd;

import java.sql.SQLException;

import org.h2.jdbcx.JdbcDataSource;

public class H2TestDataSource {

	public static JdbcDataSource createH2MemDataSource(String filename) {
		JdbcDataSource dataSource = new JdbcDataSource();
		String url = "jdbc:h2:mem:test;MODE=DB2;DB_CLOSE_DELAY=1;";
		if (filename != null) {
			url += "INIT=runscript from '" + filename + "'";
		}
		dataSource.setURL(url);
		dataSource.setUser("sa");
		dataSource.setPassword("sa");
		return dataSource;
	}

	public static JdbcDataSource createH2MemDataSource() {
		return createH2MemDataSource(null);
	}

	public static void dropAllObjects() throws SQLException {
		createH2MemDataSource().getConnection().prepareStatement("DROP ALL OBJECTS").execute();
	}
}