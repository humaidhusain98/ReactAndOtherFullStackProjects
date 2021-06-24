package controller;


import java.util.ArrayList;
import java.util.List;

import dbHandler.DoctorDbHandler;
import entity.Doctor;
import entity.User;

public class DoctorController 
{
	
	
//	
	
	 
	private List<Doctor> docs;
	private int  lastDocId=-1;
	DoctorDbHandler doctorDbHandler;
	
	public DoctorController() {
		doctorDbHandler=new DoctorDbHandler();
		this.loadDocs();
	}
	
	
	public void loadDocs() 
	{
		docs=doctorDbHandler.readDoctorFromFile();
		for(int i=0;i<docs.size();i++) 
		{
		  if(docs.get(i).getId()>lastDocId) 
		  {
			lastDocId=docs.get(i).getId();  
		  }
		}
		
	}
	
	
	public boolean registerDoctor(Doctor doctor) 
	{
		lastDocId++;
		doctor.setId(lastDocId);
		String encryptPassword=doctor.encryptPassword(doctor.getPassword());
		doctor.setPassword(encryptPassword);
		docs.add(doctor);
		boolean resp= doctorDbHandler.writeDoctorToFile(doctor);
		return resp;
	}
	
	public void removeDoctor(int id) 
	{
		for(int i=0;i<docs.size();i++)
		{
			Doctor d=docs.get(i);
			if(d.getId()==id)
				{docs.remove(d);
				System.out.println(d.toString()+"removed");
				}
		}
		doctorDbHandler.reWriteDoctorFile(docs);
		
	}
	
	public List<Doctor> getAllDoctors()
	{
		
		return docs;
		
	}
	
	public Doctor getDoc(int id) {
		Doctor doctor=docs.get(id);
		return doctor;
	}
	
	public int getSize() {
		return docs.size();
	} 
	
	
	public Doctor searchDocName(String name)
	{
		for(int i=0;i<docs.size();i++)
		{
			if(docs.get(i).getName().equals(name)) 
			{
				System.out.println("Doctor found in Repo");
				return docs.get(i);
				
			}
			
			
		}
		return null;
		
	}
	
	public boolean isUsernameValid(String username) 
	{
		boolean status=true;
		for(int i=0;i<docs.size();i++) 
		{
			if(docs.get(i).getUsername().equals(username)) 
			{
				return false;
			}
		}
		return status;
	}
	
	public Doctor getDoctorFromUserName(String username) 
	{
		Doctor doc=null;
		for(int i=0;i<docs.size();i++) 
		{
			if(docs.get(i).getUsername().equals(username)) 
			{
				doc=docs.get(i);
				return doc;
			}
		}
		return doc;
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
	
	public Doctor validatePasswordandGetDoctor(String username,String password) 
	{ 
		Doctor objdoc=getDoctorFromUserName(username);
		String encryptedPass=encryptPassword(password);
		if(objdoc!=null)
		{
			if(objdoc.getPassword().equals(encryptedPass)) 
			{
				return objdoc;
			}
		}
		
		return null;
	}
	
	
	public List<String> getAllDoctorNames(){
		List<String> doctornameslist = new ArrayList<String>();
		for(int i=0;i<docs.size();i++) {
			doctornameslist.add(docs.get(i).getName());
		}
		return doctornameslist;
	}


	public int getDoctorIdByName(String name){
		int objId=-1;
		for(int i=0;i<docs.size();i++)
		{
			if(docs.get(i).getName().equals(name)){
				objId=docs.get(i).getId();
			}
		}
		return objId;
	}
	
	
	
	
//	public static void main(String args[]) 
//	{
////		loadDocs();
////		Doctor doctor=new Doctor(5, "Rajeev Chatterjee", "ordontist", 34, "+91842536252");
////		registerDoctor(doctor);
//		
//	}
	

	
	

}
