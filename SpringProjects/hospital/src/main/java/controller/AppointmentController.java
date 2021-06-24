package controller;

import java.util.ArrayList;
import java.util.List;

import dbHandler.AppointmentDbHandler;
import entity.Appointment;

public class AppointmentController {
	

	private  List<Appointment> appointment;
	private  int lastAppointmentId=-1;
	AppointmentDbHandler appointmentDbHandler;
	
	public AppointmentController() {
		appointmentDbHandler=new AppointmentDbHandler();
		this.loadAppointment();
	}
	
	public void loadAppointment() 
	{
		appointment=appointmentDbHandler.readAllAppointmentsFromFile();
		for(int i=0;i<appointment.size();i++) 
		{
		  if(appointment.get(i).getId()>lastAppointmentId) 
		  {
			lastAppointmentId=appointment.get(i).getId();  
		  }
		}
	}
	
	
	public boolean addAppointment(Appointment d) 
	{
		lastAppointmentId++;
		d.setId(lastAppointmentId);
		System.out.println(d.toString()+"added");
		appointment.add(d);	
		boolean resp= appointmentDbHandler.writeAppointmentToFile(d);
		return resp;
	}
	
	public void removeAppointment(int id) 
	{
		for(int i=0;i<appointment.size();i++)
		{
			Appointment d=appointment.get(i);
			if(d.getId()==id)
				{appointment.remove(d);
				System.out.println(d.toString()+"removed");
				}
		}
		appointmentDbHandler.reWriteAppointmentFile(appointment);
		
	}
	
	public List<Appointment> getAllAppointments()
	{
			return appointment;
		
	}
	
	public Appointment getAppointment(int id) {
		Appointment appoint=appointment.get(id);
		return appoint;
	}
	
	public int getSize() {
		return appointment.size();
	} 


	public List<Appointment> getAllUserAppointments(int usernameId)
	{
		List<Appointment> userAppointmentList=new ArrayList<Appointment>();
		for(int i=0;i<appointment.size();i++) 
		{
			if(appointment.get(i).getUsernameId()==usernameId) 
			{
				userAppointmentList.add(appointment.get(i));
			}
		}
		return userAppointmentList;
	}
	
	

}
