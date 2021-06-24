package newUi;

import controller.DoctorController;
import entity.Doctor;

import javax.swing.*;
import java.util.List;

public class allDoctorsUi extends JFrame{

    JTable doctorsTable;
    JScrollPane doctorScroll;
    String[][] doctorData;
    String[] columns={"ID","NAME","DEPARTMENT","AGE","MOBILE"};

    public allDoctorsUi()
    {
        this.setTitle("Doctors List");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(800,600);

        DoctorController doctorController=new DoctorController();
        List<Doctor> doctorList=doctorController.getAllDoctors();
        doctorData = new String[doctorList.size()][5];
        for(int i=0;i<doctorList.size();i++)
        {

            doctorData[i][0]=String.valueOf(doctorList.get(i).getId());
            doctorData[i][1]=doctorList.get(i).getName();
            doctorData[i][2]=doctorList.get(i).getDepartment();
            doctorData[i][3]=String.valueOf(doctorList.get(i).getAge());
            doctorData[i][4]=doctorList.get(i).getMobileNumber();
        }
        doctorsTable=new JTable(doctorData,columns);
        doctorsTable.setBounds(30, 40, 700, 500);
        doctorScroll = new JScrollPane(doctorsTable);
        add(doctorScroll);
        this.setResizable(false);
        this.setVisible(true);

    }

    public static void main(String[] args) {
        new allDoctorsUi();
    }

}
