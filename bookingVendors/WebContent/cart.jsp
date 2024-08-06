<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>   
<%@page import="cn.event.model.*"%>
<%@page import="cn.event.dao.vendorDao"%>
<%@page import="java.util.*"%> 
<%@page import="cn.event.ctb.connection.ConnectionDB" %>

<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<%
ArrayList<cart> cart_list = (ArrayList<cart>) session.getAttribute("cart-list");
List<cart> cartVendor = null;
if(cart_list != null){
vendorDao vDao = new vendorDao(ConnectionDB.getCon());
cartVendor = vDao.getCartVendors(cart_list);
double total = vDao.getTotalCartPrice(cart_list);
 request.setAttribute("cart_list",cart_list);
request.setAttribute("total",total);
}

%>
<%
	LocalDate currentDate = LocalDate.now();
	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	String formattedDate = currentDate.format(dateFormatter);
	
	
    
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cart</title>
<link rel="stylesheet" href="cart.css">
	<link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet'>
<style>
.book-button {
           
           background-color:black;
		    border:none;
		    color: white;
		    font-size: 16px;
		    font-weight:600;
		    border-radius: 5px;
		    width: 180px;
		    padding: 15px 20px;
		    margin-top: 20px;
}
.crudbutton{
	font-family:'Montserrat';
    display: inline-block;
    padding: 5px 5px;
    font-size: 11px;
    cursor: pointer;
    text-align: center;
    text-decoration: none;
    outline: none;
    color: #fff;
    background-color: #aa0404;
    border: none;
    border-radius: 5px;
    box-shadow: 0 9px #999;
  }
  
.editbutton{
	font-family:'Montserrat';
    display: inline-block;
    padding: 5px 5px;
    font-size: 11px;
    cursor: pointer;
    text-align: center;
    text-decoration: none;
    outline: none;
    color: #fff;
    background-color: #cbc1c1;
    border: none;
    border-radius: 5px;
    box-shadow: 0 2px #999;
  }
.editbutton:hover {background-color: #9ec159}

  
.editbutton:active {
    background-color: #798e3e;
    box-shadow: 0 5px #666;
    transform: translateY(4px);
  }
    
  
.crudbutton:hover {background-color: #e02e06}
  
.crudbutton:active {
    background-color: #8e453e;
    box-shadow: 0 5px #666;
    transform: translateY(4px);
  }


</style>
<script>
        function validateDate() {
            var inputDate = document.getElementById("editDate").value;
            var regex = /^\d{4}-\d{2}-\d{2}$/;
            if (!regex.test(inputDate)) {
                alert("Please enter the date in the format YYYY-MM-DD.");
                return false; // Prevent form submission
            }
            return true; // Allow form submission
        }
    </script>
</head>
<body>
<%@include file ="includes/navBar.jsp" %>
<hr>

<table id ="cus">
 
	<tr>
	<th scope="col">No</th>
	<th scope="col">Title</th>
	<th scope="col">Category</th>
	<th scope="col">Price</th>
	<th scope="col">Order Date</th>
	<th scope="col">cancel</th></tr>
	<tbody>
	
	<%
	if(cart_list != null){
		for(cart c: cartVendor){%>
			<tr>
				<td><%= c.getId() %></td>
                <td><%= c.getTitle() %></td>
                <td><%= c.getCategory() %></td>
                <td><%= c.getPrice() %></td>
                <td><%= formattedDate %>
               <form action="editDateServlet" method="post" onsubmit="return validateDate()">
    <label for="editDate">Edit Date:</label>
    <input type="text" id="editDate" name="editDate" placeholder="<%= formattedDate %>">
    <input type="submit" value="Submit" class="editbutton">
	</form>
	<h2>Edit Date</h2></td>
			<td> <a class="crudbutton" href="Remove-From-cart?id=<%= c.getId() %>">Remove</a>
			<a class="crudbutton" href="BookNowServlet?id=<%= c.getId() %>">Book Now</a>
			</td>
		<%}
	}
	
	%>
	
	</tbody>
</table>
<div><br>
	<h3>Total Price: RS ${total}</h3>
</div>
<br>
<a class="book-button" href="CheckOutServlet">CheckOut</a>

</body>
</html>