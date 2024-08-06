package cn.event.ctb.connection;
import java.sql.*;

public class ConnectionDB {
	private static Connection connection = null;
	
	public static Connection getCon() throws ClassNotFoundException, SQLException{
		if(connection == null) {
			Class.forName("com.mysql.jdbc.Driver");
			connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/eventmanagement","root","#Akshaya@19");
			System.out.print("connected");
		}
		return connection;
	}
	
}
