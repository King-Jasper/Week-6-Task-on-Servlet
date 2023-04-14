package NwaforInnocetIfenna.dao;


//Add/remove a product to a cart

        // View a product in a category and as a sole product

import NwaforInnocetIfenna.Connection.DBConnection;
import NwaforInnocetIfenna.model.Product;
import NwaforInnocetIfenna.utility.CustomException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {


    static Connection dbConnection = DBConnection.getDBConnection();
    public boolean addProduct(Product product) throws SQLException, CustomException {
        boolean successful = false;
//
        String addGoods = "insert into product(name, category, price, quantity, image) values(?,?,?,?,?)";

        PreparedStatement statement = dbConnection.prepareStatement(addGoods);
        statement.setString(1, product.getName());
        statement.setString(2, product.getCategory());
        statement.setDouble(3, product.getPrice());
        statement.setInt(4, product.getQuantity());
        statement.setString(5, product.getImage());
        return statement.executeUpdate()>0;
    }
    public static ArrayList<Product> listOfProduct() throws SQLException {
        Statement statement = dbConnection.createStatement();
        ArrayList<Product> products = new ArrayList<>();
        ResultSet result = statement.executeQuery("SELECT * FROM product");

        while (result.next()){
            products.add(new Product(result.getInt("id"),
                    result.getString("name"),
                    result.getString("category"),
                    result.getInt("quantity"),
                    result.getDouble("price"),
                    result.getString("image")
            ));

        }
        return products;

    }
    public boolean removeProduct(int product_id) throws SQLException, CustomException {
        String removeGoods = "delete from product where id = ?";

        PreparedStatement statement = dbConnection.prepareStatement(removeGoods);
        statement.setInt(1, product_id);
        return statement.executeUpdate()>0;
    }
    public boolean setProductPrice(double newPrice, int product_id) throws SQLException {
        String query = "UPDATE product SET price = ? WHERE id = ?";
        PreparedStatement pst = dbConnection.prepareStatement(query);
        pst.setDouble(1, newPrice);
        pst.setInt(2, product_id);
        return pst.executeUpdate()>0;
    }
    public boolean setProductQuantity(int quantity, int product_id) throws SQLException {
        String query = "UPDATE product SET quantity = ? WHERE id = ?";
        PreparedStatement pst = dbConnection.prepareStatement(query);
        pst.setInt(1, quantity);
        pst.setInt(2, product_id);
        return pst.executeUpdate() > 0;
    }

    public List<Product> search(String query, String type) throws SQLException {
        List<Product> result = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE ";
        switch (type) {
            case "id":
                sql += "id = ?";
                break;
            case "name":
                sql += "name = ?";
                break;
            case "category":
                sql += "category = ?";
                break;
            case "price":
                sql += "price = ?";
        }
        PreparedStatement statement = dbConnection.prepareStatement(sql);
        statement.setString(1, query);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String category = resultSet.getString("category");
            double price = resultSet.getDouble("price");
            int quantity = resultSet.getInt("quantity");
            String image = resultSet.getString("image");

            Product product = new Product(id, name, category, quantity, price, image);
            result.add(product);
        }
        return result;
    }

    public static void main(String[] args) throws SQLException, CustomException {
        AdminDAO adminDOA = new AdminDAO();
        List<Product> result = listOfProduct();
        System.out.println(result.get(0).getQuantity());
        System.out.println(result.get(0).getPrice());
        System.out.println();
        System.out.println(adminDOA.setProductPrice(1500,1));
        System.out.println(adminDOA.setProductQuantity(300,1));
        System.out.println();
        List<Product> result2 = listOfProduct();
        System.out.println(result2.get(0).getQuantity());
        System.out.println(result2.get(0).getPrice());
//
        System.out.println(adminDOA.removeProduct(1));

    }

}

