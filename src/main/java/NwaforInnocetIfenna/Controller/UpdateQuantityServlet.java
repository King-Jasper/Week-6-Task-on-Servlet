package NwaforInnocetIfenna.Controller;

import NwaforInnocetIfenna.dao.AdminDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "UpdateQuantityServlet", value="/quantity_update")
public class UpdateQuantityServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productID = Integer.parseInt(request.getParameter("product_id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        AdminDAO dao = new AdminDAO();

        boolean check;
        try {
            check = dao.setProductQuantity(quantity, productID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String script;
        String redirectUrl = "Admin.jsp";
        if(check){
            script = "<script>alert('Set quantity successful');window.location='" + redirectUrl + "'</script>";
        }else{
            script = "<script>alert('Set quantity Unsuccessful');window.location='" + redirectUrl + "'</script>";
        }
        response.setContentType("text/html");
        response.getWriter().println(script);
    }
}
