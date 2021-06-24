package controller;

import java.util.List;

import dbHandler.DiseaseDbHandler;
import entity.Disease;

 
public class DiseaseController
{
	 
	
	
		private  List<Disease> disease;
		private  int lastDiseaseId=-1;
		DiseaseDbHandler diseaseDbHandler;
		
		public DiseaseController() 
		{
			diseaseDbHandler=new DiseaseDbHandler();
			this.loadDisease();
			
		}
	
		
		
		public void loadDisease() 
		{
			disease=diseaseDbHandler.readDiseaseFromFile();
			for(int i=0;i<disease.size();i++) 
			{
			  if(disease.get(i).getId()>lastDiseaseId) 
			  {
				lastDiseaseId=disease.get(i).getId();  
			  }
			}
		}
		
		
		public boolean addDisease(Disease d) 
		{
			lastDiseaseId++;
			d.setId(lastDiseaseId);
			System.out.println(d.toString()+"added");
			disease.add(d);	
			boolean resp= diseaseDbHandler.writeDiseaseToFile(d);
			return resp;
		}
		
		public void removeDisease(int id) 
		{
			for(int i=0;i<disease.size();i++)
			{
				Disease d=disease.get(i);
				if(d.getId()==id)
					{disease.remove(d);
					System.out.println(d.toString()+"removed");
					}
			}
			diseaseDbHandler.reWriteDiseaseFile(disease);
			
		}
		
		public List<Disease> getAllDiseases()
		{
				return disease;
			
		}
		
		public Disease getDisease(int id) {
			Disease dis=disease.get(id);
			return dis;
		}
		
		public int getSize() {
			return disease.size();
		} 
	
		public Disease searchDiseaseName(String name) 
		{
			for(int i=0;i<disease.size();i++)
			{
				if(disease.get(i).getName().equals(name)) 
				{
					System.out.println("Disease found in Repo");
					return disease.get(i);
					
				}
				
				
			}
			return null;
			
		}
		
//		public static void main(String args[]) 
//		{
//			DiseaseController dController=new DiseaseController();
//			System.out.println(dController.disease);
//
//			
//		}
		
		
	
	
	
}
