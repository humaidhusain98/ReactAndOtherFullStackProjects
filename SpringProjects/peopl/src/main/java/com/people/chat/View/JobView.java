package com.people.chat.View;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.apache.commons.dbcp.BasicDataSource;

import com.people.chat.Model.Company;
import com.people.chat.Model.Competency;
import com.people.chat.Model.Job;
import com.people.chat.Model.User;
import org.springframework.web.bind.annotation.GetMapping;


public class JobView
{
	public static int addJob(Job job) 
	{
        Connection conn = null;
        PreparedStatement ptstmnt = null;
        
        int jobid=-1;
        String competencytag="";
        for(int i=0;i<job.getCompetency().size();i++)
        {
        	competencytag=competencytag+" , "+job.getCompetency().get(i).getName();
		}
        
        try {
            BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
//            System.out.println("dataSource obj ==="+dataSource);
            
            conn = dataSource.getConnection();
//            System.out.println("conn obj ==="+conn);
            
            //Inserting into job Table
            String query="INSERT INTO public.jobs(datejobcreated,datetillvalid,companyid,category1,category2,jobtitle,salarystart,salaryend,currency,city,country,expstart,expend,jobdescription,toprequirements,postedbyuserid,competencytag,companyname) VALUES("
            + "'"+job.getDateJobCreated()+"','"+job.getDatetillValid()+"','"+job.getCompanyId()+"','"+job.getCategory1()
            +"','"+job.getCategory2()+"','"+
            job.getJobTitle()+"', "+job.getSalaryStart()+" ,"+
            job.getSalaryEnd()+",'"+job.getCurrency()+"','"+job.getCity()
            +"','"+job.getCountry()+"',"+job.getExpStart()+","+
            job.getExpEnd()+",'"+job.getJobDescription()+"','"+job.getTopRequirements()+"','"+job.getPostedByUserId()+"','"+competencytag+"','"+job.getCompanyTitle()+"')";
            
            ptstmnt = conn.prepareStatement(query);
//            System.out.println("ptstmnt obj prepare statment completed  ==="+ptstmnt);
            
            ptstmnt.executeUpdate();
            ptstmnt.close();
//            System.out.println("ptstmnt obj execute Update completed ==="+ptstmnt);
            
            ResultSet results=null;
            //Inserting the jobcompetencymetric Table
           
            query = "select max(id) from public.jobs";
            ptstmnt=conn.prepareStatement(query);
            results=ptstmnt.executeQuery();
            
            
            while(results!=null && results.next()) 
            {
            	job.setId(results.getInt(1));
            	jobid=results.getInt(1);
            }
            
            
            for(int i=0;i<job.getCompetency().size();i++) 
            {
            	query="INSERT INTO public.jobcompetencymetric(jobid,competencyid,competencyname) VALUES("+job.getId()+","+job.getCompetency().get(i).getId()+",'"+job.getCompetency().get(i).getName()+"'"+")";
            	ptstmnt = conn.prepareStatement(query);
            	System.out.println("ptstmnt obj prepare statment completed  ==="+ptstmnt);
            
            	ptstmnt.executeUpdate();
//            	System.out.println("ptstmnt obj execute Update completed ==="+ptstmnt);
            }
            

        } catch (SQLException e) {
            System.out.print(e.getMessage());
            jobid=-1;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    ptstmnt.close();
                    
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                   
                }
               
            } 
        }//finally 
        return jobid;
		
	}//addJob(Job job)
	
	
	//remove Job using Job id!!
	
	
	public static boolean removeJobById(int id) 
	{
		boolean status=false;
		Connection conn = null;
        PreparedStatement ptstmnt = null;
        
        try {
            BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
//            System.out.println("dataSource obj ==="+dataSource);
            
            conn = dataSource.getConnection();
//            System.out.println("conn obj ==="+conn);
            
            //Inserting into job Table
            String query="DELETE FROM public.jobs WHERE id="+id;
            ptstmnt = conn.prepareStatement(query);
//            System.out.println("ptstmnt obj prepare statment completed  ==="+ptstmnt);
            
            ptstmnt.executeUpdate();
//            System.out.println("ptstmnt obj execute Update completed ==="+ptstmnt);
            ptstmnt.close();
            
            query="DELETE FROM jobcompetencymetric WHERE jobid="+id;
            ptstmnt = conn.prepareStatement(query);
//            System.out.println("ptstmnt obj prepare statment completed  ==="+ptstmnt);
            ptstmnt.executeUpdate();
//            System.out.println("ptstmnt obj execute Update completed ==="+ptstmnt);
            
            
            
            status=true;
            
         

        } catch (SQLException e) {
            System.out.print(e.getMessage());
            status=false;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    ptstmnt.close();
                    
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                   
                }
               
            } 
        }//finally 
		
		return status;
	}
	
	
	
	public static Job getJob(int id) 
	{
		
		Job objJob=null;
		Connection conn=null;
		PreparedStatement ptstmnt=null;
		ResultSet results=null;
		try 
		{
			
			BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
//			System.out.println("dataSource obj ==="+dataSource);
			
		    conn = dataSource.getConnection();
//	        System.out.println("conn obj ==="+conn);
	        
	        String query="SELECT * FROM public.jobs WHERE id="+id;
	        ptstmnt = conn.prepareStatement(query);
//	        System.out.println("ptstmnt obj prepare statment completed  ==="+ptstmnt);
	        
	        results = ptstmnt.executeQuery();
	        
	        while(results!=null && results.next()) 
	        {
	        	Company companyObj = CompanyView.getCompanyObj(results.getInt(4));
	        	System.out.println(companyObj.toString());
	        	
	        	objJob =new Job(results.getInt(1), results.getString(2), results.getString(3), results.getInt(4), results.getString(5), results.getString(6), results.getString(7), results.getInt(8), results.getInt(9), results.getString(10), results.getString(11), results.getString(12), results.getInt(13), results.getInt(14),  results.getString(15), results.getString(16));
	        	objJob.setCompanyTitle(companyObj.getName());
	        	objJob.setCompanyImageUrl(companyObj.getImageUrl());
	        	objJob.setCompanyDescription(companyObj.getDescription());
	        	List<Competency> compListByJobId=JobCompetencyView.getCompetencyListByJobId(id);
	        	objJob.setCompetency(compListByJobId);
	        	objJob.setPostedByUserId(results.getInt(17));
	        	try 
	    		{
	    		List<User> users=UserView.getUserFromColValue(objJob.getPostedByUserId(), "id");
	    		User userobj=users.get(0);
	    		objJob.setPostedByName(userobj.getName());
	    		objJob.setPostedByImageUrl(userobj.getProfilePicUrl());
	    		}
	    		catch(SQLException e) 
	    		{
	    			e.printStackTrace();
	    		}
	        	
	        	
	        	
//	        	System.out.println("results while loop-------after getting results.getInt------------");
	        	
//	        	System.out.println(objJob.toString());
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
		return objJob;
		

		
		
		
	}//getJob
	
	
	
	
	public static List<Job> getAllJobs() 
	{
		
		List <Job> listJob=null;
		Connection conn=null;
		PreparedStatement ptstmnt=null;
		ResultSet results=null;
		try 
		{
			
			BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
//			System.out.println("dataSource obj ==="+dataSource);
			
		    conn = dataSource.getConnection();
//	        System.out.println("conn obj ==="+conn);
	        
	        String query="SELECT * FROM public.jobs ";
	        ptstmnt = conn.prepareStatement(query);
//	        System.out.println("ptstmnt obj prepare statment completed  ==="+ptstmnt);
	        
	        results = ptstmnt.executeQuery();
	        
	        while(results!=null && results.next()) 
	        {
	        	Job objJob=createNewJobObj(results);
	        	if(listJob==null)
	        	{
	        		listJob=new ArrayList<Job>();
	        	}
	        	
	        	if(objJob!=null) 
	        	{
	        		listJob.add(objJob);
	        		
	        	}
	        	
//	        	System.out.println(objJob.toString());
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
		return listJob;
		

		
		
		
	}//getJob


	public static List<Job> getJobsWithPagination(int page,int pagesize)
	{

		List <Job> listJob=null;
		Connection conn=null;
		PreparedStatement ptstmnt=null;
		ResultSet results=null;
		int offset=(page-1)*pagesize;
		try
		{

			BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
//			System.out.println("dataSource obj ==="+dataSource);

			conn = dataSource.getConnection();
//	        System.out.println("conn obj ==="+conn);

			String query="SELECT * FROM public.jobs OFFSET "+offset+" FETCH FIRST "+pagesize+" ROWS ONLY";
			ptstmnt = conn.prepareStatement(query);
	        System.out.println("ptstmnt obj prepare statment completed  ==="+ptstmnt);

			results = ptstmnt.executeQuery();

			while(results!=null && results.next())
			{
				Job objJob=createNewJobObj(results);
				if(listJob==null)
				{
					listJob=new ArrayList<Job>();
				}

				if(objJob!=null)
				{
					listJob.add(objJob);

				}

//	        	System.out.println(objJob.toString());
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
		return listJob;





	}//getJob


	
	
	
	
	public static Job createNewJobObj(ResultSet results) 
	{
		
		try {
		
    	Company companyObj = CompanyView.getCompanyObj(results.getInt(4));
    	
    	
    	Job objJob =new Job(results.getInt(1), results.getString(2), results.getString(3), results.getInt(4), results.getString(5), results.getString(6), results.getString(7), results.getInt(8), results.getInt(9), results.getString(10), results.getString(11), results.getString(12), results.getInt(13), results.getInt(14),  results.getString(15), results.getString(16));
    	objJob.setCompanyTitle(companyObj.getName());
    	objJob.setCompanyImageUrl(companyObj.getImageUrl());
    	objJob.setCompanyDescription(companyObj.getDescription());
    	List<Competency> compListByJobId=JobCompetencyView.getCompetencyListByJobId(objJob.getId());
    	objJob.setCompetency(compListByJobId);
    	objJob.setPostedByUserId(results.getInt(17));
		try 
		{
		List<User> users=UserView.getUserFromColValue(objJob.getPostedByUserId(), "id");
		User userobj=users.get(0);
		objJob.setPostedByName(userobj.getName());
		objJob.setPostedByImageUrl(userobj.getProfilePicUrl());
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
    	
    	return objJob;
    	
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
			
		}
		
		
	}
	
	
	
	
	
	
	
	public static boolean updateJob(Job job) 
	{
		boolean status=false;
		//Deleting Job
		try 
		{
			removeJobById(job.getId());
			status=true;
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			System.out.println("There has been an error in deleting");
			status=false;
			return status;
		}
		//Creating new Job
		
		Connection conn = null;
        PreparedStatement ptstmnt = null;

        try {
            BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
//            System.out.println("dataSource obj ==="+dataSource);
            
            conn = dataSource.getConnection();
//            System.out.println("conn obj ==="+conn);
            
            //Inserting into job Table
            String query="INSERT INTO public.jobs(id,datejobcreated,datetillvalid,companyid,category1,category2,jobtitle,salarystart,salaryend,currency,city,country,expstart,expend,jobdescription,toprequirements) VALUES("+job.getId()+","
            + "'"+job.getDateJobCreated()+"','"+job.getDatetillValid()+"','"+job.getCompanyId()+"','"+job.getCategory1()+"','"+job.getCategory2()+"','"+job.getJobTitle()+"', "+job.getSalaryStart()+" ,"+job.getSalaryEnd()+",'"+job.getCurrency()
            +"','"+job.getCity()+"','"+job.getCountry()+"',"+job.getExpStart()+","+job.getExpEnd()+
            ",'"+job.getJobDescription()+"','"+job.getTopRequirements()+"','"+job.getPostedByUserId()+"')";
            
            ptstmnt = conn.prepareStatement(query);
//            System.out.println("ptstmnt obj prepare statment completed  ==="+ptstmnt);
            
            ptstmnt.executeUpdate();
            ptstmnt.close();
//            System.out.println("ptstmnt obj execute Update completed ==="+ptstmnt);
            
            
            for(int i=0;i<job.getCompetency().size();i++) 
            {
            	query="INSERT INTO public.jobcompetencymetric(jobid,competencyid,competencyname) VALUES("+job.getId()+","+job.getCompetency().get(i).getId()+",'"+job.getCompetency().get(i).getName()+"'"+")";
            	ptstmnt = conn.prepareStatement(query);
//            	System.out.println("ptstmnt obj prepare statment completed  ==="+ptstmnt);
            
            	ptstmnt.executeUpdate();
//            	System.out.println("ptstmnt obj execute Update completed ==="+ptstmnt);
            }
            status=true;

        } catch (SQLException e) {
            e.printStackTrace();
            status=false;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    ptstmnt.close();
                    
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                   
                }
               
            } 
        }//finally
		return status;
	}


	public static List<String> getAllLocations()
	{

		List <String> locationsList=new ArrayList<String>();
		Connection conn=null;
		PreparedStatement ptstmnt=null;
		ResultSet results=null;
		Set<String> locations=new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
		try
		{

			BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
//			System.out.println("dataSource obj ==="+dataSource);

			conn = dataSource.getConnection();
//	        System.out.println("conn obj ==="+conn);

			String query="SELECT city,country FROM public.jobs ";
			ptstmnt = conn.prepareStatement(query);
//	        System.out.println("ptstmnt obj prepare statment completed  ==="+ptstmnt);

			results = ptstmnt.executeQuery();

			while(results!=null && results.next())
			{
				String city=results.getString(1);
				String country=results.getString(2);
				locations.add(city);
				locations.add(country);
//
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
		Iterator it= locations.iterator();
		while(it.hasNext()){
			locationsList.add(it.next().toString());
		}
		return locationsList;


	}//getAllLocations




	public static List<Integer> getMaxMinSal()
	{
		List<Integer> maxmin=new ArrayList<Integer>();
		Connection conn=null;
		PreparedStatement ptstmnt=null;
		ResultSet results=null;
		try
		{

			BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
//			System.out.println("dataSource obj ==="+dataSource);

			conn = dataSource.getConnection();
//	        System.out.println("conn obj ==="+conn);

			String query="SELECT min(salarystart) FROM jobs";
			ptstmnt = conn.prepareStatement(query);
//	        System.out.println("ptstmnt obj prepare statment completed  ==="+ptstmnt);

			results = ptstmnt.executeQuery();
			if(results!=null && results.next())
			{
				maxmin.add(results.getInt(1));
			}
			ptstmnt.close();
			query="SELECT max(salaryend) FROM jobs";
			ptstmnt=conn.prepareStatement(query);
			results=null;
			results= ptstmnt.executeQuery();
			if(results!=null && results.next())
			{
				maxmin.add(results.getInt(1));
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
		return maxmin;


	}//getMaxMin


	public static List<Integer> getMaxMinExp()
	{
		List<Integer> maxmin=new ArrayList<Integer>();
		Connection conn=null;
		PreparedStatement ptstmnt=null;
		ResultSet results=null;
		try
		{

			BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
//			System.out.println("dataSource obj ==="+dataSource);

			conn = dataSource.getConnection();
//	        System.out.println("conn obj ==="+conn);

			String query="SELECT min(expstart) FROM jobs";
			ptstmnt = conn.prepareStatement(query);
//	        System.out.println("ptstmnt obj prepare statment completed  ==="+ptstmnt);

			results = ptstmnt.executeQuery();
			if(results!=null && results.next())
			{
				maxmin.add(results.getInt(1));
			}
			ptstmnt.close();
			query="SELECT max(expend) FROM jobs";
			ptstmnt=conn.prepareStatement(query);
			results=null;
			results= ptstmnt.executeQuery();
			if(results!=null && results.next())
			{
				maxmin.add(results.getInt(1));
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
		return maxmin;


	}//getMaxMinExp
	
	
	
	
	
	
	
	
	//test Run for JOB OBJ
//	 public static void main(String args[])
//	    {
//adding job and testing-----------------------------------
//		 	List<Competency> compList =new ArrayList<Competency>();
//		 	Competency competency1=new Competency(1, "PYTHON", 1);
//		 	Competency competency2=new Competency(2, "Java", 1);
//		 	Competency competency3=new Competency(3, "C++", 1);
//		 	compList.add(competency1);
//		 	compList.add(competency2);
//		 	compList.add(competency3);
//		 	Company company=new Company(2, "TCS", "Apple the best company------Description--------", "www.google.com");
//		 	Job jobObj=new Job("08-02-2021",company.getId(),company.getImageUrl(),company.getName(),company.getDescription()
//		 			,"DATA SCIENCE","PYTHON","FULL STACK DEVELOPER",6,8,"INR","KOLKATA","INDIA",3,5,"You know full stack Description bro :)","Top Requirements"
//		 			,compList
//		 			);
//		 	System.out.println("JOB OBJECT------"+jobObj.toString());
//		 	addJob(jobObj);
//-Delete Job Testing Working-------------------------------------------------------
//		 System.out.println(removeJobById(10));
//		 System.out.println(removeJobById(2));
//		 System.out.println(removeJobById(3));
//		 System.out.println(removeJobById(4));
//		 System.out.println(removeJobById(5));
//		 System.out.println(removeJobById(6));
//------------------Get Job Test BELOW------------------------------------------

//		 System.out.println(getJob(11));
		 
//-----------------------Testing GetAllJobs Below--------------------------------
		 
//		 List<Job> listjob=getAllJobs();
//		 for(int i=0;i<listjob.size();i++) 
//		 {
//			 System.out.println(listjob.get(i).toString());
//			 
//		 }
//		 
//-------------------------Testing Update Jobs Below-----------------------------		 
		 
//		 	List<Competency> compList =new ArrayList<Competency>();
//		 	Competency competency1=new Competency(1, "PYTHON", 1);
//		 	Competency competency2=new Competency(2, "Java", 1);
//		 	Competency competency3=new Competency(3, "C++", 1);
//		 	compList.add(competency1);
//		 	compList.add(competency2);
//		 	compList.add(competency3);
//		 	Company company=new Company(1, "TCS", "Apple the best company------Description--------", "www.google.com");
//		 	Job jobObj=new Job(13,"14-08-2021",company.getId(),company.getImageUrl(),company.getName(),company.getDescription()
//		 			,"DATA SCIENCE","PYTHON","FRONT END DEVELOPER",6,8,"INR","DELHI","INDIA",3,5,"You know full stack Description bro :)","Top Requirements"
//		 			,compList
//		 			);
//		 	System.out.println("JOB OBJECT------"+jobObj.toString());
//		 	System.out.println(updateJob(jobObj));
//		 
//----------------------Testing searchJobByCompetency(String competencyKey) done below---------------	 	
		 
//		 System.out.println(searchJobByCompetency("py"));
//			System.out.println(getMaxMinExp());
//			System.out.println(getMaxMinSal());

//	    }//main method
	
	
}//class
	
	
	

