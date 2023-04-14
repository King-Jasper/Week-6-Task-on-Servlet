package NwaforInnocetIfenna.Controller;



import NwaforInnocetIfenna.dao.AdminDAO;
import NwaforInnocetIfenna.model.Product;
import NwaforInnocetIfenna.utility.CustomException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name= "RemoveProductServlet", value ="/RemoveProduct")
public class RemoveProductServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //THE productID IS THE FRONT END FORM NAME.
        String product_id = req.getParameter("productID");
        AdminDAO adminDOA = new AdminDAO();

        HttpSession session = req.getSession();
        try {
            if (!adminDOA.removeProduct(Integer.parseInt(product_id))) {
                String errorMessage = "Product Already removed";
                session.setAttribute("Removal Status", errorMessage);
                resp.sendRedirect("Admin.jsp");
            }else {
//                System.out.println("Successful");
                session.setAttribute("Removal Status", "successfully removed, do you want to remove another?.");
                resp.sendRedirect("Admin.jsp");
            }
        } catch (SQLException | CustomException e) {
            throw new RuntimeException(e);
        }
    }
}
