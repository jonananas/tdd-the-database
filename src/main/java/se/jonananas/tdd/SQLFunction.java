package se.jonananas.tdd;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
interface SQLFunction<R> {

	R apply(PreparedStatement t) throws SQLException;
}