package com.people.chat.Model;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.people.chat.View.CompetencyView;



public class applyJob 
{
	Integer id;
	Integer userId;
	String dateApplied;
	String mobile;
	int currCTC;
	int expCTC;
	String currLocation;
	int noticeperiod;
	int jobid;
	
	public applyJob() 
	{
		
		
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	Competency competency1=null;
	int yearsofexp1;
	
	Competency competency2=null;
	int yearsofexp2;
	
	Competency competency3=null;
	int yearsofexp3;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDateApplied() {
		return dateApplied;
	}
	public void setDateApplied(String dateApplied) {
		this.dateApplied = dateApplied;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getCurrCTC() {
		return currCTC;
	}
	public void setCurrCTC(int currCTC) {
		this.currCTC = currCTC;
	}
	public int getExpCTC() {
		return expCTC;
	}
	public void setExpCTC(int expCTC) {
		this.expCTC = expCTC;
	}
	public String getCurrLocation() {
		return currLocation;
	}
	public void setCurrLocation(String currLocation) {
		this.currLocation = currLocation;
	}
	public int getNoticeperiod() {
		return noticeperiod;
	}
	public void setNoticeperiod(int noticeperiod) {
		this.noticeperiod = noticeperiod;
	}
	
	public Competency getCompetency1() {
		return competency1;
	}
	public void setCompetency1(Competency competency1) {
		
		this.competency1 = competency1;
	}
	public int getYearsofexp1() {
		return yearsofexp1;
	}
	public void setYearsofexp1(int yearsofexp1) {
		this.yearsofexp1 = yearsofexp1;
	}
	public Competency getCompetency2() {
		return competency2;
	}
	public void setCompetency2(Competency competency2) {
		this.competency2 = competency2;
	}
	public int getYearsofexp2() {
		return yearsofexp2;
	}
	public void setYearsofexp2(int yearsofexp2) {
		this.yearsofexp2 = yearsofexp2;
	}
	public Competency getCompetency3() {
		return competency3;
	}
	public void setCompetency3(Competency competency3) {
		this.competency3 = competency3;
	}
	public int getYearsofexp3() {
		return yearsofexp3;
	}
	public void setYearsofexp3(int yearsofexp3) {
		this.yearsofexp3 = yearsofexp3;
	}
	
	public int getJobid() {
		return jobid;
	}
	
	public void setJobid(int jobid) 
	{
		this.jobid = jobid;
	}
	
	public applyJob(int userId, String mobile, int currCTC, int expCTC, String currLocation,
			int noticeperiod,int jobid) 
	{
		super();
		this.userId=userId;
		Date currDate=new Date(); 
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
		String currentDate= formatter.format(currDate); 
		this.dateApplied =currentDate;
		
		this.mobile = mobile;
		this.currCTC = currCTC;
		this.expCTC = expCTC;
		this.currLocation = currLocation;
		this.noticeperiod = noticeperiod;

		this.jobid=jobid;
		
	}
	
	public applyJob(int userId, String mobile, int currCTC, int expCTC, String currLocation,
			int noticeperiod,int jobid,Competency competency1,int yearsofexp1) 
	{
		super();
		this.userId=userId;
		Date currDate=new Date(); 
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
		String currentDate= formatter.format(currDate); 
		this.dateApplied =currentDate;
		
		this.mobile = mobile;
		this.currCTC = currCTC;
		this.expCTC = expCTC;
		this.currLocation = currLocation;
		this.noticeperiod = noticeperiod;

		this.jobid=jobid;
		this.competency1=competency1;
		this.yearsofexp1=yearsofexp1;
		CompetencyView.createEntryForCompetencyMap(userId,competency1.getId(),yearsofexp1);
		
		
	}
	
	public applyJob(int userId, String mobile, int currCTC, int expCTC, String currLocation,
			int noticeperiod,int jobid,Competency competency1,int yearsofexp1,Competency competency2,int yearsofexp2) 
	{
		super();
		this.userId=userId;
		Date currDate=new Date(); 
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
		String currentDate= formatter.format(currDate); 
		this.dateApplied =currentDate;
		
		this.mobile = mobile;
		this.currCTC = currCTC;
		this.expCTC = expCTC;
		this.currLocation = currLocation;
		this.noticeperiod = noticeperiod;

		this.jobid=jobid;
		this.competency1=competency1;
		this.yearsofexp1=yearsofexp1;
		CompetencyView.createEntryForCompetencyMap(userId,competency1.getId(),yearsofexp1);
		this.competency2=competency2;
		this.yearsofexp2=yearsofexp2;
		CompetencyView.createEntryForCompetencyMap(userId,competency2.getId(),yearsofexp2);
		
		
		
		
	}
	

	public applyJob(int userId, String mobile, int currCTC, int expCTC, String currLocation,
			int noticeperiod, int jobid,Competency competency1,int yearsofexp1,Competency competency2,int yearsofexp2,Competency competency3,int yearsofexp3) 
	{
		super();
		this.userId=userId;
		Date currDate=new Date(); 
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
		String currentDate= formatter.format(currDate); 
		this.dateApplied =currentDate;
		
		this.mobile = mobile;
		this.currCTC = currCTC;
		this.expCTC = expCTC;
		this.currLocation = currLocation;
		this.noticeperiod = noticeperiod;

		this.jobid=jobid;
		
		
		this.competency1=competency1;
		this.yearsofexp1=yearsofexp1;
		
		
		CompetencyView.createEntryForCompetencyMap(userId,competency1.getId(),yearsofexp1);
		
		this.competency2=competency2;
		this.yearsofexp2=yearsofexp2;
		CompetencyView.createEntryForCompetencyMap(userId,competency2.getId(),yearsofexp2);
		
		this.competency3=competency3;
		this.yearsofexp3=yearsofexp3;
		CompetencyView.createEntryForCompetencyMap(userId,competency3.getId(),yearsofexp3);
		
		
	}
	@Override
	public String toString() {
		return "applyJob [id=" + id + ", userId=" + userId + ", dateApplied=" + dateApplied + ", mobile=" + mobile
				+ ", currCTC=" + currCTC + ", expCTC=" + expCTC + ", currLocation=" + currLocation + ", noticeperiod="
				+ noticeperiod +  ", jobid=" + jobid + ", competency1=" + competency1
				+ ", yearsofexp1=" + yearsofexp1 + ", competency2=" + competency2 + ", yearsofexp2=" + yearsofexp2
				+ ", competency3=" + competency3 + ", yearsofexp3=" + yearsofexp3 + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
