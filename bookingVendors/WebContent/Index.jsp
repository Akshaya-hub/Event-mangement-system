<%@page import="cn.event.dao.vendorDao"%>
<%@page import="cn.event.model.*"%>
<%@page import="java.util.*"%> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
 <%@page import="cn.event.ctb.connection.ConnectionDB" %>
 <%  
    
 vendorDao vd = new vendorDao(ConnectionDB.getCon());
 List<Vendor> vendors = vd.getAllVendors();
 
 ArrayList<cart> cart_list = (ArrayList<cart>) session.getAttribute("cart-list");
 
 if(cart_list != null){
 request.setAttribute("cart_list",cart_list);
 }
 %>  
<!DOCTYPE html>
<html>
<meta charset="ISO-8859-1">

<head>
	<title>Book Vendors</title>
	 <link rel="stylesheet" href="Index.css"> 
	<link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet'>
</head>
<body>
	 <%@include file ="includes/navBar.jsp" %> 
<hr>
<div class='menu'>
<% 
    if (!vendors.isEmpty()) {
        for (Vendor v : vendors) {%>
            
                <div class='categories'>
                <img src= "Vendor-images/<%=v.getImage()%>" alt="cart Image">
                    <div class='details'>
                        <div class='details-sub'>
                            <h5><%=v.getTitle()%></h5><br>
                            <p><%=v.getDescription()%> </p><br>
                            <h5 class='price'><%= v.getPrice()%></h5><br><br>
                            <h5><%=v.getPhoneNo()%></h5><br>
                            <a href='addToCartServlet?id=<%= v.getId()%>'><button>add to cart</button></a>
                             
                        </div>
                    </div>
                </div>
       <% } 
    }
%>
</div>

</body>
</html>