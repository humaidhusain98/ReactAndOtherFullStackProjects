package com.people.chat.Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Job{
	
	int id;
	String dateJobCreated;//dd-mm-YYYY Format
	String datetillValid;
	int companyId;
	String companyImageUrl;
	String companyTitle;
	String companyDescription;
	String category1;
	String category2;
	String jobTitle;
	int salaryStart;
	int salaryEnd;
	String currency;
	String city;
	String country;
	int expStart;
	int expEnd;
	String jobDescription;
	String topRequirements;
	List<Competency> competency;
	
	
	//Added Later--------------------------------------------
	int postedByUserId;
	String postedByName;
	String postedByImageUrl;
	
	
	public int getPostedByUserId() {
		return postedByUserId;
	}
	public void setPostedByUserId(int postedByid) {
		this.postedByUserId = postedByid;
	}
	public String getPostedByName() {
		return postedByName;
	}
	public void setPostedByName(String postedByName) {
		this.postedByName = postedByName;
	}
	public String getPostedByImageUrl() {
		return postedByImageUrl;
	}
	public void setPostedByImageUrl(String postedByImageUrl) {
		this.postedByImageUrl = postedByImageUrl;
	}
	//Added Later above Block----------------------------------------
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDateJobCreated() {
		return dateJobCreated;
	}
	public void setDateJobCreated(String dateJobCreated) {
		this.dateJobCreated = dateJobCreated;
	}
	public String getDatetillValid() {
		return datetillValid;
	}
	public void setDatetillValid(String datetillValid) {
		this.datetillValid = datetillValid;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getCompanyImageUrl() {
		return companyImageUrl;
	}
	public void setCompanyImageUrl(String companyImageUrl) {
		this.companyImageUrl = companyImageUrl;
	}
	public String getCompanyTitle() {
		return companyTitle;
	}
	public void setCompanyTitle(String companyTitle) {
		this.companyTitle = companyTitle;
	}
	public String getCompanyDescription() {
		return companyDescription;
	}
	public void setCompanyDescription(String companyDescription) {
		this.companyDescription = companyDescription;
	}
	public String getCategory1() {
		return category1;
	}
	public void setCategory1(String category1) {
		this.category1 = category1;
	}
	public String getCategory2() {
		return category2;
	}
	public void setCategory2(String category2) {
		this.category2 = category2;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public int getSalaryStart() {
		return salaryStart;
	}
	public void setSalaryStart(int salaryStart) {
		this.salaryStart = salaryStart;
	}
	public int getSalaryEnd() {
		return salaryEnd;
	}
	public void setSalaryEnd(int salaryEnd) {
		this.salaryEnd = salaryEnd;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getExpStart() {
		return expStart;
	}
	public void setExpStart(int expStart) {
		this.expStart = expStart;
	}
	public int getExpEnd() {
		return expEnd;
	}
	public void setExpEnd(int expEnd) {
		this.expEnd = expEnd;
	}
	
	
	
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	public String getTopRequirements() {
		return topRequirements;
	}
	public void setTopRequirements(String topRequirements) {
		this.topRequirements = topRequirements;
	}
	
	
	
	
	public List<Competency> getCompetency() {
		return competency;
	}
	public void setCompetency(List<Competency> competency) {
		this.competency = competency;
	}
	
	
	
	public Job() 
	{
		
	}
	
	
	//Constructor with all vals except dateJobCreated and id
	public Job( String datetillValid, int companyId, String companyImageUrl,
			String companyTitle, String companyDescription, String category1, String category2, String jobTitle,
			int salaryStart, int salaryEnd, String currency, String city, String country, int expStart, int expEnd,String jobDescription,String topRequirements,List<Competency> compList) {
		super();
		
		
		//Assigning Date when Job object is created in dd-mm-YYYY format
		Date currDate=new Date(); 
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
		String currentDate= formatter.format(currDate); 
		this.dateJobCreated =currentDate;
		//--------------------------------------------------------------------
		
		
		
		this.datetillValid = datetillValid;
		this.companyId = companyId;
		this.companyImageUrl = companyImageUrl;
		this.companyTitle = companyTitle;
		this.companyDescription = companyDescription;
		this.category1 = category1;
		this.category2 = category2;
		this.jobTitle = jobTitle;
		this.salaryStart = salaryStart;
		this.salaryEnd = salaryEnd;
		this.currency = currency;
		this.city = city;
		this.country = country;
		this.expStart = expStart;
		this.expEnd = expEnd;
		this.jobDescription=jobDescription;
		this.topRequirements=topRequirements;
		this.competency=compList;
		
	}
	
	
	
	public Job( int id,String datetillValid, int companyId, String companyImageUrl,
			String companyTitle, String companyDescription, String category1, String category2, String jobTitle,
			int salaryStart, int salaryEnd, String currency, String city, String country, int expStart, int expEnd,String jobDescription,String topRequirements,List<Competency> compList) {
		super();
		
		this.id=id;
		//Assigning Date when Job object is created in dd-mm-YYYY format
		Date currDate=new Date(); 
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
		String currentDate= formatter.format(currDate); 
		this.dateJobCreated =currentDate;
		//--------------------------------------------------------------------
		
		
		
		this.datetillValid = datetillValid;
		this.companyId = companyId;
		this.companyImageUrl = companyImageUrl;
		this.companyTitle = companyTitle;
		this.companyDescription = companyDescription;
		this.category1 = category1;
		this.category2 = category2;
		this.jobTitle = jobTitle;
		this.salaryStart = salaryStart;
		this.salaryEnd = salaryEnd;
		this.currency = currency;
		this.city = city;
		this.country = country;
		this.expStart = expStart;
		this.expEnd = expEnd;
		this.jobDescription=jobDescription;
		this.topRequirements=topRequirements;
		this.competency=compList;
		
	}
	
	
	
	
	
	
	
	
	
	
	//constructor with all fields for mapping previous job to new job object
//	public Job(int id, String dateJobCreated, String datetillValid, int companyId, String companyImageUrl,
//			String companyTitle, String companyDescription, String category1, String category2, String jobTitle,
//			int salaryStart, int salaryEnd, String currency, String city, String country, int expStart, int expEnd,
//			String jobDescription, String topRequirements, List<Competency> competency) {
//		super();
//		this.id = id;
//		this.dateJobCreated = dateJobCreated;
//		this.datetillValid = datetillValid;
//		this.companyId = companyId;
//		this.companyImageUrl = companyImageUrl;
//		this.companyTitle = companyTitle;
//		this.companyDescription = companyDescription;
//		this.category1 = category1;
//		this.category2 = category2;
//		this.jobTitle = jobTitle;
//		this.salaryStart = salaryStart;
//		this.salaryEnd = salaryEnd;
//		this.currency = currency;
//		this.city = city;
//		this.country = country;
//		this.expStart = expStart;
//		this.expEnd = expEnd;
//		this.jobDescription = jobDescription;
//		this.topRequirements = topRequirements;
//		this.competency = competency;
//	}
	
	
	
	
	
	//constructor with all fields for mapping table to job object 
	public Job(int id, String dateJobCreated, String datetillValid, int companyId, String category1, String category2,
			String jobTitle, int salaryStart, int salaryEnd, String currency, String city, String country, int expStart,
			int expEnd, String jobDescription, String topRequirements) {
		super();
		this.id = id;
		this.dateJobCreated = dateJobCreated;
		this.datetillValid = datetillValid;
		this.companyId = companyId;
		this.category1 = category1;
		this.category2 = category2;
		this.jobTitle = jobTitle;
		this.salaryStart = salaryStart;
		this.salaryEnd = salaryEnd;
		this.currency = currency;
		this.city = city;
		this.country = country;
		this.expStart = expStart;
		this.expEnd = expEnd;
		this.jobDescription = jobDescription;
		this.topRequirements = topRequirements;
	}
	@Override
	public String toString() {
		return "Job [id=" + id + ", dateJobCreated=" + dateJobCreated + ", datetillValid=" + datetillValid
				+ ", companyId=" + companyId + ", companyImageUrl=" + companyImageUrl + ", companyTitle=" + companyTitle
				+ ", companyDescription=" + companyDescription + ", category1=" + category1 + ", category2=" + category2
				+ ", jobTitle=" + jobTitle + ", salaryStart=" + salaryStart + ", salaryEnd=" + salaryEnd + ", currency="
				+ currency + ", city=" + city + ", country=" + country + ", expStart=" + expStart + ", expEnd=" + expEnd
				+ ", jobDescription=" + jobDescription + ", topRequirements=" + topRequirements + ", competency="
				+ competency + ", postedByUserId=" + postedByUserId + ", postedByName=" + postedByName
				+ ", postedByImageUrl=" + postedByImageUrl + "]";
	}
	
	
	
	

	
	
	
	
	
	
	
	

}
