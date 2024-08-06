package cn.event.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.event.ctb.connection.ConnectionDB;
import cn.event.dao.bookDao;
import cn.event.model.User;
import cn.event.model.book;
import cn.event.model.cart;

/**
 * Servlet implementation class CheckOutServlet
 */
@WebServlet("/CheckOutServlet")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter()){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
			ArrayList<cart> cart_list = (ArrayList<cart>) request.getSession().getAttribute("cart-list");
			User auth = (User) request.getSession().getAttribute("auth");
			if(cart_list != null && auth!=null) {
				for(cart c:cart_list) {
					book order = new book();
					order.setId(c.getId());
					/*
					 * order.setUid(auth.getId()); order.setDate(formatter.format(date));
					 */
					
					bookDao oDao = new bookDao(ConnectionDB.getCon());
					boolean result = oDao.insertBook(order);
					if(!result) break;
				}
				cart_list.clear();
				response.sendRedirect("book.jsp");
			}else {
				if(auth==null) {
					response.sendRedirect("login.jsp");
				}
				response.sendRedirect("cart.jsp");
			}
		} catch (ClassNotFoundException|SQLException e) {
			
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}


