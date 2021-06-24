package com.people.chat.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Company {
    Integer id;
    String name;
    String description;
    String imageUrl;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
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
    
    
	public Company(Integer id, String name,String description,String imageUrl) {
    	
        this.id = id;
        this.name = name;
        this.description=description;
        this.imageUrl=imageUrl;
        
    }
	
	  @Override
		public String toString() {
			return "Company [id=" + id + ", name=" + name + ", description=" + description + ", imageUrl=" + imageUrl + "]";
		}
	

    public Company(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Company getCompanyFromSQL(ResultSet result) {
        try {
            return new Company(result.getInt(1), result.getString(2));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

}