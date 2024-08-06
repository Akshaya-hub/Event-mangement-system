package cn.event.model;

public class book extends Vendor{
	private int event_Id;
	private int uid;

	
	public book() {
	}
	
	public book(int event_Id, int uid, int qunatity) {
		super();
		this.event_Id = event_Id;
		this.uid = uid;
		
	}

	public book(int uid, int qunatity) {
		super();
		this.uid = uid;
		
	}

	public int getEvent_Id() {
		return event_Id;
	}
	public void setEvent_Id(int event_Id) {
		this.event_Id = event_Id;
	}
	
	public int getUid() {
		return uid;
	}
}
