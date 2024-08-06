package cn.event.model;

public class Vendor {
	private int id;
	private String title;
	private String description;
	private double price;
	private String category;
	private String phoneNo;
	private String image;
	
	public Vendor(int id, String title, String description, double price,String phoneNo, String image) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.price= price;
		this.phoneNo = phoneNo;
		this.image = image;
	}
	public Vendor() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
