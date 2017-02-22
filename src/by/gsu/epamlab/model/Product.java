package by.gsu.epamlab.model;

public class Product {
	private int idProduct;
	private String header;
	private String image;
	private String description;
	private int cost;	
	
	public Product() {
		super();
	}

	public Product(int idProduct, String header, String image, String description, int cost) {
		super();
		this.idProduct = idProduct;
		this.header = header;
		this.image = image;
		this.description = description;
		this.cost = cost;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}	
}