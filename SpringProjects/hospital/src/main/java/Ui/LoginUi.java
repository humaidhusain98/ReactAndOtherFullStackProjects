//package Ui;
//
//import java.awt.BorderLayout;
//import java.awt.GridLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
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
//import newUi.UserDashboardUi;
//
//public class LoginUi extends JFrame implements ActionListener {
//
//    JPanel panel;
//    JLabel user_label, password_label, message;
//    JTextField userName_text;
//    JPasswordField password_text;
//    JButton submit, cancel;
//    boolean doctor;
//    UserController userController=new UserController();
//    DoctorController doctorController=new DoctorController();
//
//    public LoginUi(boolean doc) {
//        this.doctor=doc;
//        // User Label
//        user_label = new JLabel();
//        user_label.setText("User Name :");
//        userName_text = new JTextField();
//
//        // Password
//
//        password_label = new JLabel();
//        password_label.setText("Password :");
//        password_text = new JPasswordField();
//
//
//        // Submit
//
//        submit = new JButton("SUBMIT");
//
//        panel = new JPanel(new GridLayout(3, 1));
//
//        panel.add(user_label);
//        panel.add(userName_text);
//        panel.add(password_label);
//        panel.add(password_text);
//
//        message = new JLabel();
//        panel.add(message);
//        panel.add(submit);
//
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        // Adding the listeners to components..
//        submit.addActionListener(this);
//        add(panel, BorderLayout.CENTER);
//        setTitle("Please Login Here !");
//        setSize(300, 100);
//        setVisible(true);
//
//    }
//
//
//    @Override
//    public void actionPerformed(ActionEvent ae) {
//        String userName = userName_text.getText();
//        String password = password_text.getText();
//        if(doctor==false)
//        {
//        	User user =userController.validatePasswordandGetUser(userName, password);
//        	if(user==null) {
//        		message.setText(" Invalid user.. ");
//        	}
//        	else
//        	{
//        		message.setText(" User Validated");
//        		new UserDashboardUi();
//        		this.dispose();
//        	}
//        }
//        else
//        {
//        	Doctor doc= doctorController.validatePasswordandGetDoctor(userName, password);
//        	if(doc==null)
//        	{
//        		message.setText("Invalid Doctor");
//        	}
//        	else
//        	{
//        		message.setText(" Doctor Validated");
//        		this.dispose();
//        	}
//
//        }
//
//
//    }
//
//}