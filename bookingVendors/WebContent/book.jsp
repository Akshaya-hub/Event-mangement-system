<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="cn.event.dao.bookDao"%>
<%@page import="cn.event.ctb.connection.ConnectionDB" %>
<%@page import="cn.event.dao.vendorDao"%>
<%@page import="cn.event.model.*"%>
<%@page import="java.util.*"%>

	<%
	DecimalFormat dcf = new DecimalFormat("#.##");
	request.setAttribute("dcf", dcf);
	User auth = (User) request.getSession().getAttribute("auth");
	List<book> books = null;
	if (auth != null) {
	    request.setAttribute("person", auth);
	    bookDao bookDao  = new bookDao(ConnectionDB.getCon());
		books = bookDao.userBooks(auth.getId());
	}else{
		response.sendRedirect("login.jsp");
	}
	ArrayList<cart> cart_list = (ArrayList<cart>) session.getAttribute("cart-list");
	if (cart_list != null) {
		request.setAttribute("cart_list", cart_list);
	}
	
	%>
<!DOCTYPE html>
<html>
<head>

<title>E-Commerce Cart</title>
<link rel="stylesheet" href="cart.css">
</head>
<body>
	 <%@include file ="includes/navBar.jsp" %> 
	 <hr>
	<div class="container">
		<div class="card-header my-3">All Orders</div>
		<table id ="cus">
			<thead>
				<tr>
					<th scope="col">Date</th>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Price</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
			
			 <% if (cart_list != null && books != null) {
                for (cart c : cart_list) {
                    for (book o : books) { %>
                    <tr>
                        <td><%= c.getO_date() %></td>
                        <td><%= o.getTitle() %></td>
                        <td><%= o.getCategory() %></td>
                        <td><%= dcf.format(o.getPrice()) %></td>
                        <td><a class="btn btn-sm btn-danger" href="cancel-order?id=<%= o.getEvent_Id() %>">Cancel Order</a></td>
                    </tr>
                <% }
                }
            } %>
			
			</tbody>
		</table>
	</div>

</body>
</html>