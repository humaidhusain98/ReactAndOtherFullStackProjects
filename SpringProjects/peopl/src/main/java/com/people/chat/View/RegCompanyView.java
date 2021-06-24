package com.people.chat.View;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.web.multipart.MultipartFile;

import com.people.chat.Model.RegCompany;
import com.people.chat.Utils.AwsUtils;

public class RegCompanyView {
	
	public static Integer addRegCompany(RegCompany regcompany,MultipartFile companyimage){
			
		
		int result=-1;
		Connection conn=null;
		PreparedStatement ptstmnt=null;
		ResultSet results=null;
		int regcompanyid=-1;
		try 
		{
			 BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
//           System.out.println("dataSource obj ==="+dataSource);
           
			 conn = dataSource.getConnection();
//           System.out.println("conn obj ==="+conn);
			 
			 String query="INSERT INTO regcompany(name,description) VALUES('"+regcompany.getName()+"','"+regcompany.getDescription()+"')";
			 
			 ptstmnt = conn.prepareStatement(query);
//	         System.out.println("ptstmnt obj prepare statment completed  ==="+ptstmnt);
			 
			 ptstmnt.executeUpdate();
	         ptstmnt.close();
	         
	           
	         query = "select max(id) from public.regcompany";
	         ptstmnt=conn.prepareStatement(query);
	         results=ptstmnt.executeQuery();
	         if(results!=null && results.next()) 
	         {
	        	 regcompanyid=results.getInt(1);
	        	 
	         }
	         regcompany.setId(regcompanyid);
	         
	         boolean imageUpload=AwsUtils.uploadCompanyImg(companyimage, regcompanyid);
	         if(imageUpload==true)
	        	 result=regcompanyid;
	         else
	        	 System.out.println("There has been an error in uploading");
			 
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
		
		return result;
	}//addRegCompany
	
	
	
	
	
	
	
	public static boolean removeRegCompany(int id) {
		boolean result=false;
		Connection conn=null;
		PreparedStatement ptstmnt=null;
		try 
		{
			BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
//          System.out.println("dataSource obj ==="+dataSource);
          
			 conn = dataSource.getConnection();
//          System.out.println("conn obj ==="+conn);
			 
			 String query="DELETE FROM regcompany WHERE id="+id;
			 ptstmnt = conn.prepareStatement(query);
//	         System.out.println("ptstmnt obj prepare statment completed  ==="+ptstmnt);
			 
			 ptstmnt.executeUpdate();
			 
			 result=true;
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
		
		return result;
	}//removeRegCompany
	
	
	
	public static RegCompany getRegCompanyById(int id) {
		RegCompany returnobj=null;
		Connection conn=null;
		PreparedStatement ptstmnt=null;
		ResultSet results=null;
		try 
		{
			BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
//          System.out.println("dataSource obj ==="+dataSource);
          
			 conn = dataSource.getConnection();
//          System.out.println("conn obj ==="+conn);
			 
			 String query="SELECT * FROM regcompany WHERE id="+id;
		     ptstmnt=conn.prepareStatement(query);
	         results=ptstmnt.executeQuery();
			 
			 
			 if(results!=null && results.next()) 
	         {
	        	 returnobj=new RegCompany();
	        	 returnobj.setId(results.getInt(1));
	        	 returnobj.setName(results.getString(2));
	        	 returnobj.setDescription(results.getString(3));
	        	 returnobj.setImageUrl(returnobj.generateFullImageUrl());
	        	 
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
		
		return returnobj;
	}//getRegCompanyById
	
	
	
	public static List<RegCompany> getAllReg() {
		List<RegCompany> returnobj=null;
		Connection conn=null;
		PreparedStatement ptstmnt=null;
		ResultSet results=null;
		try 
		{
			BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
//          System.out.println("dataSource obj ==="+dataSource);
          
			conn = dataSource.getConnection();
//          System.out.println("conn obj ==="+conn);
			 
			String query="SELECT * FROM regcompany";
			ptstmnt=conn.prepareStatement(query);
			results=ptstmnt.executeQuery();
			while(results!=null && results.next()) 
			{
				
			  	 RegCompany obj=new RegCompany();
	        	 obj.setId(results.getInt(1));
	        	 obj.setName(results.getString(2));
	        	 obj.setDescription(results.getString(3));
	        	 obj.setImageUrl(obj.generateFullImageUrl());
	        	 if(returnobj==null) 
	        	 {
	        		 returnobj=new ArrayList<RegCompany>();
	        	 }
	        	 returnobj.add(obj);
				
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
		
		return returnobj;
	}//getAllRegCompany
	
	public static Boolean editRegCompany(RegCompany regcompany,MultipartFile companyimage){
		Connection conn=null;
		PreparedStatement ptstmnt=null;
		ResultSet results=null;
		boolean imageUpload=false;
		try 
		{
			 BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
//           System.out.println("dataSource obj ==="+dataSource);
           
			 conn = dataSource.getConnection();
//           System.out.println("conn obj ==="+conn);
			 
			 String query="UPDATE regcompany SET name='"+regcompany.getName()+"', description='"+regcompany.getDescription()+"' WHERE id="+regcompany.getId();
			 
			 ptstmnt = conn.prepareStatement(query);
//	         System.out.println("ptstmnt obj prepare statment completed  ==="+ptstmnt);
			 
			 ptstmnt.executeUpdate();
	         imageUpload=AwsUtils.uploadCompanyImg(companyimage, regcompany.getId());
			 
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
		
		return imageUpload;
	}//editRegCompany
	
	
	
	
	
	
	
	
	
	
	
	

}
