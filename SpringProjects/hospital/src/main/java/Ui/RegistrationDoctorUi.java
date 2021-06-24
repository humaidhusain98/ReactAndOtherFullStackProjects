//package Ui;
//
//import java.awt.BorderLayout;
//import java.awt.GridLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JPasswordField;
//import javax.swing.JTextField;
//
//import controller.DoctorController;
//import controller.UserController;
//import entity.Doctor;
//import entity.User;
//
//public class RegistrationDoctorUi extends JFrame implements ActionListener{
//
//	JPanel panel;
//    JLabel name_label,user_label, password_label, message,mobile_label,age_label,department_label;
//    JTextField  name_text,userName_text,mobile_text,age_text,department_text;
//    JPasswordField password_text;
//    JButton submit, cancel;
//    DoctorController doctorController=new DoctorController();
//
//
//public RegistrationDoctorUi()  {
//
//
//	//name
//	name_label = new JLabel();
//	name_label.setText("Doctor Name :");
//	name_text = new JTextField();
//
//    // User Label
//    user_label = new JLabel();
//    user_label.setText("Username :");
//    userName_text = new JTextField();
//
//    // Password
//
//    password_label = new JLabel();
//    password_label.setText("Password :");
//    password_text = new JPasswordField();
//
//    //Mobile Label
//    mobile_label = new JLabel();
//    mobile_label.setText("Mobile :");
//    mobile_text = new JTextField();
//
//    //age Label
//    age_label= new JLabel();
//    age_label.setText("Age :");
//    age_text=new JTextField();
//
//
//    //Department Label
//    department_label = new JLabel();
//    department_label.setText("Department :");
//    department_text =new JTextField();
//
//    // Submit
//
//    submit = new JButton("SUBMIT");
//
//    panel = new JPanel(new GridLayout(7, 1));
//
//    panel.add(name_label);
//    panel.add(name_text);
//    panel.add(user_label);
//    panel.add(userName_text);
//    panel.add(password_label);
//    panel.add(password_text);
//    panel.add(mobile_label);
//    panel.add(mobile_text);
//    panel.add(age_label);
//    panel.add(age_text);
//    panel.add(department_label);
//    panel.add(department_text);
//
//
//    message = new JLabel();
//    panel.add(message);
//    panel.add(submit);
//
//    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//    // Adding the listeners to components..
//    submit.addActionListener(this);
//    add(panel, BorderLayout.CENTER);
//    setTitle("Doctor Registration !");
//    setSize(400, 200);
//    setVisible(true);
//
//}
//
//
//@Override
//public void actionPerformed(ActionEvent e) {
// String name = name_text.getText();
// String userName = userName_text.getText();
// String mobileNumber= mobile_text.getText();
// String password = password_text.getText();
// String age		=  age_text.getText();
// String department = department_text.getText();
//
// if(userName.length()>3 && mobileNumber.length()>8 && password.length()>2 && name.length()>2 && age.length()>1 && department.length()>1 )
// {
//	 if(doctorController.isUsernameValid(userName))
//	 {
//	 Doctor doc=new Doctor(-1, name, department, Integer.parseInt(age), mobileNumber);
//	 doc.setPassword(password);
//	 doc.setUsername(userName);
//	 doctorController.registerDoctor(doc);
//	 message.setText("Doctor Registered!");
//	 }
//	 else
//	 {
//		 message.setText("Username Taken!");
//	 }
// }
// else
// {
//	 message.setText("Enter Valid Details!!");
// }
//
//}
//
//}
