package NwaforInnocetIfenna.dao;
import NwaforInnocetIfenna.Connection.DBConnection;
import NwaforInnocetIfenna.model.User;

import com.mysql.cj.xdevapi.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static NwaforInnocetIfenna.Connection.DBConnection.getDBConnection;

public class UserDAO {



    public static ResultSet getAllUser() throws SQLException {
        String query = "select * from user_account";
        return getDBConnection().createStatement().executeQuery(query);
    }
    public static List<String> getAllUserEmail() throws SQLException {
        ResultSet allUser = getAllUser();
        List<String> userEmail = new ArrayList<>();
        while (allUser.next()) {
            userEmail.add(allUser.getString("email"));
        }
        return userEmail;
    }
    public boolean registerUser(User user) throws SQLException {
        boolean isSuccess = false;
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        String query = "insert into user_account(firstname,lastname,username,email,password,number)VALUES(?,?,?,?,?,?)";
        PreparedStatement statement = getDBConnection().prepareStatement(query);

        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastName());
        statement.setString(3, user.getUserName());
        statement.setString(4, user.getEmail());
        statement.setString(5, user.getPassword());
        statement.setString(6, user.getNumber());

        if(!getAllUserEmail().contains(user.getEmail())) {
            statement.executeUpdate();
            isSuccess = true;
            statement.close();
        }
        return isSuccess;
    }

    public static String getUserId(String email, String password) throws SQLException {
        String user_Id = null;
        String query = "select * from store.user_account where email = ? and password = ?";
        PreparedStatement statement = getDBConnection().prepareStatement(query);
        statement.setString(1, email);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            user_Id = resultSet.getString("id");
            statement.close();
        }
        return user_Id;
    }

    public static User getUserById(String user_Id) throws SQLException {
        User user = new User();
        String query = "select * from store.user_account where id = ?";
        PreparedStatement statement = getDBConnection().prepareStatement(query);
        statement.setString(1, user_Id);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()) {
            user.setFirstName(resultSet.getString("firstname"));
            user.setLastName(resultSet.getString("lastname"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            statement.close();
        }
        return user;
    }

    public static ResultSet loginUser(String email, String password) throws SQLException {
        boolean isSuccess = false;
        String query = "select id,username from store.user_account where email = ? and password = ?";
        PreparedStatement statement = getDBConnection().prepareStatement(query);
        statement.setString(1, email);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();

        return resultSet;
    }




    public static void main(String[] args) throws SQLException {
        UserDAO userDAO = new UserDAO();
//        User user = new User("moses", "Afolabi", "mosquito", "moses@gmail.com", "1234", "9088765");
//        userDAO.registerUser(user);
//        System.out.println("success");
        ResultSet resultSet = UserDAO.loginUser("moses@gmail.com","1234");
        while(resultSet.next()){
            System.out.println(resultSet.getInt("id")+", "+resultSet.getString("username"));
        }
    }

    }



