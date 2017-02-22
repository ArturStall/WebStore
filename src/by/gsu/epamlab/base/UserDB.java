package by.gsu.epamlab.base;

import java.sql.*;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.model.Person;

public class UserDB {
	private Connection connection = null;
	private PreparedStatement pStatement = null;
	private ResultSet resultSet = null;
	private ResultSet rs_user_id = null;
	
	public synchronized Person addUser(String login, String name, String lastName, String email, String password) {		
		try {
			connection = ConnectionBase.getConnection();			
			rs_user_id = connection.prepareStatement(Constants.QUERY_INSERT_NEXT_ID_USER).executeQuery();
			rs_user_id.next();
			pStatement = connection.prepareStatement(Constants.QUERY_INSERT_USER);
			pStatement.setInt(1, rs_user_id.getInt(1));
			pStatement.setString(2, login);
			pStatement.setString(3, name);
			pStatement.setString(4, lastName);
			pStatement.setString(5, password);
			pStatement.setString(6, email);
			pStatement.executeUpdate();
			return new Person(login, name, lastName, email);
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			ConnectionBase.closeResultSet(rs_user_id);
			ConnectionBase.closeStatement(pStatement);
			ConnectionBase.closeConnection(connection);
		}		
	}

	public synchronized Person getUser(String login, String password) {		
		try {			
			connection = ConnectionBase.getConnection();
			pStatement = connection.prepareStatement(Constants.QUERY_SELECT_USER);
			pStatement.setString(1, login);
			pStatement.setString(2, password);
			resultSet = pStatement.executeQuery();
			resultSet.next();
			String name = resultSet.getString(Constants.KEY_NAME);
			String lastName = resultSet.getString(Constants.KEY_LAST_NAME);
			String email = resultSet.getString(Constants.KEY_EMAIL);
			return new Person(login, name, lastName, email);
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {			
			ConnectionBase.closeResultSet(resultSet);
			ConnectionBase.closeStatement(pStatement);
			ConnectionBase.closeConnection(connection);
		}
	}
}