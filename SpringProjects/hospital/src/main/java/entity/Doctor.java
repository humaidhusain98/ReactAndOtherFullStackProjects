package entity;

public class Doctor {
	
	
	private int id;
	private String name;
	private String department;
	private int age;
	private String mobileNumber;
	private String username;
	private String password;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumberString) {
		this.mobileNumber = mobileNumberString;
	}
	public Doctor(int id, String name, String department, int age, String mobileNumber) {
		super();
		this.id = id;
		this.name = name;
		this.department = department;
		this.age = age;
		this.mobileNumber = mobileNumber;
	}
	@Override
	public String toString() {
		return "Doctor [id=" + id + ", name=" + name + ", department=" + department + ", age=" + age + ", mobileNumber="
				+ mobileNumber + "]";
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
