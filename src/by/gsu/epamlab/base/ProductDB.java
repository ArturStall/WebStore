package by.gsu.epamlab.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.model.Product;
import by.gsu.epamlab.ws.CostProductSOAP;

public class ProductDB {
	private Connection connection = null;
	private PreparedStatement pStatement = null;
	private ResultSet resultSet = null;
	
	public synchronized Product getProduct(int idProduct) {		
		try {			
			connection = ConnectionBase.getConnection();
			pStatement = connection.prepareStatement(Constants.QUERY_SELECT_PRODUCT);
			pStatement.setInt(1, idProduct);
			resultSet = pStatement.executeQuery();
			resultSet.next();
			String header = resultSet.getString(Constants.KEY_HEADER);
			String image = resultSet.getString(Constants.KEY_IMAGE);
			String description = resultSet.getString(Constants.KEY_DESCRIPTION);
			int cost = new CostProductSOAP().getCost(idProduct);
			return new Product(idProduct, header, image, description, cost);
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {			
			ConnectionBase.closeResultSet(resultSet);
			ConnectionBase.closeStatement(pStatement);
			ConnectionBase.closeConnection(connection);
		}
	}

	public synchronized ArrayList<Product> getProducts() {
		try {
			ArrayList<Product> products = new ArrayList<>();
			connection = ConnectionBase.getConnection();
			pStatement = connection.prepareStatement(Constants.QUERY_SELECT_PRODUCTS);
			resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				int idProduct = resultSet.getInt(Constants.KEY_ID_PRODUCT);
				String header = resultSet.getString(Constants.KEY_HEADER);
				String image = resultSet.getString(Constants.KEY_IMAGE);
				String description = resultSet.getString(Constants.KEY_DESCRIPTION);
				int cost = new CostProductSOAP().getCost(idProduct);
				products.add(new Product(idProduct, header, image, description, cost));
			}
			return products;
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {			
			ConnectionBase.closeResultSet(resultSet);
			ConnectionBase.closeStatement(pStatement);
			ConnectionBase.closeConnection(connection);
		}		
	}
}