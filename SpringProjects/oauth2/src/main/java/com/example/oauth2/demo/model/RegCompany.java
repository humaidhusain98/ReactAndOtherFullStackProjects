package com.example.oauth2.demo.model;

public class RegCompany {

  private Integer id;
  private String name;
  private String description;
  private String imageUrl;
  
  
  public String generateFullImageUrl() {
	  	String bucketName="companyimgbucket"; 
	  	String url="https://companyimgbucket.s3.ap-south-1.amazonaws.com/"+generateUrlKey();
	    return url;
  }
  
  public String generateUrlKey() 
  {
  	String fileName = "profileImg.jpeg";
    String key = this.id + "/" + fileName;
    return key;
  }
  
  
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getImageUrl() {
	return imageUrl;
}
public void setImageUrl(String imageUrl) {
	this.imageUrl = imageUrl;
}
public RegCompany() {
	super();
}

public RegCompany(Integer id, String name, String description, String imageUrl) {
	super();
	this.id = id;
	this.name = name;
	this.description = description;
	this.imageUrl = imageUrl;
}

	@Override
	public String toString() {
		return "RegCompany{" +
				"id=" + id +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", imageUrl='" + imageUrl + '\'' +
				'}';
	}
}
