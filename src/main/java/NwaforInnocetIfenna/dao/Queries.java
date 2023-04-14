package NwaforInnocetIfenna.dao;

public class Queries {
    protected String addProduct = "INSERT INTO store.product (product_name, price, quantity) VALUES (?, ?, ?)";
    protected String getProductUpdate = "UPDATE store.product SET quantity = ? WHERE product_id = ?";
   protected String deleteProduct = "DELETE store.product FROM = ? WHERE product_id = ?";
   protected String UpdateProductPrice = "UPDATE store.product SET price = ? WHERE id = ?";


    protected  String getUserByUserName = "SELECT * from users where username = ?";

   protected String userAddToCart = "INSERT INTO cart (user_id, product_id, quantity) VALUES (?, ?, ?)";


}
