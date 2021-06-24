package unitTesting;


import controller.DoctorController;

import dbHandler.DoctorDbHandler;
import entity.Appointment;
import entity.Doctor;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class doctorTest {

    @Test
    public void  controllerTest()
    {
        //reading from file
        DoctorDbHandler dbHandler=new DoctorDbHandler();
        List<Doctor> doctorListFromFile=dbHandler.readDoctorFromFile();

        //reading from controller
        DoctorController controller=new DoctorController();
        List<Doctor> doctorListFromController =  controller.getAllDoctors();

        //Checking if they have same data
        assertEquals(doctorListFromController.toString(),doctorListFromFile.toString());

    }



}
