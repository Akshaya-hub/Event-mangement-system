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

@WebServlet("/DeleteVendorServlet")
public class DeleteVendorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the vendor ID parameter from the request
        String idStr = request.getParameter("id");

        // Convert the ID parameter to an integer
        int id = Integer.parseInt(idStr);

        try {
            // Create vendorDao instance
            vendorDao vendorDao = new vendorDao(ConnectionDB.getCon());

            // Call the deleteVendor method to delete the vendor from the database
            vendorDao.deleteVendor(id);

            // Redirect back to vendor_hub.jsp or any other appropriate page
            response.sendRedirect("vendor_hub.jsp");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // If an exception occurs, handle it appropriately
            // You can redirect to an error page or display an error message to the user
            response.sendRedirect("error.jsp");
        }
    }
}

