//package Ui;
//
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.ItemEvent;
//import java.awt.event.ItemListener;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.swing.ComboBoxModel;
//import javax.swing.JButton;
//import javax.swing.JComboBox;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JTextField;
//
//import controller.DoctorController;
//import entity.Doctor;
//import org.jdatepicker.impl.JDatePanelImpl;
//import org.jdatepicker.impl.JDatePickerImpl;
//import org.jdatepicker.impl.UtilDateModel;
//
//public class BookAppointmentUi extends JFrame implements ItemListener{
//
//
//	    // label
//	JLabel select_doctor,enter_date,select_time;
//
//	JTextField date;
//	    // combobox
//	 JComboBox docnames,time;
//
//	    // textfield to add and delete items
//
//
//	public BookAppointmentUi() {
//
//        setLayout(new GridLayout());
//
//        //doctor
//        DoctorController doctorController=new DoctorController();
//        List<String> doctornameslist= doctorController.getAllDoctorNames();
//        String[] doctornames=new String[doctornameslist.size()];
//         doctornames=doctornameslist.toArray(doctornames);
//         // create checkbox
//        docnames = new JComboBox( doctornames);
//        // create textfield
//        // add ItemListener
//        docnames.addItemListener(this);
//        // create labels
//        select_doctor = new JLabel("Select Doctor");
//
//        //date
//        enter_date=new JLabel("Enter Date");
//        date=new JTextField();
//
//
//        String times[]={"10:00AM-10:15AM","10:15AM-10:30AM","10:30AM-10:45AM","10:45AM-11:00AM","11:00AM-11:15AM","11:15AM-11:30AM","11:30AM-11:45AM","12:00PM-12:15PM","12:15PM-12:30PM","12:30PM-12:45PM","12:45PM-01:00PM","01:00PM-01:15PM","01:15PM-01:30PM","01:30PM-01:45PM","01:45PM-02:00PM"};
//        select_time=new JLabel("Enter Time");
//        time=new JComboBox(times);
//
//
//
//
//        // create a new panel
//        JPanel p = new JPanel(new GridLayout(5,1));
//
//        p.add(select_doctor);
//
//        // add combobox to panel
//        p.add(docnames);
//
//        //date
//        p.add(enter_date);
//        p.add(date);
//
//        //time
//        p.add(select_time);
//        p.add(time);
//
//
//        setTitle("Book Appointment");
//        // add panel to frame
//        add(p);
//
//
//        // set the size of frame
//        setSize(600, 400);
//
//        setVisible(true);
//
//
//	}
//
//
//
//
//
//	@Override
//	public void itemStateChanged(ItemEvent e) {
//		// TODO Auto-generated method stub
//        System.out.println(date.getText());
//	}
//
//
//	public static void main(String args[])
//	{
//		new BookAppointmentUi();
//	}
//
//
//
//
//}
