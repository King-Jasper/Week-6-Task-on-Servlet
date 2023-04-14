package NwaforInnocetIfenna.Controller;



import NwaforInnocetIfenna.dao.AdminDAO;
import NwaforInnocetIfenna.model.Product;
import NwaforInnocetIfenna.utility.CustomException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AdminServlet", value = "/AddProduct")
public class AddProductServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String productName = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        String category = req.getParameter("category");
        String image = req.getParameter("image");
        Product product = new Product(productName,category,quantity,price,image);
        AdminDAO adminDAO = new AdminDAO();

        HttpSession session = req.getSession();
        try {
            if (!adminDAO.addProduct(product)) {
                String errorMessage = "Product Already Exist";
                session.setAttribute("Registration Status", errorMessage);
           resp.sendRedirect("Admin.jsp");
            }else {
//                System.out.println("Successful");
                session.setAttribute("Registration Status", "successfully registered! Login to continue.");
              resp.sendRedirect("Admin.jsp");
            }
        } catch (SQLException | CustomException e) {
          throw new RuntimeException(e);
        }
//        resp.sendRedirect("hellojsp.jsp");
    }


   // @Override
   // protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

