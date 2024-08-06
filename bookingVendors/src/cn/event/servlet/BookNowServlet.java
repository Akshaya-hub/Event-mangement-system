package cn.event.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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

@WebServlet("/BookNowServlet")
public class BookNowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        	
        	
            User auth = (User) request.getSession().getAttribute("auth");

            if (auth != null) {
                String id = request.getParameter("id");
                
                // Get cart item details
                ArrayList<cart> cart_list = (ArrayList<cart>) request.getSession().getAttribute("cart-list");
                cart cartItem = null;
                if (cart_list != null) {
                    for (cart c : cart_list) {
                        if (Integer.toString(c.getId()).equals(id)) {
                            cartItem = c;
                            break;
                        }
                    }
                }

                // Prepare order details
                book orderModel = new book();
                orderModel.setId(auth.getId());
//                cart cart = new cart();
                orderModel.setTitle(cartItem.getTitle());
                orderModel.setCategory(cartItem.getCategory());
                orderModel.setPrice(cartItem.getPrice());
                
//                String newDate = request.getParameter("newDate");
//                cart.setO_date(newDate);

                // Insert order into database
                bookDao orderDao = new bookDao(ConnectionDB.getCon());
                boolean result = orderDao.insertBook(orderModel);
                if (result) {
                    // Remove item from cart
                    cart_list.remove(cartItem);
                    response.sendRedirect("book.jsp");
                } else {
                    out.println("Order failed");
                }
            } else {
                response.sendRedirect("login.jsp");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
