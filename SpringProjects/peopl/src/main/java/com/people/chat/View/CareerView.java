package com.people.chat.View;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;


import com.people.chat.Model.Career;

public class CareerView {
	
	public static Integer addCareer(Career career) 
	{
		
		Connection conn=null;
		PreparedStatement ptstmnt=null;
		ResultSet results=null;
		int careerid=-1;
		try 
		{
			 BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
//           System.out.println("dataSource obj ==="+dataSource);
           
			 conn = dataSource.getConnection();
//           System.out.println("conn obj ==="+conn);
			 
			 String query="INSERT INTO public.career(imageurl,hyperlink,dateadded,title,description,keyword,tags,keyword_type) "
					 +"VALUES('"+career.getImageUrl()+"','"+career.getHyperlink()+"','"+career.getDateAdded()+"','"+career.getTitle()
					 +"','"+career.getDescription()+"','"+career.getKeyword()+"','"+career.getTags()+"','"+career.getType()+"')";
           
			 ptstmnt = conn.prepareStatement(query);
//	         System.out.println("ptstmnt obj prepare statment completed  ==="+ptstmnt);
			 
			 ptstmnt.executeUpdate();
	         ptstmnt.close();
	         
	           
	         query = "select max(id) from public.career";
	         ptstmnt=conn.prepareStatement(query);
	         results=ptstmnt.executeQuery();
	         if(results!=null && results.next()) 
	         {
	        	 careerid=results.getInt(1);
	        	 
	         }
	         
	         
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			  if (conn != null) 
			  {
	                try 
	                {
	                    conn.close();
	                    ptstmnt.close();
	                    
	                } 
	                catch (SQLException e) 
	                {
	                    e.printStackTrace();   
	                }
	               
	           } 
			
		}
		
		return careerid;
	}
	
	
	
	public static boolean removeCareer(int id) 
	{
		boolean status=false;
		Connection conn=null;
		PreparedStatement ptstmnt=null;
		try 
		{
			 BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
//           System.out.println("dataSource obj ==="+dataSource);
           
			 conn = dataSource.getConnection();
//           System.out.println("conn obj ==="+conn);
			 
			 String query="DELETE FROM career WHERE id="+id;
           
			 ptstmnt = conn.prepareStatement(query);
//	         System.out.println("ptstmnt obj prepare statment completed  ==="+ptstmnt);
			 
			 ptstmnt.executeUpdate();
			status=true;
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			  if (conn != null) 
			  {
	                try 
	                {
	                    conn.close();
	                    ptstmnt.close();
	                    
	                } 
	                catch (SQLException e) 
	                {
	                    e.printStackTrace();   
	                }
	               
	           } //if block
			
		}
		
		return status;
		
	}
	
	
	
	public static boolean edit(Career career) 
	{
		
		boolean status=false;
		Connection conn=null;
		PreparedStatement ptstmnt=null;
		try 
		{
			 BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
//           System.out.println("dataSource obj ==="+dataSource);
           
			 conn = dataSource.getConnection();
//           System.out.println("conn obj ==="+conn);
			 
			 String query="UPDATE career SET dateadded='"+career.getDateAdded()+"', imageurl='"+career.getImageUrl()
			 +"', hyperlink='"+career.getHyperlink()+"', title='"+career.getTitle()+"', keyword_type='"+career.getType()+"', description='"+career.getDescription()
			 +"', keyword='"+career.getKeyword()+"', tags='"+career.getTags()+"' WHERE id="+career.getId();
			 
			 ptstmnt = conn.prepareStatement(query);
//	         System.out.println("ptstmnt obj prepare statment completed  ==="+ptstmnt);
			 
			 ptstmnt.executeUpdate();
			 status=true;
			
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			
		}
		finally 
		{
			  if (conn != null) 
			  {
	                try 
	                {
	                    conn.close();
	                    ptstmnt.close();
	                    
	                } 
	                catch (SQLException e) 
	                {
	                    e.printStackTrace();   
	                }
	               
	           } //if block
			
			
		}
		
		
		return status;
		
	}
	
	
	public static Career getCareerById(int id) 
	{
		Career obj=null;
		Connection conn=null;
		PreparedStatement ptstmnt=null;
		ResultSet results=null;
		try
		{
			 BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
//           System.out.println("dataSource obj ==="+dataSource);
           
			 conn = dataSource.getConnection();
//           System.out.println("conn obj ==="+conn);
			 
			 String query="SELECT * FROM career WHERE id="+id;
	           
			 ptstmnt = conn.prepareStatement(query);
//	         System.out.println("ptstmnt obj prepare statment completed  ==="+ptstmnt);
			 
	         results=ptstmnt.executeQuery();
	         if(results!=null && results.next()) 
	         {
	        	 obj=createNewCareerObj(results);
	        	 
	         }
			
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			 if (conn != null) 
			  {
	                try 
	                {
	                    conn.close();
	                    ptstmnt.close();
	                    
	                } 
	                catch (SQLException e) 
	                {
	                    e.printStackTrace();   
	                }
	               
	           } //if block
			
		}
		
		
		
		
		return obj;
	}
	
	public static List<Career> getAllCareer() 
	{
		List<Career> careerlist=null;
		Connection conn=null;
		PreparedStatement ptstmnt=null;
		ResultSet results=null;
		try
		{
			 BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
//           System.out.println("dataSource obj ==="+dataSource);
           
			 conn = dataSource.getConnection();
//           System.out.println("conn obj ==="+conn);
			 
			 String query="SELECT * FROM career";
	           
			 ptstmnt = conn.prepareStatement(query);
//	         System.out.println("ptstmnt obj prepare statment completed  ==="+ptstmnt);
			 
	         results=ptstmnt.executeQuery();
	         while(results!=null && results.next()) 
	         {
	        	 if(careerlist==null) 
	        	 {
	        		 careerlist=new ArrayList<Career>();
	        	 }
	        	 Career obj=createNewCareerObj(results);
	        	 careerlist.add(obj);
	        	 
	         }
			
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			 if (conn != null) 
			  {
	                try 
	                {
	                    conn.close();
	                    ptstmnt.close();
	                    
	                } 
	                catch (SQLException e) 
	                {
	                    e.printStackTrace();   
	                }
	               
	           } //if block
			
		}
		
		
		
		
		return careerlist;
	}
	
	

	
	public static Career createNewCareerObj(ResultSet results) 
	{
		Career obj=null;
		try 
		{
			obj=new Career(results.getInt(1),results.getString(2),results.getString(3),results.getString(4),results.getString(5),results.getString(6),results.getString(7),results.getString(8),results.getString(9));
//			obj.setId(results.getInt(1));
//			obj.setImageUrl(results.getString(2));
//			obj.setHyperlink(results.getString(3));
//			obj.setDateAdded(results.getString(4));
//			obj.setTitle(results.getString(5));
//			obj.setDescription(results.getString(6));
//			obj.setKeyword(results.getString(7));
//			obj.setTags(results.getString(8));
//			obj.setType(results.getString(9));
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		return obj;
	}
	
	
	
	
	
	
	
	
	public static void main(String args[]) 
	{
//	Career obj=new Career(1, "ImageUrl2", "HyperLink2", "DateAdded2", "Title2", "Description2","Keyword2","Tags2","Type2");
//	System.out.println(addCareer(obj));
//	System.out.println(getAllCareer());
//		System.out.println(removeCareer(1));
//	System.out.println(getCareerById(3));
		
	
	
	
	}
	
	
	

}
