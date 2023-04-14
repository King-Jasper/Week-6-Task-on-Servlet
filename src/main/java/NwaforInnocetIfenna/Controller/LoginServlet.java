package NwaforInnocetIfenna.Controller;

import NwaforInnocetIfenna.dao.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", value = "/Login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("Email");
        System.out.println(email);
        String password = request.getParameter("Password");
        System.out.println(password);
        RequestDispatcher requestDispatcher = null;
        ResultSet resultSet = null;
        boolean isSuccess = false;
        int user_Id = 0;
        String username = "";
        try {
            resultSet = UserDAO.loginUser(email, password);
            HttpSession session = request.getSession();
            while(resultSet.next()){
                user_Id = resultSet.getInt("id");
                username = resultSet.getString("username");
                isSuccess = true;
            }
            if(username.equals("Admin")){
                requestDispatcher = request.getRequestDispatcher("Admin.jsp");
                System.out.println("forwarded to admin");
            }else{
                if(isSuccess) {
                    session.setAttribute("username", username);
                    session.setAttribute("user_Id", user_Id);
                    session.setAttribute("status","success");
                    requestDispatcher = request.getRequestDispatcher("index.jsp");
                }else{
                    session.setAttribute("status","failed");
                    requestDispatcher = request.getRequestDispatcher("login.jsp");
                }
            }

            requestDispatcher.forward(request,response);
        } catch (ServletException | SQLException e) {
            System.out.println("Error in User Login "+e.getMessage());
        }
    }

}
