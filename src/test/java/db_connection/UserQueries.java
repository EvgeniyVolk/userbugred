package db_connection;

import java.sql.ResultSet;
import java.sql.SQLException;

import static db_connection.SetupJdbcConnection.connection;
import static db_connection.SetupJdbcConnection.statement;
import static db_connection.SetupJdbcConnection.result;
import static org.testng.Assert.assertTrue;

public class UserQueries {

    public void dbQuery(String query) throws SQLException {

        SetupJdbcConnection setupJdbcConnection = new SetupJdbcConnection();
        setupJdbcConnection.init();

        statement = connection.createStatement();
        // executing query
        statement.executeUpdate(query);
    }

    public void selectQuery(String query) throws SQLException {
        SetupJdbcConnection setupJdbcConnection = new SetupJdbcConnection();
        setupJdbcConnection.init();

        statement = connection.createStatement();
        result = statement.executeQuery(query);
        result.next();
    }
}
