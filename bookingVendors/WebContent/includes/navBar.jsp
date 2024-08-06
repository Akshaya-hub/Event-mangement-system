<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="Index.css">
<link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet'>
</head>
<body>
	<img src="Vendor-images/Cover page.png" width="100%">
<div class="heading">
		<h1 id="topic"> Event Management System</h1>
		<h3>&mdash;Book vendors &mdash;</h3>
	</div>

	<nav>
		<ul>
			<li><h2>MENU</h2></li>
			<li style="float:right"><a class="active" href="cart.jsp">View cart<span style="background-color: red; color: white;border-radius:50%; box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19);">
			${cart_list.size()}</span></a></li>
			<li style="float:right"><a class="active" href="Index.jsp">Home</a></li>
			<li style="float:right"><a class="active" href="vendor_hub.jsp">Vendor_hub</a></li>
		</ul>
	</nav>
	

</body>
</html>