package by.gsu.epamlab.services;

import java.util.ArrayList;

import javax.servlet.http.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.json.*;

import by.gsu.epamlab.base.ProductDB;
import by.gsu.epamlab.base.UserDB;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.model.Person;
import by.gsu.epamlab.model.Product;

@Path("/user")
public class REST {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String addPerson(String json, @Context HttpServletRequest request,  @Context HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		JSONObject jsObj = new JSONObject(json);
		String name = jsObj.getString(Constants.KEY_NAME);
		String lastName = jsObj.getString(Constants.KEY_LAST_NAME);
		String login = jsObj.getString(Constants.KEY_LOGIN);
		String email = jsObj.getString(Constants.KEY_EMAIL);
		String password = jsObj.getString(Constants.KEY_PASSWORD);
		new UserDB().addUser(login, name, lastName, email, password);
		request.getSession();
		return jsObj.toString();
	}
	
	@POST
	@Path("/{login}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getPerson(String json, @PathParam(Constants.KEY_LOGIN) String login, @Context HttpServletRequest request, @Context HttpServletResponse response) {		
		response.setHeader("Access-Control-Allow-Origin", "*");
		JSONObject jsObj = new JSONObject(json);
		String password = jsObj.getString(Constants.KEY_PASSWORD);
		Person person = new UserDB().getUser(login, password);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put(Constants.KEY_LOGIN, person.getLogin());
		jsonObj.put(Constants.KEY_NAME, person.getName());
		jsonObj.put(Constants.KEY_LAST_NAME, person.getLastName());
		jsonObj.put(Constants.KEY_EMAIL, person.getEmail());
		jsonObj.put(Constants.KEY_PHOTO, Constants.DEFAULT_PHOTO);
		return jsonObj.toString();
	}
	
	@GET
	@Path("/product/{idProduct}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getProduct(@PathParam(Constants.KEY_ID_PRODUCT) int idProduct, @Context HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Product product = new ProductDB().getProduct(idProduct);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put(Constants.KEY_HEADER, product.getHeader());
		jsonObj.put(Constants.KEY_IMAGE, product.getImage());
		jsonObj.put(Constants.KEY_DESCRIPTION, product.getDescription());
		jsonObj.put(Constants.KEY_COST, product.getCost());
		return jsonObj.toString();
	}
	
	@GET
	@Path("/products")
	@Produces(MediaType.APPLICATION_JSON)
	public String getProducts(@Context HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");		
		ArrayList<Product> products = new ProductDB().getProducts();
		JSONArray arrProd = new JSONArray();
		for (int i = 0; i < products.size(); i++) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put(Constants.KEY_ID_PRODUCT, products.get(i).getIdProduct());
			jsonObj.put(Constants.KEY_HEADER, products.get(i).getHeader());
			jsonObj.put(Constants.KEY_IMAGE, products.get(i).getImage());
			jsonObj.put(Constants.KEY_DESCRIPTION, products.get(i).getDescription());
			jsonObj.put(Constants.KEY_COST, products.get(i).getCost());
			arrProd.put(jsonObj);
		}
		return new JSONObject().put(Constants.KEY_PRODUCTS_ARRAY, arrProd).toString();
	}
}