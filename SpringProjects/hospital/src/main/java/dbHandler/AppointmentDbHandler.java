package dbHandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


import entity.Appointment;

public class AppointmentDbHandler {
	public List<Appointment> readAllAppointmentsFromFile() {
		String path = System.getProperty("user.dir");
		String filePath=path+"/db/appointment.csv";
		String line="";
		try 
		{
			List<Appointment> Appointmentlist=new ArrayList<Appointment>();
			BufferedReader br=new BufferedReader(new FileReader(filePath));
			while((line=br.readLine())!=null) 
			{
				String[] values=line.split(",");
				Appointment Appointmentobj=new Appointment(Integer.parseInt(values[0]), values[1], values[2], Integer.parseInt(values[3]), Integer.parseInt(values[4]), values[5], values[6]);
				Appointmentlist.add(Appointmentobj);
				//Operations To do
			
			}
			br.close();
			return Appointmentlist;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}//ReadFile
	
	
	public boolean writeAppointmentToFile(Appointment appointment)
	
	{
		try 
		{
			String path = System.getProperty("user.dir");
			String filePath=path+"/db/appointment.csv";
			FileWriter fw=new FileWriter(filePath,true);
			BufferedWriter bWriter= new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bWriter);
			pw.println(appointment.getId()+","+appointment.getDate()+","+appointment.getTime()+","+appointment.getDoctorId()+","+appointment.getUsernameId()+","+appointment.getStatus()+","+appointment.getComment());
			pw.flush();
			pw.close();
			System.out.println("Writing to appointment.csv File Done Successfully!!!");
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
		
	}//writeFile
	
	
	public boolean reWriteAppointmentFile(List<Appointment> appointmentList) 
	{
		boolean resp=false;
		try 
		{
			String path = System.getProperty("user.dir");
			String filePath=path+"/db/appointment.csv";
			FileWriter fw=new FileWriter(filePath);
			BufferedWriter bWriter= new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bWriter);
			for(int i=0;i<appointmentList.size();i++) 
			{
				pw.println(appointmentList.get(i).getId()+","+appointmentList.get(i).getDate()+","+appointmentList.get(i).getTime()+","+appointmentList.get(i).getDoctorId()+","+appointmentList.get(i).getUsernameId()+","+appointmentList.get(i).getStatus()+","+appointmentList.get(i).getComment());
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
//		 List<Appointment> list= readAllAppointmentsFromFile();
//		list.remove(0);
//		reWriteAppintmentFile(list);
//	}

}
