package com.people.chat.View;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.web.bind.annotation.GetMapping;


import com.people.chat.Model.Competency;
import com.people.chat.Model.applyJob;



public class ApplyJobView
{
	public static boolean addApplyJob(applyJob apply) 
	{
        Connection conn = null;
        PreparedStatement ptstmnt = null;
        boolean status=false;
        String query="";
        try {
            BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
//            System.out.println("dataSource obj ==="+dataSource);
            
            conn = dataSource.getConnection();
//            System.out.println("conn obj ==="+conn);
            
            
            //Inserting into job Table
            if(apply.getCompetency1()==null)
            {
            query="INSERT INTO public.applyjob(userid,dateapplied,mobile,currctc,expctc,currlocation,noticeperiod,competency1id,competency2id,competency3id,jobid) VALUES("
            +apply.getUserId()+",'"+apply.getDateApplied()+"','"+apply.getMobile()+"',"+apply.getCurrCTC()+","+apply.getExpCTC()+",'"+apply.getCurrLocation()+"', "+apply.getNoticeperiod()+" ,"+ "0,0,0,"+apply.getJobid()+")";
            }
            else if(apply.getCompetency2()==null)
            {
            	query="INSERT INTO public.applyjob(userid,dateapplied,mobile,currctc,expctc,currlocation,noticeperiod,competency1id,competency2id,competency3id,jobid) VALUES("
                        +apply.getUserId()+",'"+apply.getDateApplied()+"','"+apply.getMobile()+"',"+apply.getCurrCTC()+","+apply.getExpCTC()+",'"+apply.getCurrLocation()+"', "+apply.getNoticeperiod()+" ,"+apply.getCompetency1().getId()+",0,0,"+apply.getJobid()+")";
            	
            }
            else if(apply.getCompetency3()==null)
            {
            	query="INSERT INTO public.applyjob(userid,dateapplied,mobile,currctc,expctc,currlocation,noticeperiod,competency1id,competency2id,competency3id,jobid) VALUES("
                        +apply.getUserId()+",'"+apply.getDateApplied()+"','"+apply.getMobile()+"',"+apply.getCurrCTC()+","+apply.getExpCTC()+",'"+apply.getCurrLocation()+"', "+apply.getNoticeperiod()+" ,"+apply.getCompetency1().getId()+","+apply.getCompetency2().getId()+",0,"+apply.getJobid()+")";
            	
            }
            else 
            {
            	query="INSERT INTO public.applyjob(userid,dateapplied,mobile,currctc,expctc,currlocation,noticeperiod,competency1id,competency2id,competency3id,jobid) VALUES("
                        +apply.getUserId()+",'"+apply.getDateApplied()+"','"+apply.getMobile()+"',"+apply.getCurrCTC()+","+apply.getExpCTC()+",'"+apply.getCurrLocation()+"', "+apply.getNoticeperiod()+" ,"+apply.getCompetency1().getId()+","+apply.getCompetency2().getId()+","+apply.getCompetency3().getId()+","+apply.getJobid()+")";
            	
            }
            
            ptstmnt = conn.prepareStatement(query);
//            System.out.println("ptstmnt obj prepare statment completed  ==="+ptstmnt);
            
            ptstmnt.executeUpdate();
            ptstmnt.close();
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
		
	}//addApplyJob()
	
	
	
	//remove Job using Job id!!
	
	
	public static boolean removeApplyJobById(int id) 
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
            String query="DELETE FROM public.applyjob WHERE id="+id;
            ptstmnt = conn.prepareStatement(query);
//            System.out.println("ptstmnt obj prepare statment completed  ==="+ptstmnt);
            
            ptstmnt.executeUpdate();
//            System.out.println("ptstmnt obj execute Update completed ==="+ptstmnt);
            ptstmnt.close();
            
         
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
	
	
	
	public static applyJob getApplyJob(int id) 
	{
		
		applyJob applyObj=null;
		Connection conn=null;
		PreparedStatement ptstmnt=null;
		ResultSet results=null;
		try 
		{
			
			BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
//			System.out.println("dataSource obj ==="+dataSource);
			
		    conn = dataSource.getConnection();
//	        System.out.println("conn obj ==="+conn);
	        
	        String query="SELECT * FROM public.applyjob WHERE id="+id;
	        ptstmnt = conn.prepareStatement(query);
//	        System.out.println("ptstmnt obj prepare statment completed  ==="+ptstmnt);
	        
	        results = ptstmnt.executeQuery();
	        
	        if(results!=null && results.next()) 
	        {
	        	applyObj=createNewApplyJobObj(results);
	        	
//	        	int comp1id,comp2id,comp3id;//Default Value for no Additional competency is 0
//	        	comp1id=results.getInt(10);
//	        	comp2id=results.getInt(11);
//	        	comp3id=results.getInt(12);
//	        
//	        	
//	        	
//	        	applyJob objApplyJob=new applyJob(results.getInt(2), results.getString(4), results.getInt(5), results.getInt(6), results.getString(7), results.getInt(8), results.getString(9), results.getInt(13));
//	        	objApplyJob.setId(results.getInt(1));
//	        	objApplyJob.setDateApplied(results.getString(3));
//	        	if(comp1id!=0) 
//	        	{
//	        		System.out.println(comp1id);
//	        		Competency comp=CompetencyMetricView.getCompetency(comp1id);
//	        		int years = CompetencyMetricView.getYearsofExp(objApplyJob.getUserId(), comp1id); 
//	        		objApplyJob.setCompetency1(comp);
//	        		objApplyJob.setYearsofexp1(years);
//	        	}
//	        	if(comp2id!=0) 
//	        	{
//	        		System.out.println(comp2id);
//	        		Competency comp=CompetencyMetricView.getCompetency(comp2id);
//	        		int years = CompetencyMetricView.getYearsofExp(objApplyJob.getUserId(), comp2id); 
//	        		objApplyJob.setCompetency2(comp);
//	        		objApplyJob.setYearsofexp2(years);
//	        	}
//	        	if(comp3id!=0) 
//	        	{
//	        		System.out.println(comp3id);
//	        		Competency comp=CompetencyMetricView.getCompetency(comp3id);
//	        		int years = CompetencyMetricView.getYearsofExp(objApplyJob.getUserId(), comp3id); 
//	        		objApplyJob.setCompetency3(comp);
//	        		objApplyJob.setYearsofexp3(years);
//	        	}
//	        	
//	        	
	        	
	        	
	        	
	        
	        	
//	        	applyObj=objApplyJob;
	        	
//	        	System.out.println(applyObj.toString());
	        }//while loop

		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			System.out.println("Error in Catch !!!!1");
			
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
		return applyObj;
		

		
		
		
	}//applyJob
	
	
	
	
	public static List<applyJob> getAllApplyJobs() 
	{
		
		List <applyJob> listapplyJob=null;
		Connection conn=null;
		PreparedStatement ptstmnt=null;
		ResultSet results=null;
		try 
		{
			
			BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
//			System.out.println("dataSource obj ==="+dataSource);
			
		    conn = dataSource.getConnection();
//	        System.out.println("conn obj ==="+conn);
	        
	        String query="SELECT * FROM public.applyjob";
	        ptstmnt = conn.prepareStatement(query);
//	        System.out.println("ptstmnt obj prepare statment completed  ==="+ptstmnt);
	        
	        results = ptstmnt.executeQuery();
	        
	        while(results!=null && results.next()) 
	        {
	        	if(listapplyJob==null) 
	        	{
	        		listapplyJob=new ArrayList<applyJob>();
	        	}
	       
	        	applyJob obj = createNewApplyJobObj(results);
	        	listapplyJob.add(obj);
	        	
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
		return listapplyJob;
		

		
		
		
	}//getJob
	
//	
//	
//	
//	
//	
//	
	public static applyJob createNewApplyJobObj(ResultSet results) 
	{
		
		applyJob obj=null;
		
		try {
		
    	
    	
        	int comp1id,comp2id,comp3id;//Default Value for no Additional competency is 0
        	comp1id=results.getInt(9);
        	comp2id=results.getInt(10);
        	comp3id=results.getInt(11);
        
        	
        	
        	applyJob objApplyJob=new applyJob(results.getInt(2), results.getString(4), results.getInt(5), results.getInt(6), results.getString(7), results.getInt(8), results.getInt(12));
        	objApplyJob.setId(results.getInt(1));
        	objApplyJob.setDateApplied(results.getString(3));
        	if(comp1id!=0) 
        	{
//        		System.out.println(comp1id);
//        		Competency comp=CompetencyMetricView.getCompetency(comp1id);
        		Competency comp=CompetencyView.getCompetencyObject(comp1id);
        		int years = CompetencyMetricView.getYearsofExp(objApplyJob.getUserId(), comp1id); 
        		objApplyJob.setCompetency1(comp);
        		objApplyJob.setYearsofexp1(years);
        	}
        	if(comp2id!=0) 
        	{
//        		System.out.println(comp2id);
//        		Competency comp=CompetencyMetricView.getCompetency(comp2id);
        		Competency comp=CompetencyView.getCompetencyObject(comp2id);
        		int years = CompetencyMetricView.getYearsofExp(objApplyJob.getUserId(), comp2id); 
        		objApplyJob.setCompetency2(comp);
        		objApplyJob.setYearsofexp2(years);
        	}
        	if(comp3id!=0) 
        	{
//        		System.out.println(comp3id);
//        		Competency comp=CompetencyMetricView.getCompetency(comp3id);
        		Competency comp=CompetencyView.getCompetencyObject(comp3id);
        		int years = CompetencyMetricView.getYearsofExp(objApplyJob.getUserId(), comp3id); 
        		objApplyJob.setCompetency3(comp);
        		objApplyJob.setYearsofexp3(years);
        	}
        	
        	
        	
        	
        	
        
        	
//        	applyObj=objApplyJob;
        	
//        	System.out.println(objApplyJob.toString());
        	obj=objApplyJob;
    	
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return obj;
		
	}
	
	
//	
//	
//	
//	
//	
	public static boolean updateApplyJob(applyJob apply) 
	{
		boolean status=false;
		//Deleting Job
		try 
		{
			status =	removeApplyJobById(apply.getId());
			
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
            String query="";
            //Inserting into job Table
            //Inserting into job Table
            
            if(apply.getCompetency1()==null)
            {
            query="INSERT INTO public.applyjob(id,userid,dateapplied,mobile,currctc,expctc,currlocation,noticeperiod,competency1id,competency2id,competency3id,jobid) VALUES("+apply.getId()+","
            +apply.getUserId()+",'"+apply.getDateApplied()+"','"+apply.getMobile()+"',"+apply.getCurrCTC()+","+apply.getExpCTC()+",'"+apply.getCurrLocation()+"', "+apply.getNoticeperiod()+" ,"+ "0,0,0,"+apply.getJobid()+")";
            }
            else if(apply.getCompetency2()==null)
            {
            	query="INSERT INTO public.applyjob(id,userid,dateapplied,mobile,currctc,expctc,currlocation,noticeperiod,competency1id,competency2id,competency3id,jobid) VALUES("+apply.getId()+","
                        +apply.getUserId()+",'"+apply.getDateApplied()+"','"+apply.getMobile()+"',"+apply.getCurrCTC()+","+apply.getExpCTC()+",'"+apply.getCurrLocation()+"', "+apply.getNoticeperiod()+" ,"+apply.getCompetency1().getId()+",0,0,"+apply.getJobid()+")";
            	
            }
            else if(apply.getCompetency3()==null)
            {
            	query="INSERT INTO public.applyjob(id,userid,dateapplied,mobile,currctc,expctc,currlocation,noticeperiod,competency1id,competency2id,competency3id,jobid) VALUES("+apply.getId()+","
                        +apply.getUserId()+",'"+apply.getDateApplied()+"','"+apply.getMobile()+"',"+apply.getCurrCTC()+","+apply.getExpCTC()+",'"+apply.getCurrLocation()+"', "+apply.getNoticeperiod()+" ,"+apply.getCompetency1().getId()+","+apply.getCompetency2().getId()+",0,"+apply.getJobid()+")";
            	
            }
            else 
            {
            	query="INSERT INTO public.applyjob(id,userid,dateapplied,mobile,currctc,expctc,currlocation,noticeperiod,competency1id,competency2id,competency3id,jobid) VALUES("+apply.getId()+","
                        +apply.getUserId()+",'"+apply.getDateApplied()+"','"+apply.getMobile()+"',"+apply.getCurrCTC()+","+apply.getExpCTC()+",'"+apply.getCurrLocation()+"', "+apply.getNoticeperiod()+" ,"+apply.getCompetency1().getId()+","+apply.getCompetency2().getId()+","+apply.getCompetency3().getId()+","+apply.getJobid()+")";
            	
            }
            
            ptstmnt = conn.prepareStatement(query);
//            System.out.println("ptstmnt obj prepare statment completed  ==="+ptstmnt);
            
            ptstmnt.executeUpdate();
            ptstmnt.close();
            status=true;
//            System.out.println("ptstmnt obj execute Update completed ==="+ptstmnt);

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
//	
//	
//	
//	
//	public static List<applyJob> searchApplyJobByUserId(String userId)
//	{
//	List<Job> searchjobList=null;
//	Set<Integer> jobidlist=new HashSet<Integer>();
//	String query = "SELECT * FROM public.applyjob WHERE userid="+userId;
//	ResultSet results = null;
//    Connection conn = null;
//    PreparedStatement ptstmnt = null;
//    
//    try 
//    {
//    	
//        BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
//        System.out.println("Datasource ===="+dataSource);
//        
//        
//        conn = dataSource.getConnection();
//        System.out.println("conn obj ==="+conn);
//        
//        
//        ptstmnt = conn.prepareStatement(query);
//        System.out.println("ptstmnt obj prepare statment completed  ==="+ptstmnt);
//        
//        
//        results = ptstmnt.executeQuery();
//        System.out.println("ptstmnt obj query execution completed ==="+ptstmnt);
//        
//        //Do something with results
//        while (results != null && results.next()) {
//        	jobidlist.add(results.getInt(2));
//        	
//        }
//    	
//    }
//    catch(SQLException e) 
//    {
//    	e.printStackTrace();
//    	
//    }
//    finally 
//    {
//    	
//    	 if (conn != null) {
//             try {
//                 conn.close();
//                 ptstmnt.close();
//                 
//             } catch (SQLException e) {
//                 // TODO Auto-generated catch block
//                 e.printStackTrace();
//                
//             }
//            
//         } 
//    	
//    	
//    }
//    
//	try 
//	{
//		searchjobList=new ArrayList<Job>();
//		System.out.println("Iterating over jobidlist----------------------:"); 
//	        Iterator<Integer> i = jobidlist.iterator(); 
//	        while (i.hasNext()) 
//	        {
//	        	int id=i.next();
//	        	Job objJob=getJob(id);
//	        	if(objJob!=null)
//	        		searchjobList.add(objJob);
//	        	
//	        }
//	
//	}
//	catch (Exception e) 
//	{
//		e.printStackTrace();
//	}
//		
//		
//		
//		
//		
//	return 	searchjobList;
//	}
	
	
	
	
	public static boolean isApplied(int userId,int jobId) 
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
	        
	        String query="SELECT * FROM public.applyjob WHERE userid="+userId+" AND jobid="+jobId;
	        ptstmnt = conn.prepareStatement(query);
//	        System.out.println("ptstmnt obj prepare statment completed  ==="+ptstmnt);
	        
	        results = ptstmnt.executeQuery();
	        
	        if(results!=null && results.next()) 
	        {
	        	status=true;
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
		return status;
		

	}//isApplied
	
	
	public static applyJob getMyapplicationForJobid(int userId,int jobId) 
	{
		applyJob objApplyJob=null;
		Connection conn=null;
		PreparedStatement ptstmnt=null;
		ResultSet results=null;
		try 
		{
			
			BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
//			System.out.println("dataSource obj ==="+dataSource);
			
		    conn = dataSource.getConnection();
//	        System.out.println("conn obj ==="+conn);
	        
	        String query="SELECT * FROM public.applyjob WHERE userid="+userId+" AND jobid="+jobId;
	        ptstmnt = conn.prepareStatement(query);
//	        System.out.println("ptstmnt obj prepare statment completed  ==="+ptstmnt);
	        
	        results = ptstmnt.executeQuery();
	        
	        if(results!=null && results.next()) 
	        {
	         objApplyJob = createNewApplyJobObj(results);
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
		return objApplyJob;
		

	}//Get USER application
	
	
	public static List<applyJob> getAllMyapplication(int userId) 
	{
		List <applyJob> listapplyJob=null;
		Connection conn=null;
		PreparedStatement ptstmnt=null;
		ResultSet results=null;
		try 
		{
			
			BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
//			System.out.println("dataSource obj ==="+dataSource);
			
		    conn = dataSource.getConnection();
//	        System.out.println("conn obj ==="+conn);
	        
	        String query="SELECT * FROM public.applyjob WHERE userid="+userId;
	        ptstmnt = conn.prepareStatement(query);
//	        System.out.println("ptstmnt obj prepare statment completed  ==="+ptstmnt);
	        
	        results = ptstmnt.executeQuery();
	        
	        while(results!=null && results.next()) 
	        {
	        	if(listapplyJob==null) 
	        	{
	        		listapplyJob=new ArrayList<applyJob>();
	        	}
	       
	        	applyJob obj = createNewApplyJobObj(results);
	        	listapplyJob.add(obj);
	        	
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
		return listapplyJob;
		

		

	}//Get USER application
	
	
	
	public static List<applyJob> getAllApplicationForJobid(int jobid) 
	{
		List <applyJob> listapplyJob=null;
		Connection conn=null;
		PreparedStatement ptstmnt=null;
		ResultSet results=null;
		try 
		{
			
			BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
//			System.out.println("dataSource obj ==="+dataSource);
			
		    conn = dataSource.getConnection();
//	        System.out.println("conn obj ==="+conn);
	        
	        String query="SELECT * FROM public.applyjob WHERE jobid="+jobid;
	        ptstmnt = conn.prepareStatement(query);
//	        System.out.println("ptstmnt obj prepare statment completed  ==="+ptstmnt);
	        
	        results = ptstmnt.executeQuery();
	        
	        while(results!=null && results.next()) 
	        {
	        	if(listapplyJob==null) 
	        	{
	        		listapplyJob=new ArrayList<applyJob>();
	        	}
	       
	        	applyJob obj = createNewApplyJobObj(results);
	        	listapplyJob.add(obj);
	        	
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
		return listapplyJob;
		

		

	}//Get All Applications for a particular job
	
	
	
	public static String getSocialId(int userId) 
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
	        
	        String query="SELECT socialid FROM users WHERE id="+userId;
	        ptstmnt = conn.prepareStatement(query);
//	        System.out.println("ptstmnt obj prepare statment completed  ==="+ptstmnt);
	        
	        results = ptstmnt.executeQuery();
	        
	        if(results!=null && results.next()) 
	        {
	        	return results.getString(1);
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
		return null;
		

	}
	
	
	
	
	
	
	
	
	
	
	public static void main(String args[]) 
	{
//		Competency compObj1=CompetencyView.getCompetencyObject(1);
//		Competency compObj2=CompetencyView.getCompetencyObject(2);
//		applyJob obj=new applyJob(1001, "+919748414152", 20, 33, "Bangalore", 45, "www.googletest.com",11,compObj1,4,compObj2,8);
//		addApplyJob(obj);
		
//		System.out.println(getApplyJob(5).toString());
//		System.out.println(getApplyJob(4));
//		System.out.println(getApplyJob(5));
//		System.out.println(getApplyJob(6));
//add ,get and remove tested--------------------------		
//		System.out.println("-------------Testing isApplied below------------");
//		System.out.println(isApplied(1001, 11));
//		System.out.println(isApplied(1001, 13));
//		System.out.println(isApplied(99, 11));
//		System.out.println(isApplied(20, 8));
//		System.out.println("-------------Testing isApplied Finished------------");
//		System.out.println();
//		System.out.println("-------------Testing getAllApplyJobs below------------");
//		System.out.println(getAllApplyJobs());
//		System.out.println("-------------Testing getAllApplyJobs  finished ------------");
//		System.out.println();
//		System.out.println("-------------Testing getAllApplication For jobid below------------");
//		System.out.println(getAllApplicationForJobid(11));
//		System.out.println("-------------Testing getAllApplyJobs  For jobid finished ------------");
//		System.out.println();
//		System.out.println("-------------Testing getAllMyApplication For userid below------------");
//		System.out.println(getAllMyapplication(99));
//		System.out.println("-------------Testing getAllMyApplication For userid finished ------------");
//		
//		
//		System.out.println(getSocialId(99));
		
		
	}
	
	
	
	
	
	
	
	
}
