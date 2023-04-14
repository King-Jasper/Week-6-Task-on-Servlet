package NwaforInnocetIfenna.Connection;

import java.sql.*;

public class DBConnection {
    private static String url = "jdbc:mysql://localhost:3306/store";
    private static String username = "root";
    private static String password = "ifenna2020";

    public static  Connection getDBConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection Failed "+e.getMessage());

        }
        return connection;
    }

    public static void main(String[]args){
        Connection connection = getDBConnection();
        ResultSet resultSet = null;
        try{
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM store.product");
            while(resultSet.next()){
                System.out.println(resultSet.getString("id")+" "+resultSet.getString("name")+" "+resultSet.getString("price"));
            }
        }catch(SQLException e){
            System.out.println("Failed Test: "+e.getMessage());
        }

    }
}
