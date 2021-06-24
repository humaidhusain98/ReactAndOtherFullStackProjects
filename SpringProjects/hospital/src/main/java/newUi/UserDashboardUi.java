package newUi;

import entity.User;

import javax.swing.*;
import java.awt.*;

public class UserDashboardUi extends JFrame {

    JButton allDoctors,allAppointments,confirmedAppointments,pendingAppointments,diseaseList,bookAppointment,cancelAppointment,logout;

    public UserDashboardUi(User user){
        System.out.println();
        this.setLayout(new GridLayout(4,2,10,10));
        allDoctors=new JButton("Doctors List");
        allAppointments=new JButton("All My Appointments");
        pendingAppointments=new JButton("Pending Appointments");
        confirmedAppointments=new JButton("Confirmed Appointments");
        diseaseList=new JButton("Disease List");
        bookAppointment=new JButton("Book Appointment");
        cancelAppointment=new JButton("Cancel Appointment");
        logout = new JButton("Logout");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        allDoctors.addActionListener(e->{
            new allDoctorsUi();
        });

        diseaseList.addActionListener(e->{
            new allDiseaseUi();
        });

        bookAppointment.addActionListener(e->{
            new BookAppointmentUi(user);
        });

        allAppointments.addActionListener(e->{
            new allMyAppointmentsUi(user);
        });

        logout.addActionListener(e->{
            new ChoiceUi();
            this.dispose();
        });


        add(allDoctors);
        add(allAppointments);
        add(pendingAppointments);
        add(confirmedAppointments);
        add(diseaseList);
        add(bookAppointment);
        add(cancelAppointment);
        add(logout);




        this.setResizable(false);
        this.setSize(1024,768);
        this.setVisible(true);
    }




}
