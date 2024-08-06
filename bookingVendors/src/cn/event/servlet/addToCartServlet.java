package cn.event.servlet;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import cn.event.model.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class addToCartServlet
 */
@WebServlet("/addToCartServlet")
public class addToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset-UTF-8");
		
		try(PrintWriter out = response.getWriter()){
			ArrayList<cart> cartList = new ArrayList<>();
		
		int id = Integer.parseInt(request.getParameter("id"));
		cart cm = new cart();
		cm.setId(id);
//		cm.setQuantity(1);
		
		
		HttpSession session = request.getSession();
		ArrayList<cart> cart_list = (ArrayList<cart>) session.getAttribute("cart-list");
		
		if(cart_list == null) {
			cartList.add(cm);
			session.setAttribute("cart-list", cartList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Index.jsp");
			dispatcher.forward(request, response);
		}else {
			cartList = cart_list;
			boolean exist = false;
			
			for(cart c: cart_list) {
				if(c.getId() == id) {
					exist = true;
					out.println("<h3 style='colour:crimson; text-align:center'>Item already exist in cart.<a href='cart.jsp'>Go to cart Page</a></h3>");
				}
				
				}
			if (!exist) {
				cartList.add(cm);
				response.sendRedirect("Index.jsp");
			}
		}
		
		for(cart c: cart_list) {
			out.println(c.getId());
		}
		}
	}
	

}
