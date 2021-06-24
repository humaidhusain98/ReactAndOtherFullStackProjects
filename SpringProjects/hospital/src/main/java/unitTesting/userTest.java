package unitTesting;

import controller.UserController;
import dbHandler.UserDbHandler;
import entity.Doctor;
import entity.User;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class userTest {

    @Test
    public void  controllerTest()
    {
        //reading from file
        UserDbHandler dbHandler=new UserDbHandler();
        List<User> userListFromFile=dbHandler.getAllUsersFromFile();

        //reading from controller
        UserController controller=new UserController();
        List<User> userListFromController =  controller.getAllUsers();

        //Checking if they have same data
        assertEquals(userListFromController.toString(),userListFromFile.toString());

    }




}
