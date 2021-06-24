package com.people.chat.Controllers;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.people.chat.Model.searchJob;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.people.chat.Model.Company;
import com.people.chat.Model.Competency;
import com.people.chat.Model.Job;

import com.people.chat.View.CompanyView;
import com.people.chat.View.CompetencyView;
import com.people.chat.View.JobView;
import com.people.chat.View.SearchJobView;




@RestController
@RequestMapping("/jobs")
public class jobController {

	//Returns a List of all jobs in the DB
//	@GetMapping
//	public ResponseEntity<List<Job>>getAllJobs()
//	{
//		List<Job> joblist=null;
//		try
//		{
//			joblist=JobView.getAllJobs();
//			return ResponseEntity.ok().body(joblist);
//
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//			return ResponseEntity.ok().body(null);
//
//		}
//	}

	@PostMapping()
	public ResponseEntity<List<Job>>getAllJobsWithPagination(@RequestParam int page,@RequestParam  int pagesize)
	{
		List<Job> joblist=null;
		try
		{
			joblist=JobView.getJobsWithPagination(page,pagesize);
			return ResponseEntity.ok().body(joblist);

		}
		catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.ok().body(null);

		}
	}
	
	
	
	
	
	//Selects the jobs in the DB by id and if not present it returns null
	@PostMapping("/{id}")
	public ResponseEntity<Job> getJobById(@PathVariable int id)
	{
		try 
		{
		return ResponseEntity.ok().body(JobView.getJob(id));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.ok().body(null);
		}
	}
	

	//Post Mapping saves the job object passed as JSON to DB
	@PostMapping("/add")
	public ResponseEntity<Integer> saveJobObject(@RequestBody Job job) 
	{
		//Creating a new Company Object if it is not present in company Database
		if(job.getCompanyId()==-1) 
		{
		Company obj = new Company(-1,job.getCompanyTitle());
		Company newobj= CompanyView.createCompany(obj);
		job.setCompanyId(newobj.getId());
		}
		//Creating a new competency if not present
		for(int i=0;i<job.getCompetency().size();i++) 
		{
			Competency obj = job.getCompetency().get(i);
			if(obj.getId()==-1) 
			{
				Competency newobj=CompetencyView.save(obj);
				obj.setId(newobj.getId());
				job.getCompetency().set(i,newobj);
				
			}
			
		}
		//Setting Name and Image Url
//		try 
//		{
//		List<User> users=UserView.getUserFromColValue(job.getPostedByUserId(), "id");
//		User userobj=users.get(0);
//		job.setPostedByName(userobj.getName());
//		job.setPostedByImageUrl(userobj.getProfilePicUrl());
//		}
//		catch(SQLException e) 
//		{
//			e.printStackTrace();
//		}
		int resp=-1;
		try 
		{
	
		resp =	JobView.addJob(job);
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			resp=-1;
		}
		
		return ResponseEntity.ok().body(resp);
	}
	
	
	//Deletes the job specified by the id
	@PostMapping("/delete")
	public ResponseEntity<Boolean> deleteJobObject(@RequestParam("id") Integer id) 
	{
		boolean resp=false;
		try 
		{
		resp =	JobView.removeJobById(id);
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			resp=false;
		}
		
		return ResponseEntity.ok().body(resp);
	}
	
	//Edits or Updates the Job Object passed
	@PostMapping("/edit")
	public ResponseEntity<Boolean> editJobObject(@RequestBody Job job ) 
	{
		boolean resp=false;
		try 
		{
		resp =	JobView.updateJob(job);
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			resp=false;
		}
		
		return ResponseEntity.ok().body(resp);
	}
	
	
	//Searches by Competency object in jobcompetencymetric to get all jobs with competency
	@PostMapping("/competencysearch")
	public ResponseEntity<List<Job>> searchJobByCompetency(@RequestParam("searchKey") String searchKey,@RequestParam int page,@RequestParam  int pagesize)
	{
		int offset=(page-1)*pagesize;
		if(offset!=0)
			offset--;
		List<Job> joblist=null;
		
		try 
		{
			joblist=SearchJobView.searchJobByCompetency(searchKey);
			// Adding pagination
			if(offset>= joblist.size())
			{
				System.out.println("PageSize out of bounds");
				return null;
			}
			else{
				if(joblist.size()<=(pagesize*page-1))
				{
					joblist=joblist.subList(offset,joblist.size());
				}
				else{
					joblist=joblist.subList(offset,offset+pagesize);
				}
			}
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return ResponseEntity.ok().body(joblist);
	}
	
	
	//searches every string variable in jobs object
	@PostMapping("/jobsearch")
	public ResponseEntity<List<Job>> searchJobEveryString(@RequestParam("keyword") String keyword,@RequestParam int page,@RequestParam  int pagesize)
	{
		int offset=(page-1)*pagesize;
		if(offset!=0)
			offset--;
		List<Job> joblist=null;
		try 
		{
			joblist=SearchJobView.searchJobByKeyword(keyword);
			// Adding pagination
			if(offset>= joblist.size())
			{
				System.out.println("PageSize out of bounds");
				return null;
			}
			else{
				if(joblist.size()<=(pagesize*page))
				{
					joblist=joblist.subList(offset,joblist.size());
					System.out.printf(offset+","+ joblist.size());
				}
				else{
					joblist=joblist.subList(offset,offset+pagesize);
				}
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return ResponseEntity.ok().body(joblist);
		
	}
	
	
	//Giving -1 means the parameter is not specified
	@PostMapping("/expsearch")
	public ResponseEntity<List<Job>> searchFilterByExp(@RequestParam("minExp") int minExp
		,	@RequestParam("maxExp") int maxExp,@RequestParam int page,@RequestParam  int pagesize)
	{
		int offset=(page-1)*pagesize;
		if(offset!=0)
			offset--;
		List<Job> joblist=null;
		try 
		{
			List<Job> allJobs=JobView.getAllJobs();
			if(allJobs==null) 
			{
				return null;
			}
			joblist	= SearchJobView.filterByExp(minExp, maxExp, allJobs);
			// Adding pagination
			if(offset>= joblist.size())
			{
				System.out.println("PageSize out of bounds");
				return null;
			}
			else{
				if(joblist.size()<=(pagesize*page))
				{
					joblist=joblist.subList(offset,joblist.size());
				}
				else{
					joblist=joblist.subList(offset,offset+pagesize);
				}
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return ResponseEntity.ok().body(joblist);
		
	}
	
	
	
	@PostMapping("/compensationsearch")
	public ResponseEntity<List<Job>> searchFilterByCompensation(@RequestParam("minSal") int minSal
		,	@RequestParam("maxSal") int maxSal,@RequestParam int page,@RequestParam  int pagesize)
	{
		int offset=(page-1)*pagesize;
		if(offset!=0)
			offset--;
		List<Job> joblist=null;
		try 
		{
			List<Job> allJobs=JobView.getAllJobs();
			if(allJobs==null) 
			{
				return null;
			}
			joblist	= SearchJobView.filterByCompensation(minSal, maxSal, allJobs);
			// Adding pagination
			if(offset>= joblist.size())
			{
				System.out.println("PageSize out of bounds");
				return null;
			}
			else{
				if(joblist.size()<=(pagesize*page))
				{
					joblist=joblist.subList(offset,joblist.size());
				}
				else{
					joblist=joblist.subList(offset,offset+pagesize);
				}
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return ResponseEntity.ok().body(joblist);
		
	}
	
	
	
	
	@PostMapping("/ultimatesearch")
	public ResponseEntity<List<Job>> searchUsingUltimateSearch(
		@RequestParam("keyword") String keyword,
		@RequestParam("minExp") int minExp,
		@RequestParam("maxExp") int maxExp,
		@RequestParam("minSal") int minSal,
		@RequestParam("maxSal") int maxSal,
		@RequestParam int page,
		@RequestParam int pagesize
	)
	{
		int offset=(page-1)*pagesize;
		if(offset!=0)
			offset--;
		List<Job> joblist=null;

		
		try 
		{
			joblist=SearchJobView.UltimateSearchAlgorithm(keyword, minExp, maxExp, minSal, maxSal);
			// Adding pagination
			if(offset>= joblist.size())
			{
				System.out.println("PageSize out of bounds");
				return null;
			}
			else{
				if(joblist.size()<=(pagesize*page))
				{
					joblist=joblist.subList(offset,joblist.size());
				}
				else{
					joblist=joblist.subList(offset,offset+pagesize);
				}
			}
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return ResponseEntity.ok().body(joblist);
	}
	
	

	@PostMapping("/search1keyword")
	public ResponseEntity<List<Job>> search1KeywordFlexible(
			@RequestParam("colName1") String colName1,
			@RequestParam("colVal1") String colVal1,
			@RequestParam int page,@RequestParam  int pagesize
			)
	{
		int offset=(page-1)*pagesize;
		if(offset!=0)
			offset--;
		if(colName1.length()<=1 || colVal1.length()<=1)
		{
			System.out.println("Please Enter valid inputs");
			return null;
		}
		List<Job> joblist=null;
		try 
		{
			joblist=SearchJobView.SearchJob(colName1, colVal1);
			// Adding pagination
			if(offset>= joblist.size())
			{
				System.out.println("PageSize out of bounds");
				return null;
			}
			else{
				if(joblist.size()<=(pagesize*page))
				{
					joblist=joblist.subList(offset,joblist.size());
				}
				else{
					joblist=joblist.subList(offset,offset+pagesize);
				}
			}
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return ResponseEntity.ok().body(joblist);
		
	}
	
	
	
	
	@PostMapping("/search2keyword")
	public ResponseEntity<List<Job>> searchUsing2keywords(
			@RequestParam("colName1") String colName1,
			@RequestParam("colVal1") String colVal1,
			@RequestParam("colName2") String colName2,
			@RequestParam("colVal2") String colVal2,
			@RequestParam int page,@RequestParam  int pagesize
			)
	{
		int offset=(page-1)*pagesize;
		if(offset!=0)
			offset--;
		if(colName1.length()<=1 || colVal1.length()<=1 || colName2.length()<=1 || colVal2.length()<=1)
		{
			System.out.println("Please Enter valid inputs");
			return null;
		}
		List<Job> joblist=null;
		try 
		{
			joblist=SearchJobView.SearchJob2ColumnsANDOperation(colName1, colVal1, colName2, colVal2);
			// Adding pagination
			if(offset>= joblist.size())
			{
				System.out.println("PageSize out of bounds");
				return null;
			}
			else{
				if(joblist.size()<=(pagesize*page))
				{
					joblist=joblist.subList(offset,joblist.size());
				}
				else{
					joblist=joblist.subList(offset,offset+pagesize);
				}
			}
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return ResponseEntity.ok().body(joblist);
		
	}
	
	
	
	
	
	
	
	
	
	@PostMapping("/searchfilter2keywords")
	public ResponseEntity<List<Job>> searchUsing2keywordsANDFilters(
			@RequestParam("colName1") String colName1,
			@RequestParam("colVal1") String colVal1,
			@RequestParam("colName2") String colName2,
			@RequestParam("colVal2") String colVal2,
			@RequestParam("minExp") int minExp,
			@RequestParam("maxExp") int maxExp,
			@RequestParam("minSal") int minSal,
			@RequestParam("maxSal") int maxSal,
			@RequestParam int page,@RequestParam  int pagesize
			)
	{



		int offset=(page-1)*pagesize;
		if(offset!=0)
			offset--;
		List<Job> joblist=null;
		List<Job> afterFilterComp=null;
		try 
		{
			joblist=SearchJobView.SearchJob2ColumnsANDOperation(colName1, colVal1, colName2, colVal2);
			if(joblist==null) 
			{
				return null;
			}
			List<Job> afterFilterExp= SearchJobView.filterByExp(minExp,maxExp ,joblist );
			if(afterFilterExp==null)
			{
				return null;
			}
			afterFilterComp=SearchJobView.filterByCompensation(minSal, maxSal, afterFilterExp);
			if(afterFilterComp==null) 
			{
				return null;
			}
			// Adding pagination
			if(offset>= afterFilterComp.size())
			{
				System.out.println("PageSize out of bounds");
				return null;
			}
			else{
				if(afterFilterComp.size()<=(pagesize*page))
				{
					afterFilterComp=afterFilterComp.subList(offset,afterFilterComp.size());
				}
				else{
					afterFilterComp=afterFilterComp.subList(offset,offset+pagesize);
				}
			}
			return ResponseEntity.ok().body(afterFilterComp);

		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return ResponseEntity.ok().body(afterFilterComp);
		
	}
	
	
	@PostMapping("/locations")
	public ResponseEntity<List<String>> getAllLocations(){
		List<String> locationslist=JobView.getAllLocations();
		return ResponseEntity.ok().body(locationslist);

	}


	@PostMapping("/expminmax")
	public ResponseEntity<List<Integer>> getMinMaxExp(){
		List<Integer> expminmax=JobView.getMaxMinExp();
		return ResponseEntity.ok().body(expminmax);

	}

	@PostMapping("/salaryminmax")
	public ResponseEntity<List<Integer>> getMinMaxSalary(){
		List<Integer> salminmax=JobView.getMaxMinSal();
		return ResponseEntity.ok().body(salminmax);

	}

	@PostMapping("/finalsearch")
	public ResponseEntity<List<Job>> getAllFilteredObjects(@RequestBody searchJob searchJobObj)
	{
		List<Job> jobslist= new ArrayList<Job>();
		System.out.println(searchJobObj);
		jobslist=SearchJobView.FinalSearch(searchJobObj);
		int offset=(searchJobObj.getPage()-1)*searchJobObj.getPagesize();
		if(offset!=0)
			offset--;
		// Adding pagination
		if(jobslist==null)
		{
			System.out.println("Jobs list is null");
			return null;
		}
		if(offset>= jobslist.size())
		{
			System.out.println("PageSize out of bounds");
			return null;
		}
		else{
			if(jobslist.size()<=(searchJobObj.getPagesize()*searchJobObj.getPage()))
			{
				jobslist=jobslist.subList(offset,jobslist.size());
				System.out.printf(offset+","+ jobslist.size());
			}
			else{
				jobslist=jobslist.subList(offset,offset+searchJobObj.getPagesize());
			}
		}


		return ResponseEntity.ok().body(jobslist);
	}


	
	
	
	
	
	
	
}
