package newUi;

import controller.AppointmentController;
import controller.DoctorController;
import entity.Appointment;
import entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BookAppointmentUi extends JFrame implements ItemListener{

	  
	    // label 
	JLabel select_doctor,enter_date,select_time,message;


	    // combobox 
	 JComboBox docnames,time,dateCombo;
	  
	    // textfield to add and delete items 

	
	public BookAppointmentUi(User user) {
        message = new JLabel();
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //doctor
        DoctorController doctorController=new DoctorController();
        List<String> doctornameslist= doctorController.getAllDoctorNames();
        String[] doctornames=new String[doctornameslist.size()];
         doctornames=doctornameslist.toArray(doctornames);
         // create checkbox
        docnames = new JComboBox( doctornames);
        // create textfield
        // add ItemListener
        docnames.addItemListener(this);
        // create labels
        select_doctor = new JLabel("Select Doctor");

        //date
        enter_date=new JLabel("Select Date");
        Date currDate=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String currentDate= formatter.format(currDate);

        Date tomorrow = new Date(currDate.getTime() + (1000 * 60 * 60 * 24));
        String tomorrowDate=formatter.format(tomorrow);

        Date dayAfter = new Date(currDate.getTime() + (1000 * 60 * 60 * 24*2));
        String dayAfterDate=formatter.format(dayAfter);

        Date dayAfterplus1 = new Date(currDate.getTime() + (1000 * 60 * 60 * 24*3));
        String dayAfterDateplus1=formatter.format(dayAfterplus1);

        String datesList[]={tomorrowDate,dayAfterDate,dayAfterDateplus1};
        dateCombo=new JComboBox(datesList);


        String times[]={"10:00AM-10:15AM","10:15AM-10:30AM","10:30AM-10:45AM","10:45AM-11:00AM","11:00AM-11:15AM","11:15AM-11:30AM","11:30AM-11:45AM","12:00PM-12:15PM","12:15PM-12:30PM","12:30PM-12:45PM","12:45PM-01:00PM","01:00PM-01:15PM","01:15PM-01:30PM","01:30PM-01:45PM","01:45PM-02:00PM"};
        select_time=new JLabel("Enter Time");
        time=new JComboBox(times);


  
  
        // create a new panel 
        JPanel p = new JPanel(new GridLayout(5,1));
  
        p.add(select_doctor);
  
        // add combobox to panel 
        p.add(docnames);

        //date
        p.add(enter_date);
        p.add(dateCombo);

        //time
        p.add(select_time);
        p.add(time);

        //top Panel
        JPanel topPanel=new JPanel();
        topPanel.setPreferredSize(new Dimension(50,50));
        topPanel.add(message);

        JPanel bottomPanel=new JPanel();
        bottomPanel.setPreferredSize(new Dimension(50,50));
        JButton bookAppointment = new JButton("Book Appointment");
        bookAppointment.addActionListener(e->{
//            System.out.println( docnames.getSelectedItem());
//            System.out.println(date.getText());
//            System.out.println(time.getSelectedItem());
//            System.out.println(user.getId());
//            System.out.println(doctorController.getDoctorIdByName(docnames.getSelectedItem().toString()))
             String dat=dateCombo.getSelectedItem().toString();
             String tim=time.getSelectedItem().toString();
             int doctorId=doctorController.getDoctorIdByName(docnames.getSelectedItem().toString());
             int usernameId=user.getId();
             String status="AWAITING CONFIRMATION";
             String comment="No Comment";
            int input = JOptionPane.showConfirmDialog(null,
                    "Click yes to confirm booking?", "Confirm Booking",JOptionPane.YES_NO_CANCEL_OPTION);
            if(input==0)
            {
                Appointment obj = new Appointment();
                obj.setDate(dat);
                obj.setTime(tim);
                obj.setDoctorId(doctorId);
                obj.setUsernameId(usernameId);
                obj.setStatus(status);
                obj.setComment(comment);
                AppointmentController appointmentController=new AppointmentController();
                appointmentController.addAppointment(obj);
                JOptionPane.showMessageDialog(null, "Appointment Booked Successfully",
                        "SUCCESS DIALOG", JOptionPane.QUESTION_MESSAGE);
                this.dispose();
            }
            else if(input==1)
            {
                this.dispose();
            }
            else
                {
                //do nothing!!!
            }


            // 0=yes, 1=no, 2=cancel




        });
        bottomPanel.add(bookAppointment);

        setTitle("Book Appointment");
        // add panel to frame
        add(topPanel,BorderLayout.NORTH);
        add(p,BorderLayout.CENTER);
        add(bottomPanel,BorderLayout.SOUTH);

  
        // set the size of frame 
        setSize(600, 400); 
  
        setVisible(true);
		
	
	}
	
	
	
	

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
	}
	
	

	
	
	

}
