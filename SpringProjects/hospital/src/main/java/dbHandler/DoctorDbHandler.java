package dbHandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import entity.Doctor;

public class DoctorDbHandler {

	public List<Doctor> readDoctorFromFile() {
		String path = System.getProperty("user.dir");
		String filePath=path+"/db/doctor.csv";
		String line="";
		try 
		{
			List<Doctor> doctorlist=new ArrayList<Doctor>();
			BufferedReader br=new BufferedReader(new FileReader(filePath));
			while((line=br.readLine())!=null) 
			{
				String[] values=line.split(",");
				Doctor docobj=new Doctor(Integer.parseInt(values[0]), values[1], values[2], Integer.parseInt(values[3]), values[4]);
				docobj.setUsername(values[5]);
				docobj.setPassword(values[6]);
				doctorlist.add(docobj);
				//Operations To do
			
			}
			br.close();
			return doctorlist;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}//ReadFile
	
	
	public boolean writeDoctorToFile(Doctor doctor)
	{
		try 
		{
			String path = System.getProperty("user.dir");
			String filePath=path+"/db/doctor.csv";
			FileWriter fw=new FileWriter(filePath,true);
			BufferedWriter bWriter= new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bWriter);
			pw.println(doctor.getId()+","+doctor.getName()+","+doctor.getDepartment()+","+doctor.getAge()+","+doctor.getMobileNumber()+","+doctor.getUsername()+","+doctor.getPassword());
			pw.flush();
			pw.close();
			System.out.println("Writing of File Done Successfully!!!");
			return true;
		}
		catch(Exception e)
		{
		
			e.printStackTrace();
			return false;
		}
		
		
	}//writeFile
	

	public boolean reWriteDoctorFile(List<Doctor> doctorList) 
	{
		boolean resp=false;
		try 
		{
			String path = System.getProperty("user.dir");
			String filePath=path+"/db/doctor.csv";
			FileWriter fw=new FileWriter(filePath);
			BufferedWriter bWriter= new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bWriter);
			for(int i=0;i<doctorList.size();i++) 
			{
				pw.println(doctorList.get(i).getId()+","+doctorList.get(i).getName()+","+doctorList.get(i).getDepartment()+","+doctorList.get(i).getAge()+","+doctorList.get(i).getMobileNumber()+","+doctorList.get(i).getUsername()+","+doctorList.get(i).getPassword());
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
////		reWriteDoctorFile(new ArrayList<Doctor>());//-----Clearing Db
//		
//		
//	}
	
	
}
