package unitTesting;

import controller.AppointmentController;
import controller.DiseaseController;
import dbHandler.AppointmentDbHandler;
import dbHandler.DiseaseDbHandler;
import entity.Appointment;
import entity.Disease;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class appointmentTest {

    @Test
    public void  controllerTest()
    {
        //reading from file
        AppointmentDbHandler dbHandler=new AppointmentDbHandler();
        List<Appointment> appointmentListFromFile=dbHandler.readAllAppointmentsFromFile();

        //reading from controller
        AppointmentController controller=new AppointmentController();
        List<Appointment> appointmentListFromController =  controller.getAllAppointments();

        //Checking if they have same data
        assertEquals(appointmentListFromController.toString(),appointmentListFromFile.toString());

    }




}
