<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="cn.event.ctb.connection.ConnectionDB" %>
<%@page import="cn.event.model.*"%>
<%@page import="java.util.*"%> 
<%@page import="cn.event.dao.*"%>

<%
	
	
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
    <meta charset="UTF-8">
    <title>Vendor Hub</title>
    <link rel="stylesheet" href="admin.css">
</head>
<body>
	<%@include file ="includes/navBar.jsp" %> 
    <h1>Add Vendor</h1>
    <hr>
    <form action="AddVendorServlet" method="post">
    
    	<label for="id">ID:</label>
		<input type="number" id="id" name="id"><br>
    	
        <label for="title">Title:</label>
        <input type="text" id="title" name="title"><br>
        
        <label for="categories">Category:</label>
        <input type="text" id="categories" name="categories"><br>
        
        <label for="description">Description:</label>
        <textarea id="description" name="description"></textarea><br>
        
        <label for="price">Price:</label>
        <input type="text" id="price" name="price"><br>
        
        <label for="image">Image:</label>
        <input type="text" id="image" name="image"><br>
        
        <label for="phoneNo">Phone Number:</label>
        <input type="text" id="phoneNo" name="phoneNo"><br>
        
        <input type="submit" value="Add Vendor">
    </form>
    <hr>
    <h1>Update Vendor</h1>
    <form action="EditVendorServlet" method="post">
    
    	<label for="id">ID:</label>
		<input type="number" id="id" name="id"><br>
    	
        <label for="title">Title:</label>
        <input type="text" id="title" name="title"><br>
        
        <label for="categories">Category:</label>
        <input type="text" id="categories" name="categories"><br>
        
        <label for="description">Description:</label>
        <textarea id="description" name="description"></textarea><br>
        
        <label for="price">Price:</label>
        <input type="text" id="price" name="price"><br>
        
        <label for="image">Image:</label>
        <input type="text" id="image" name="image"><br>
        
        <label for="phoneNo">Phone Number:</label>
        <input type="text" id="phoneNo" name="phoneNo"><br>
        
        <input type="submit" value="update">
    </form>
    
    <hr>
     <h1>Delete Vendor</h1>
    <form action="DeleteVendorServlet" method="get">
    <label for="id">Vendor ID:</label>
    <input type="text" id="id" name="id" required><br><br>
    <input type="submit" value="Delete">
</form>
</body>
</html>
