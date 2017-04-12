package se.jonananas.tdd;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(CdiRunner.class)
public class JDBCRunnerTest {

	@Mock
	DataSource ds;
	@Mock
	Connection connection;
	@Mock
	PreparedStatement statement;

	Logger log = LoggerFactory.getLogger(JDBCRunner.class);

	@Test
	public void shouldCloseStatementAndConnectionOnException() throws Exception {
		when(ds.getConnection()).thenReturn(connection);
		when(connection.prepareStatement(Mockito.anyString())).thenReturn(statement);
		doThrow(new SQLException("connection")).when(connection).close();
		doThrow(new SQLException("statement")).when(statement).close();

		try {
			JDBCRunner.runQuery("", ds, statementParam -> {
				throw new RuntimeException("lambda");
			});
			fail("Should not be reached!");
		} catch (Exception ex) {
			assertThat(ex.getMessage()).isEqualTo("lambda");
			assertThat(ex.getSuppressed().length).isEqualTo(2);
			assertThat(getCauses(ex.getSuppressed())).contains("connection", "statement");
		}

		verify(connection, Mockito.times(1)).close();
		verify(statement, Mockito.times(1)).close();
	}

	private List<String> getCauses(Throwable[] suppressed) {
		return Arrays.asList(suppressed).stream().map(ex -> ex.getMessage()).collect(Collectors.toList());
	}

}
