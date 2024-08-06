package cn.event.servlet;

import cn.event.ctb.connection.ConnectionDB;
import cn.event.dao.DateDao;
import cn.event.dao.bookDao;
import cn.event.dao.processDateDao;
import cn.event.model.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/editDateServlet")
public class editDateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DateDao dateDao;

    public void init() {
        dateDao = new DateDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String newDateStr = request.getParameter("editDate");

    	try {
    	    // Validate date format
    	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	    dateFormat.setLenient(false);
    	    java.util.Date parsedDate = dateFormat.parse(request.getParameter("editDate")); // Line 40
    	    Date newDate = new Date(parsedDate.getTime());
    	    // Update date in database
    	    dateDao.updateDate(newDate);
    	    
			/*
			 * String formattedDate = dateFormat.format(newDate);
			 * request.setAttribute("formattedDate", formattedDate);
			 */

    	    // Redirect back to index.jsp
    	    response.sendRedirect(request.getContextPath() + "/cart.jsp");
    	} catch (ParseException e) {
            // Invalid date format, set error message
            request.setAttribute("errorMessage", "Invalid date format. Please use YYYY-MM-DD.");
            request.getRequestDispatcher("/cart.jsp").forward(request, response);
        }
    }
}

