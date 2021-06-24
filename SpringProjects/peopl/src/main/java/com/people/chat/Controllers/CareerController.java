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

import com.people.chat.Model.Career;

import com.people.chat.View.CareerView;

@RestController
@RequestMapping("/career")
public class CareerController {


	
	
	@PostMapping
	public ResponseEntity<List<Career>> getAllCareers()
	{
		List<Career> careerlist=null;
		try 
		{
			careerlist=CareerView.getAllCareer();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		return ResponseEntity.ok().body(careerlist);
	}
	
	
	
	@PostMapping("/add")
	public ResponseEntity<Integer> addCareerObj(@RequestBody Career career)
	{	
		System.out.println(career.toString());
		int resp=-1;
		try 
		{
			resp=CareerView.addCareer(career);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return ResponseEntity.ok().body(resp);
	}
	
	
	//Selects the Career in the DB by id and if not present it returns null
	@PostMapping("/{id}")
	public ResponseEntity<Career> getCareerById(@PathVariable int id)
	{
		try 
		{
		return ResponseEntity.ok().body(CareerView.getCareerById(id));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.ok().body(null);
		}
	}
	
	@PostMapping("/delete")
	public ResponseEntity<Boolean> deleteCareerObjById(@RequestParam("id") Integer id) 
	{
		boolean resp=false;
		try 
		{
		resp =	CareerView.removeCareer(id);
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			resp=false;
		}
		
		return ResponseEntity.ok().body(resp);
	}
	
	
	@PostMapping("/edit")
	public ResponseEntity<Boolean> editCareerObject(@RequestBody Career career ) 
	{
		boolean resp=false;
		try 
		{
		resp =	CareerView.edit(career);
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			resp=false;
		}
		
		return ResponseEntity.ok().body(resp);
	}
	
	
	
	
	
	
	
	
}
