package com.people.chat.View;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.web.bind.annotation.GetMapping;


import com.people.chat.Model.Competency;



public class JobCompetencyView {
	
	public static List<Competency> getCompetencyListByJobId(int job_id)
	{
		List<Competency> compList=new ArrayList<Competency>();
		List<Integer> compId=new ArrayList<Integer>();
		Connection conn = null;
        PreparedStatement ptstmnt = null;
        ResultSet results=null;
        try 
        {
            BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
//            System.out.println("dataSource obj ==="+dataSource);
            
            conn = dataSource.getConnection();
//            System.out.println("conn obj ==="+conn);
            
            String query="SELECT * FROM jobcompetencymetric WHERE jobid="+job_id;
            ptstmnt = conn.prepareStatement(query);
            System.out.println("ptstmnt obj prepare statment completed  ==="+ptstmnt);
            
            results = ptstmnt.executeQuery();
//            System.out.println("Query Executed-----------------------"+ptstmnt);
            
            while(results!=null && results.next()) 
	        {
            	compId.add(results.getInt(3));
	        }
            System.out.println(compId);
            
        	
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
        
        for(int i=0;i<compId.size();i++) 
        {
        	int id=compId.get(i);
        	Competency compObj=CompetencyView.getCompetencyObject(id);
        	compList.add(compObj);
//        	System.out.println(compObj.toString());
        	
        }
		
			
		
		return compList;
	}
	
	
	
	public static void main(String args[] ) 
	{
		System.out.println(getCompetencyListByJobId(11));
	}
	
	

}
