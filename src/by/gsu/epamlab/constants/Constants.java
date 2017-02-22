package by.gsu.epamlab.constants;

public class Constants {
    public static final String KEY_HEADER = "header";
    public static final String KEY_ID_PRODUCT = "idProduct";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_COST = "cost";
    public static final String KEY_PRODUCTS_ARRAY = "productsArray";
    public static final String KEY_PHOTO = "photo";
    public static final String KEY_LOGIN = "login";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_NAME = "name";
    public static final String KEY_LAST_NAME = "lastName";
    public static final String KEY_EMAIL = "email";    
    
    public static final String DEFAULT_PHOTO = "https://s-media-cache-ak0.pinimg.com/236x/1c/d6/8c/1cd68cf1696cae52cd45db862c73701d.jpg";
    
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DATA_BASE = "jdbc:mysql://localhost/webstore";
    public static final String USER_DATA_BASE = "artur";
    public static final String PASSWORD_DATA_BASE = "123";
    
    public static final String QUERY_SELECT_PRODUCT = "SELECT * FROM products WHERE idProduct = ?";
    public static final String QUERY_SELECT_PRODUCTS = "SELECT * FROM products";
    public static final String QUERY_SELECT_USER = "SELECT * FROM users WHERE login = ? AND password = ?";
    public static final String QUERY_INSERT_USER = "INSERT INTO users (idUser, login, name, lastName, password, email) VALUES (?, ? , ?, ? , ?, ?)";
    public static final String QUERY_INSERT_NEXT_ID_USER = "SELECT (MAX(idUser) + 1) FROM users";
}