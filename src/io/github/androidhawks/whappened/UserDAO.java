package io.github.androidhawks.whappened;


public class UserDAO{
	private int id;
	private String first_name;
	private String last_name;
	private String email;
	private String password;
	private String created_date;
	//UserProfile profile;
	
	public UserDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDAO(int id, String first_name, String last_name, String email,
			String password, String created_date) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.password = password;
		this.created_date = created_date;
		//this.profile = profile;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
/*
	public UserProfile getProfile() {
		return profile;
	}

	public void setProfile(UserProfile profile) {
		this.profile = profile;
	} 
	
	*/
	
	
}