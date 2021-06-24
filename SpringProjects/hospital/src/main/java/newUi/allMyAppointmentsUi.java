package newUi;

import controller.AppointmentController;
import controller.DiseaseController;
import entity.Appointment;
import entity.Disease;
import entity.User;

import javax.swing.*;
import java.util.List;

public class allMyAppointmentsUi extends JFrame {
    JTable appTable;
    JScrollPane appScroll;
    String[][] appData;
    String[] columns={"ID","DATE","TIME","DOCTOR ID","STATUS","COMMENT"};

    public allMyAppointmentsUi(User user)
    {
        this.setTitle("All My Appointments");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(1024,800);

        AppointmentController appointmentController=new AppointmentController();
        List<Appointment> appList=appointmentController.getAllUserAppointments(user.getId());
        appData = new String[appList.size()][6];
        for(int i=0;i<appList.size();i++)
        {

            appData[i][0]=String.valueOf(appList.get(i).getId());
            appData[i][1]=appList.get(i).getDate();
            appData[i][2]=appList.get(i).getTime();
            appData[i][3]=String.valueOf(appList.get(i).getDoctorId());
            appData[i][4]=appList.get(i).getStatus();
            appData[i][5]=appList.get(i).getComment();
        }
        appTable=new JTable(appData,columns);
        appTable.setBounds(30, 40, 700, 500);
        appScroll = new JScrollPane(appTable);
        add(appScroll);
        this.setResizable(false);
        this.setVisible(true);
    }

}
