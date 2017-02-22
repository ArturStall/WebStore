package by.gsu.epamlab.base;

import java.sql.*;

import by.gsu.epamlab.constants.Constants;

public class ConnectionBase {
	
	static {
		try {
			Class.forName(Constants.JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(Constants.DATA_BASE, Constants.USER_DATA_BASE, Constants.PASSWORD_DATA_BASE);
	}
	
	public static void closeResultSet(ResultSet ... resultSets) {
		for (ResultSet resultSet : resultSets) {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					System.err.println("Resource closing problem : " + e);
				}
			}
		}
	}
	
	public static void closeStatement(Statement ... statements) {
		for (Statement statemet : statements) {
			if (statemet != null) {
				try {
					statemet.close();
				} catch (SQLException e) {
					System.err.println("Resource closing problem : " + e);
				}
			}
		}
	}
	
	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				System.err.println("Resource closing problem : " + e);
			}
		}		
	}
}