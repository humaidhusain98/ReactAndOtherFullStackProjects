package com.people.chat.Model;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

public class Career 
{//CREATE TABLE career(id serial,imageurl VARCHAR(1000),hyperlink VARCHAR(1000),dateadded VARCHAR(20),title VARCHAR,description VARCHAR,keyword VARCHAR,tags VARCHAR,type VARCHAR,PRIMARY KEY(id))
	Integer id;
	String imageUrl;
	String hyperlink;
	String dateAdded;
	String title; 
	String description;
	String keyword;
	String tags;
	String type;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getHyperlink() {
		return hyperlink;
	}
	public void setHyperlink(String hyperlink) {
		this.hyperlink = hyperlink;
	}
	public String getDateAdded() {
		return dateAdded;
	}
	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String toString() {
		return "Career [id=" + id + ", imageUrl=" + imageUrl + ", hyperlink=" + hyperlink + ", dateAdded=" + dateAdded
				+ ", title=" + title + ", description=" + description + ", keyword=" + keyword + ", tags=" + tags
				+ ", type=" + type + "]";
	}
	
	@Autowired
	public Career(Integer id, String imageUrl, String hyperlink, String dateAdded, String title, String description,
			String keyword, String tags, String type) {
	
		super();
		this.id = id;
		this.imageUrl = imageUrl;
		this.hyperlink = hyperlink;
		this.dateAdded = dateAdded;
		this.title = title;
		this.description = description;
		this.keyword = keyword;
		this.tags = tags;
		this.type = type;
	}
	
	
	public Career(String imageUrl, String hyperlink, String dateAdded, String title, String description, String keyword,
			String tags, String type) {
	
		super();
		this.imageUrl = imageUrl;
		this.hyperlink = hyperlink;
		this.dateAdded = dateAdded;
		this.title = title;
		this.description = description;
		this.keyword = keyword;
		this.tags = tags;
		this.type = type;
	}
	
	
	public Career() 
	{
		
	}

	
	
	
	
	
	
	
	
	
	
	
}

