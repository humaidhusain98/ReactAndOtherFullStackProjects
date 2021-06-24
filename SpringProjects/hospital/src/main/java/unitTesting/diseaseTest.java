package unitTesting;

import java.util.List;



import controller.DiseaseController;
import dbHandler.DiseaseDbHandler;
import entity.Disease;
import org.junit.Test;

import static org.junit.Assert.*;

public class diseaseTest {

	@Test
	public void  controllerTest()
	{
	    //reading from file
	    DiseaseDbHandler dbHandler=new DiseaseDbHandler();
	    List<Disease> diseaseListFromFile=dbHandler.readDiseaseFromFile();

	    //reading from controller
        DiseaseController controller=new DiseaseController();
        List<Disease> diseaseListFromController =  controller.getAllDiseases();

		//Checking if they have same data
        assertEquals(diseaseListFromController.toString(),diseaseListFromFile.toString());

	}





}
