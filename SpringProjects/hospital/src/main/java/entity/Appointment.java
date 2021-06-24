package entity;



public class Appointment 
{

private int id;
  private String date;
  private String time;
  private int doctorId;
  private int usernameId;
  private String status;
  private String comment;

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}
public int getDoctorId() {
	return doctorId;
}
public void setDoctorId(int doctorId) {
	this.doctorId = doctorId;
}
public int getUsernameId() {
	return usernameId;
}
public void setUsernameId(int usernameId) {
	this.usernameId = usernameId;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getComment() {
	return comment;
}
public void setComment(String comment) {
	this.comment = comment;
}
public Appointment() {
	super();
}
public Appointment(int id, String date, String time, int doctorId, int usernameId, String status, String comment) {
	super();
	this.id = id;
	this.date = date;
	this.time = time;
	this.doctorId = doctorId;
	this.usernameId = usernameId;
	this.status = status;
	this.comment = comment;
}
  
@Override
	public String toString() {
		return "Appointment [id=" + id + ", date=" + date + ", time=" + time + ", doctorId=" + doctorId
				+ ", usernameId=" + usernameId + ", status=" + status + ", comment=" + comment + "]";
	}
  
	
}
