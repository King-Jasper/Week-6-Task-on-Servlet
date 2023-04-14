package NwaforInnocetIfenna.Controller;
import NwaforInnocetIfenna.dao.UserDAO;

import NwaforInnocetIfenna.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "helloServlet", value = "/SignUp")
public class SignUpServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String userName = req.getParameter("Username");
        String password = req.getParameter("Password");
        String email = req.getParameter("Email");
        String number = req.getParameter("Phone");


        User user = new User(firstName, lastName, userName,email,password,number);
        UserDAO userDAO = new UserDAO();
//        try {
//            userDAO.registerUser(user);
//            System.out.println("Success");
//        } catch (SQLException | CustomException e) {
//            throw new RuntimeException(e);
//        }
        RequestDispatcher requestDispatcher = null;
        try {
            boolean isRegistered = userDAO.registerUser(user);
            if (isRegistered) {
                req.setAttribute("Registration Status","failed");
                requestDispatcher = req.getRequestDispatcher("login.jsp");

            }else {
                req.setAttribute("Registration Status", "success");
                requestDispatcher = req.getRequestDispatcher("index.jsp");
            }
            requestDispatcher.forward(req,resp);
        } catch (SQLException e) {
            System.out.println("Error in Registration: "+e.getMessage());
        }

    }


}
