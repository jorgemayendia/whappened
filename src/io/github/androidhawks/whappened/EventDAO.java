package io.github.androidhawks.whappened;


public class EventDAO{
	private long id;
	private String user_id;
	private String location;
	private String date_time;
	private String comment;
	private String isRecommended;
	

	public EventDAO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public EventDAO(long id, String user_id, String location, String date_time,
			String comment, String isRecommended) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.location = location;
		this.date_time = date_time;
		this.comment = comment;
		this.isRecommended = isRecommended;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getDate_time() {
		return date_time;
	}


	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public String getIsRecommended() {
		return isRecommended;
	}


	public void setIsRecommended(String isRecommended) {
		this.isRecommended = isRecommended;
	}
	
		
}