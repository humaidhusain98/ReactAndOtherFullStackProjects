package com.people.chat.Controllers;

import java.sql.SQLException;
import java.util.List;

import javax.swing.text.StyleConstants.ColorConstants;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.people.chat.Common.Constants;
import com.people.chat.Service.UserService;
import com.people.chat.Service.UserServiceImpl;
import com.people.chat.Utils.AwsUtils;
import com.people.chat.View.ApplyJobView;
import com.people.chat.View.UserView;
import com.people.chat.Model.User;






@RestController
@RequestMapping("/cv")
public class CVController 
{
	
		// used to upload CV to S3 Bucket and then maps userid with CVURL in usercvmetric TABLE
		@ResponseBody
	    @RequestMapping(value = "/upload", method = RequestMethod.POST)
	    public ResponseEntity<Boolean> updateCV(@RequestParam("file") MultipartFile file,
	    @RequestParam("userId")  int userId, @RequestParam("accessToken") String accessToken
	    		)  {
			boolean isUpdated=false;
			String socialId = ApplyJobView.getSocialId(userId);
			try 
			{
			List<User> users=UserView.getUserFromColValue(userId, "id");
			User userobj=users.get(0);
			if(userobj.getAccessToken().equals(accessToken)) 
				{
					System.out.println("Authentication successful!!");
					isUpdated = AwsUtils.uploadCV(file, socialId);
					return ResponseEntity.ok().body(isUpdated);
				}
			else 
				{
				System.out.println("User authentication failed!!");
				return ResponseEntity.ok().body(false);
				
				}
			
			
			}
			catch(Exception e) 
			{
			e.printStackTrace();
			
			}
		
			return ResponseEntity.ok().body(isUpdated);
			
	       
	    }

		
//		//Deletes the CV uploaded to s3 Bucket and then removes mapping from usercvmetric
//		@RequestMapping(value = "/delete", method = RequestMethod.POST)
//		public ResponseEntity<Boolean> DeleteCV(@RequestParam("userId") int userId ) 
//		{
//			boolean resp = CVView.isCVPresent(userId);
//			if(resp==false) 
//			{
//				System.out.println("CVURL DATA not present in Database!!!!!!!!!");
//				return ResponseEntity.ok().body(true);
//			}
//			String CVURL= CVView.getCVUrl(userId);
//			resp=credentials.deleteCV(CVURL);
//			resp=CVView.removeCVData(userId);
//			return ResponseEntity.ok().body(resp);
//			
//		}
		
		@RequestMapping(value = "/isuploaded/{userId}", method = RequestMethod.POST)
		public ResponseEntity<Boolean> isCVPresent(@PathVariable int userId) 
		{
			String socialId = ApplyJobView.getSocialId(userId);
			boolean resp=AwsUtils.isCVUploaded(socialId);
			return ResponseEntity.ok().body(resp);
			
	
		}
		
		
		
		
		
}
