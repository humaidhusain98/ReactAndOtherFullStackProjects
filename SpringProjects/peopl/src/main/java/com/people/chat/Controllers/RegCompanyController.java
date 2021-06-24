package com.people.chat.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.people.chat.Model.RegCompany;
import com.people.chat.View.RegCompanyView;

@RestController
@RequestMapping("/regcompany")
public class RegCompanyController
{
	@PostMapping("/add")
	public ResponseEntity<Integer> addRegCompany(@RequestParam String name,@RequestParam String description,@RequestParam MultipartFile file)
	{
		int resp=-1;
		try 
		{   
			RegCompany regcompany=new RegCompany();
			regcompany.setId(-1);
			regcompany.setDescription(description);
			regcompany.setName(name);
			resp=RegCompanyView.addRegCompany(regcompany, file);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ResponseEntity.ok().body(resp);
		
	}
	
	
	@PostMapping()
	public ResponseEntity<List<RegCompany>> getAllCompany()
	{
		List<RegCompany> regcompanyList=null;
		try {
			regcompanyList=RegCompanyView.getAllReg();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return ResponseEntity.ok().body(regcompanyList);
	}
	
	
	@PostMapping("/{id}")
	public ResponseEntity<RegCompany> getRegCompanyById(@PathVariable int id)
	{
		RegCompany regcompany=null;
		try {
			regcompany=RegCompanyView.getRegCompanyById(id);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return ResponseEntity.ok().body(regcompany);
	}
	
	
	
	
	
	
	
	@PostMapping("/remove/{id}")
	public ResponseEntity<Boolean> removeRegCompany(@PathVariable int id)
	{
		boolean status=false;
		try {
			status=RegCompanyView.removeRegCompany(id);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return ResponseEntity.ok().body(status);
	}
	
	
	@PostMapping("/edit")
	public ResponseEntity<Boolean> editRegCompany(@RequestParam int id,@RequestParam String name,@RequestParam String description,@RequestParam MultipartFile file)
	{
		boolean resp=false;
		try 
		{   
			RegCompany regcompany=new RegCompany();
			regcompany.setId(id);
			regcompany.setDescription(description);
			regcompany.setName(name);
			resp=RegCompanyView.editRegCompany(regcompany, file);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ResponseEntity.ok().body(resp);
	}
	
	
	
	
	
	
	

}
