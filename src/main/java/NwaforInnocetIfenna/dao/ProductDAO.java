package NwaforInnocetIfenna.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductDAO {
    Connection conn;

    public void addToCart(int userId, int productId, int quantity) throws SQLException {
        String query = "INSERT INTO cart (id, postid,productname, category,qty) VALUES (?, ?, ?,?,?)";

        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, userId);
        statement.setInt(2, productId);
        statement.setInt(3, quantity);
        statement.executeUpdate();
    }
}
