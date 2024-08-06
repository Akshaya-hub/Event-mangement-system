package cn.event.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import cn.event.model.Vendor;
import cn.event.model.cart;

public class vendorDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	public vendorDao(Connection con) {
		this.con = con;
	}
	
	public List<Vendor> getAllVendors(){
		List <Vendor> vendors = new ArrayList<Vendor>();
	
		try {
		query = "select * from vendors";
		pst = this.con.prepareStatement(query);
		rs = pst.executeQuery();
		while(rs.next()) {
			Vendor row = new Vendor();
			row.setId(rs.getInt("id"));
			row.setTitle(rs.getString("title"));
			row.setDescription(rs.getString("description"));
			row.setPrice(rs.getDouble("price"));
			row.setImage(rs.getString("image"));
			row.setPhoneNo(rs.getString("phoneNo"));
			
			vendors.add(row);
			
		}
	}catch(Exception e) {
		e.printStackTrace();
	}
	
	return vendors;
	
	}
	
	 public Vendor getSingleVendor(int id) {
		 Vendor row = null;
	        try {
	            query = "select * from vendors where id=? ";

	            pst = this.con.prepareStatement(query);
	            pst.setInt(1, id);
	            ResultSet rs = pst.executeQuery();

	            while (rs.next()) {
	            	row = new Vendor();
	                row.setId(rs.getInt("id"));
	                row.setTitle(rs.getString("title"));
	                row.setCategory(rs.getString("category"));
	                row.setPrice(rs.getDouble("price"));
	                row.setImage(rs.getString("image"));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println(e.getMessage());
	        }

	        return row;
	    }
	
	public List<cart> getCartVendors(ArrayList<cart> cartList){
		List<cart> vendors = new ArrayList<cart>();
		
		try {
			if(cartList.size()>0) {
				for(cart item : cartList) {
					query = "select * from vendors where id=?";
					pst = this.con.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs = pst.executeQuery();
					while(rs.next()) {
						cart row = new cart();
						row.setId(rs.getInt("id"));
						row.setTitle(rs.getString("title"));
						row.setCategory(rs.getString("category"));
						row.setPrice(rs.getDouble("price"));
//						row.setO_date(rs.getString("O_date"));
						vendors.add(row);
					}
				}
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return vendors;
	}
	
	public double getTotalCartPrice(ArrayList<cart> cartList) {
		double sum=0.0;
		
		try {
			if(cartList.size()>0) {
				for(cart item:cartList) {
					query="select price from vendors where id=?";
					pst =this.con.prepareStatement(query);
					pst.setInt(1,item.getId());
					rs = pst.executeQuery();
					
					
					while(rs.next()) {
						sum+= rs.getDouble("price");
					}
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		
		return sum;
	}

	public void addVendor(Vendor vendor) {
	    try {
	        String query = "INSERT INTO vendors (id,title, category, description, price, image, phoneNo) VALUES (?,?, ?, ?, ?, ?, ?)";
	        try (PreparedStatement pstmt = con.prepareStatement(query)) {
	        	pstmt.setInt(1, vendor.getId());
	        	pstmt.setString(2, vendor.getTitle());
	            pstmt.setString(3, vendor.getCategory());
	            pstmt.setString(4, vendor.getDescription());
	            pstmt.setDouble(5, vendor.getPrice());
	            pstmt.setString(6, vendor.getImage());
	            pstmt.setString(7, vendor.getPhoneNo());
	            
	            pstmt.executeUpdate();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        
	    }
	}

	public void updateVendor(Vendor vendor) {
	    try {
	        String query = "UPDATE vendors SET title=?, category=?, description=?, price=?, image=?, phoneNo=? WHERE id=?";
	        try (PreparedStatement pstmt = con.prepareStatement(query)) {
	            pstmt.setString(1, vendor.getTitle());
	            pstmt.setString(2, vendor.getCategory());
	            pstmt.setString(3, vendor.getDescription());
	            pstmt.setDouble(4, vendor.getPrice());
	            pstmt.setString(5, vendor.getImage());
	            pstmt.setString(6, vendor.getPhoneNo());
	            pstmt.setInt(7, vendor.getId());
	            
	            pstmt.executeUpdate();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public void deleteVendor(int id) {
	    try {
	        String query = "DELETE FROM vendors WHERE id=?";
	        try (PreparedStatement pstmt = con.prepareStatement(query)) {
	            pstmt.setInt(1, id);
	            pstmt.executeUpdate();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


		
	}
	 
