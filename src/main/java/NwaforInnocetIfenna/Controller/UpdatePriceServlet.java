package NwaforInnocetIfenna.Controller;

import NwaforInnocetIfenna.dao.AdminDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "UpdatePriceServlet", value="/price_update")
public class UpdatePriceServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productID = Integer.parseInt(request.getParameter("product_id"));
        double price = Double.parseDouble(request.getParameter("price"));
        AdminDAO dao = new AdminDAO();

        boolean check;
        try {
            check = dao.setProductPrice(price, productID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String script;
        String redirectUrl = "Admin.jsp";
        if(check){
            script = "<script>alert('Set price successful');window.location='" + redirectUrl + "'</script>";
        }else{
            script = "<script>alert('Set price Unsuccessful');window.location='" + redirectUrl + "'</script>";
        }
        response.setContentType("text/html");
        response.getWriter().println(script);
    }
}
