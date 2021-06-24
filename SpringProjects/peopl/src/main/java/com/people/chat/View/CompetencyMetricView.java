package com.people.chat.View;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

import com.people.chat.Model.Competency;


public class CompetencyMetricView {

	public static int getYearsofExp(int userId,int competencyId) 
	{
		boolean status=false;
		Connection conn=null;
		PreparedStatement ptstmnt=null;
		ResultSet results=null;
		try 
		{
			
			BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
//			System.out.println("dataSource obj ==="+dataSource);
			
		    conn = dataSource.getConnection();
//	        System.out.println("conn obj ==="+conn);
	        
	        String query="SELECT * FROM usercompetencymetric WHERE userid="+userId+" AND competencyid="+competencyId;
	        ptstmnt = conn.prepareStatement(query);
	        System.out.println("ptstmnt obj prepare statment completed  ==="+ptstmnt);
	        
	        results = ptstmnt.executeQuery();
	        
	        if(results!=null && results.next()) 
	        {
	        	return results.getInt(4);
	        }
	        
	        
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			status=false;
		}
		finally 
		{
			  if (conn != null) 
			  {
	                try 
	                {
	                    conn.close();
	                    if (results != null)
	                        results.close();
	                    ptstmnt.close();
	                }
	                catch (SQLException e) 
	                {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	          }
			
		}
		return 0;
		

	}
	
	
	
	
}
