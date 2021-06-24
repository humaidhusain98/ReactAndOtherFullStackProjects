package controller;

import java.util.List;



import dbHandler.UserDbHandler;
import entity.User;

public class UserController {

	
	private List<User> userList;
	private int lastUserId=-1;
	UserDbHandler userDbHandler;
	
	public UserController() {
		userDbHandler=new UserDbHandler();
		this.loadUserFromDb();
	}
	
	
	public void loadUserFromDb() 
	{
		userList= userDbHandler.getAllUsersFromFile();
		for(int i=0;i<userList.size();i++) 
		{
		  if(userList.get(i).getId()>lastUserId) 
		  {
			lastUserId=userList.get(i).getId();  
		  }
		}
		
	}

	public List<User> getAllUsers()
	{
		return userList;
	}
	
	public boolean registerUser(User user) {
		boolean status=false;
		try 
		{
			lastUserId++;
			user.setId(lastUserId);
			String encryptPassword=user.encryptPassword(user.getPassword());
			user.setPassword(encryptPassword);
			userList.add(user);
			status = userDbHandler.addUserToDB(user);
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public boolean isUsernameValid(String username) 
	{
		boolean status=true;
		for(int i=0;i<userList.size();i++) 
		{
			if(userList.get(i).getUsername().equals(username)) 
			{
				return false;
			}
		}
		return status;
	}
	
	public User getUserFromUserName(String username) 
	{
		User user=null;
		for(int i=0;i<userList.size();i++) 
		{
			if(userList.get(i).getUsername().equals(username)) 
			{
				user=userList.get(i);
				return user;
			}
		}
		return user;
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
	
	public User validatePasswordandGetUser(String username,String password) 
	{ 
		User objUser=getUserFromUserName(username);
		System.out.println(objUser);
		String encryptedPass=encryptPassword(password);
		System.out.println(encryptedPass);
		if(objUser!=null)
		{
			System.out.println(objUser.getPassword().equals(encryptedPass));
			if(objUser.getPassword().equals(encryptedPass)) 
			{
				return objUser;
			}
		}
		
		return null;
	}
	
	public static void main(String args[]) 
	{
//		loadUserFromDb();
//		User user=new User(2, "humaid", "+91852635215");
//		user.setPassword("password");
//		userList.add(user);
//		UserDbHandler.reWriteUserFile(userList);
//		System.out.println(userList.get(0).getPassword());
//		
//		System.out.println(validatePasswordandGetUser("humaid", "password"));
	}
	
}
