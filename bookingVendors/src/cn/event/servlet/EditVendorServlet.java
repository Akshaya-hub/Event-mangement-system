package cn.event.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.event.ctb.connection.ConnectionDB;
import cn.event.dao.vendorDao;
import cn.event.model.Vendor;

@WebServlet("/EditVendorServlet")
public class EditVendorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
        int id = Integer.parseInt(request.getParameter("id")); // Assuming you have an input field for id
        String title = request.getParameter("title");
        String categories = request.getParameter("categories");
        String description = request.getParameter("description");
        String priceStr = request.getParameter("price");
        String image = request.getParameter("image");
        String phoneNo = request.getParameter("phoneNo");

        // Convert price to double
        double price = Double.parseDouble(priceStr);

        // Create a new vendor object
        Vendor vendor = new Vendor();
        vendor.setId(id);
        vendor.setTitle(title);
        vendor.setCategory(categories); // Assuming this is the correct setter name for categories
        vendor.setDescription(description);
        vendor.setPrice(price);
        vendor.setImage(image);
        vendor.setPhoneNo(phoneNo);

        try {
            // Create vendorDao instance
            vendorDao vendorDao = new vendorDao(ConnectionDB.getCon());

            // Call the updateVendor method to update vendor information in the database
            vendorDao.updateVendor(vendor);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // If an exception occurs, handle it appropriately
            // You can redirect to an error page or display an error message to the user
            response.sendRedirect("error.jsp");
            return; // Stop further execution
        }

        // Redirect back to vendor_hub.jsp
        response.sendRedirect("vendor_hub.jsp");
    }
}
