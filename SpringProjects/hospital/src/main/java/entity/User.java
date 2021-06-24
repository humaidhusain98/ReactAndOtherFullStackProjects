package entity;

public class User {
	int id;
	String username;
	String password;
	String mobileNumber;
	public User() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username +  ", mobileNumber=" + mobileNumber
				+ "]";
	}
	public User(int id, String username, String mobileNumber) {
		super();
		this.id = id;
		this.username = username;
		this.mobileNumber = mobileNumber;
	}
	
	public String encryptPassword(String password) 
	{
		String newpassword="";
		for(int i=0;i<password.length();i++) 
		{
			int ch=password.charAt(i);
			newpassword=newpassword+((char)(ch+3));
		}
		return newpassword;
	}
	
	
}
