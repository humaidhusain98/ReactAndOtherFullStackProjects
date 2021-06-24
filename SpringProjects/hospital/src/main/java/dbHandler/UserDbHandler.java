package dbHandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import entity.User;

public class UserDbHandler {

	public boolean addUserToDB(User user) 
	{
		boolean status=false;
		String path = System.getProperty("user.dir");
		String filePath=path+"/db/user.csv";
		try 
		{
			FileWriter fw=new FileWriter(filePath,true);
			BufferedWriter bWriter= new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bWriter);
			pw.println(user.getId()+","+user.getUsername()+","+user.getPassword()+","+user.getMobileNumber());
			pw.flush();
			pw.close();
			System.out.println("User Saved Successfully in DB!!!");
			status=true;
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		return status;
	}
	
	
	public List<User> getAllUsersFromFile()
	{
		List<User> userlist=new ArrayList<User>();
		String path = System.getProperty("user.dir");
		String filePath=path+"/db/user.csv";
		String line="";
		try 
		{
			BufferedReader bReader=new BufferedReader(new FileReader(filePath));
			while((line=bReader.readLine())!=null) 
			{
				String[] value=line.split(",");
				User userobj = new User();
				userobj.setId(Integer.parseInt(value[0]));
				userobj.setUsername(value[1]);
				userobj.setPassword(value[2]);
				userobj.setMobileNumber(value[3]);
				userlist.add(userobj);
			}
			bReader.close();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		return userlist;
		
	}
	
	
	public boolean reWriteUserFile(List<User> userlist) 
	{
		boolean resp=false;
		try 
		{
			String path = System.getProperty("user.dir");
			String filePath=path+"/db/user.csv";
			FileWriter fw=new FileWriter(filePath);
			BufferedWriter bWriter= new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bWriter);
			for(int i=0;i<userlist.size();i++) 
			{
				pw.println(userlist.get(i).getId()+","+userlist.get(i).getUsername()+","+userlist.get(i).getPassword()+","+userlist.get(i).getMobileNumber());
			}
			pw.flush();
			pw.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
		return resp;
	} 

	
	
	
//	public static void main(String args[])
//	{
//
////		User userobj = new User();
////		userobj.setId(1);
////		userobj.setUsername("humaidhusain98");
////		userobj.setPassword("password");
////		userobj.setMobileNumber("+918523698574");
////		addUserToDB(userobj);
//	}
	
}
