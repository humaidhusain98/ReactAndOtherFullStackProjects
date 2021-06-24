package com.people.chat.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.people.chat.Model.Competency;
import com.people.chat.Model.User;
import com.people.chat.Model.applyJob;
import com.people.chat.Utils.AwsUtils;
import com.people.chat.View.ApplyJobView;
import com.people.chat.View.CompetencyView;
import com.people.chat.View.UserView;



@RestController
@RequestMapping("/applyjobs")
public class applyJobController 
{
	//Used by User to get All Job Applications he/she has applied to by giving userid
	@PostMapping("/user/getallapplications/{userid}")
	public ResponseEntity<List<applyJob>> getAllUserAppliedJobs(@PathVariable int userid)
	{
		try 
		{
		return ResponseEntity.ok().body(ApplyJobView.getAllMyapplication(userid));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.ok().body(null);
		}
	}
	
	//Used To Check if user has applied to specific Job giving userid and jobid
	@PostMapping("/user/isApplied")
	public ResponseEntity<Boolean> isAppliedToJob(@RequestParam("userId")int userId,
			@RequestParam("jobId") int jobId)
	{
		boolean resp=ApplyJobView.isApplied(userId, jobId);
		return ResponseEntity.ok().body(resp);
		
	}
	
	//Used By user To apply to a particular job adding applyJob object having all details
	//Note it checks if CV of user is uploaded to AWS if not uploaded then it will return false and not add the object
	//It also check if user has already applied for the job or not
	@PostMapping("/user/applytojob/{accessToken}")
	public ResponseEntity<Boolean> applyToJob(@RequestBody applyJob apply,@PathVariable String accessToken)
	{
		User userobj=null;
		try 
		{
			List<User> users=UserView.getUserFromColValue(apply.getUserId(), "id");
			userobj=users.get(0);
		}
		catch(Exception e) 
		{
			System.out.println("User not found in Database");
			return ResponseEntity.ok().body(false);	
		}
		
		if(userobj.getAccessToken().equals(accessToken)) 
		{
			System.out.println("User Authentication successfull");
			if(ApplyJobView.isApplied(apply.getUserId(), apply.getJobid())) 
			{
				System.out.println("User has already applied for this job! He cannot apply again!!!!");
				return ResponseEntity.ok().body(false);
			
			}
		
			String socialId = ApplyJobView.getSocialId(apply.getUserId());
			if(AwsUtils.isCVUploaded(socialId)==false) 
			{
				System.out.println("-----CV Not uploaded !!!!please upload CV and try again");
				return ResponseEntity.ok().body(false);
			}
			//Creating new competency if not present in the database!!!
			if(apply.getCompetency1()!=null && apply.getCompetency1().getId()==-1) 
			{
				Competency newobj = CompetencyView.save(apply.getCompetency1());
				apply.setCompetency1(newobj);
				System.out.println("New competency created =="+apply.getCompetency1().toString());
			}
			if(apply.getCompetency2()!=null && apply.getCompetency2().getId()==-1) 
			{
				Competency newobj = CompetencyView.save(apply.getCompetency2());
				apply.setCompetency2(newobj);
				System.out.println("New competency created =="+apply.getCompetency2().toString());
			}
			if(apply.getCompetency3()!=null && apply.getCompetency3().getId()==-1) 
			{
				
				Competency newobj = CompetencyView.save(apply.getCompetency3());
				apply.setCompetency3(newobj);
				System.out.println("New competency created =="+apply.getCompetency3().toString());
				
			}
			
			boolean status=ApplyJobView.addApplyJob(apply);
			if(status==true && apply.getCompetency1()!=null) 
			{
				CompetencyView.createEntryForCompetencyMap(apply.getUserId(),apply.getCompetency1().getId(),apply.getYearsofexp1());
			}
			if(status==true && apply.getCompetency2()!=null) 
			{
				CompetencyView.createEntryForCompetencyMap(apply.getUserId(),apply.getCompetency2().getId(),apply.getYearsofexp2());
			}
			if(status==true && apply.getCompetency3()!=null) 
			{
				CompetencyView.createEntryForCompetencyMap(apply.getUserId(),apply.getCompetency3().getId(),apply.getYearsofexp3());
			}
			return ResponseEntity.ok().body(status);
		}
		else 
		{
			System.out.println("Authentication Failed!! Access Token not valid");
			return ResponseEntity.ok().body(false);
		}
	}
	
	//Used to get application for specific job using userId and JobId
	@PostMapping("/user/getapplication")
	public ResponseEntity<applyJob> getApplicationByUserId(@RequestParam("userId") int userId,
			@RequestParam("jobId") int jobId)
	{
		applyJob obj=null;
		obj=ApplyJobView.getMyapplicationForJobid(userId, jobId);
		return ResponseEntity.ok().body(obj);
		
	}
	
	//Used to update previous application by user
	@PostMapping("/user/update")
	public ResponseEntity<Boolean> updateAppliedJob(@RequestBody applyJob apply)
	{
		boolean resp=ApplyJobView.updateApplyJob(apply);
		return ResponseEntity.ok().body(resp);
		
	}
	
	//Gets all job applications for specific job id and is used by the admin
	@PostMapping("/admin/getallapplications")
	public ResponseEntity<List<applyJob>> getAllJobsByJobId(@RequestParam("jobId") int jobId)
	{
		try 
		{
		return ResponseEntity.ok().body(ApplyJobView.getAllApplicationForJobid(jobId));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.ok().body(null);
		}
	}
	
	
	
	//Removes Application for job by applyJob id!!! Returns true if removed Successfully
	@PostMapping("/superadmin/remove")
	public ResponseEntity<Boolean> removeApplyJobById(@RequestParam("id") int id )
	{
		return ResponseEntity.ok().body(ApplyJobView.removeApplyJobById(id));
	}
	
	//Used to get Application for specific apply Job id
	@PostMapping("/superadmin/getapplication/{id}")
	public ResponseEntity<applyJob> getApplyJobById(@PathVariable int id )
	{
		return ResponseEntity.ok().body(ApplyJobView.getApplyJob(id));
	}
	
	
	//Used to get All Applications
	@PostMapping("/superadmin/getallapplication")
	public ResponseEntity<List<applyJob>> getAllapplyJobs( )
	{
		return ResponseEntity.ok().body(ApplyJobView.getAllApplyJobs());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
