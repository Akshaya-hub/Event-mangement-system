package cn.event.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import cn.event.model.*;

public class bookDao {
	
	private Connection con;

	private String query;
    private PreparedStatement pst;
    private ResultSet rs;
    
    

	public bookDao(Connection con) {
		super();
		this.con = con;
	}

	public boolean insertBook(book model) {
	    boolean result = false;
	    try {
	        query = "INSERT INTO cart (event_id, title, category) VALUES (?, ?, ?)";
	        pst = this.con.prepareStatement(query);
	        pst.setInt(1, model.getEvent_Id());
	        pst.setString(2, model.getTitle());
	        pst.setString(3, model.getCategory());
//	        pst.setString(4, cart.getO_date());
	        pst.executeUpdate();
	        result = true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return result;
	}

	
	public List<book> userBooks(int id) {
	    List<book> list = new ArrayList<>();
	    try {
	        query = "SELECT * FROM cart WHERE event_id = ?";
	        pst = this.con.prepareStatement(query);
	        pst.setInt(1, id);
	        rs = pst.executeQuery();
	        while (rs.next()) {
	            book book = new book();
//	            cart cart = new cart();
	            // Assuming cart has attributes like event_id, title, category, O_date
	            book.setEvent_Id(rs.getInt("event_id"));
	            book.setTitle(rs.getString("title"));
	            book.setCategory(rs.getString("category"));
	            // Assuming O_date is a date or timestamp
//	            cart.setO_date(rs.getString("O_date"));
	            // Add the book object to the list
	            list.add(book);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}


	public boolean cancelBook(int id) {
	    boolean result = false;
	    try {
	        query = "DELETE FROM cart WHERE event_id=?";
	        pst = this.con.prepareStatement(query);
	        pst.setInt(1, id);
	        pst.executeUpdate();
	        result = true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.print(e.getMessage());
	    }
	    return result;
	}
	
}


