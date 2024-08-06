package cn.event.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.event.model.cart;

@WebServlet("/Remove-From-cart")
public class RemoveFromcartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;cgarset=UTF-8");
		try(PrintWriter out =response.getWriter()){
			String id = request.getParameter("id");
			if(id!= null) {
				ArrayList<cart> cart_list = (ArrayList<cart>) request.getSession().getAttribute("cart-list");
				if(cart_list != null) {
					for(cart c: cart_list) {
						if(c.getId() == Integer.parseInt(id)) {
							cart_list.remove(cart_list.indexOf(c));
							break;
						}
					}
					response.sendRedirect("cart.jsp");
				}
			}else {
				response.sendRedirect("cart.jsp");
		    }
		
	      }
	  }
}
