package dbHandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import entity.Disease;

public class DiseaseDbHandler {
	
	public  List<Disease> readDiseaseFromFile() {
		String path = System.getProperty("user.dir");
		String filePath=path+"/db/disease.csv";
		String line="";
		try 
		{
			List<Disease> diseaselist=new ArrayList<Disease>();
			BufferedReader br=new BufferedReader(new FileReader(filePath));
			while((line=br.readLine())!=null) 
			{
				String[] values=line.split(",");
				Disease diseaseobj=new Disease(Integer.parseInt(values[0]), values[1], values[2], values[3], values[4]);
				diseaselist.add(diseaseobj);
				//Operations To do
			
			}
			br.close();
			return diseaselist;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}//ReadFile
	
	
	public boolean writeDiseaseToFile(Disease disease)
	
	{
		try 
		{
			String path = System.getProperty("user.dir");
			String filePath=path+"/db/disease.csv";
			FileWriter fw=new FileWriter(filePath,true);
			BufferedWriter bWriter= new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bWriter);
			pw.println(disease.getId()+","+disease.getName()+","+disease.getSymptom1()+","+disease.getSymptom2()+","+disease.getSymptom3());
			pw.flush();
			pw.close();
			System.out.println("Writing to Disease.csv File Done Successfully!!!");
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
		
	}//writeFile
	
	
	public boolean reWriteDiseaseFile(List<Disease> diseaseList) 
	{
		boolean resp=false;
		try 
		{
			String path = System.getProperty("user.dir");
			String filePath=path+"/db/disease.csv";
			FileWriter fw=new FileWriter(filePath);
			BufferedWriter bWriter= new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bWriter);
			for(int i=0;i<diseaseList.size();i++) 
			{
			pw.println(diseaseList.get(i).getId()+","+diseaseList.get(i).getName()+","+diseaseList.get(i).getSymptom1()+","+diseaseList.get(i).getSymptom2()+","+diseaseList.get(i).getSymptom3());
			}
			pw.flush();
			pw.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
		return resp;
	} 
	
	
//	
//	public static void main(String args[]) 
//	{
//
//
//		
//		
//	}
	
	
	

}
