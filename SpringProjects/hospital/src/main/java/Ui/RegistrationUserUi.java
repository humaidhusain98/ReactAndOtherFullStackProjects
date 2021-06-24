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
//import controller.UserController;
//import entity.User;
//
//public class RegistrationUserUi extends JFrame implements ActionListener {
//
//		JPanel panel;
//	    JLabel user_label, password_label, message,mobile_label;
//	    JTextField userName_text,mobile_text;
//	    JPasswordField password_text;
//	    JButton submit, cancel;
//	    UserController userController=new UserController();
//
//
//public RegistrationUserUi() {
//
//        // User Label
//        user_label = new JLabel();
//        user_label.setText("User Name :");
//        userName_text = new JTextField();
//
//        //Mobile Label
//        mobile_label = new JLabel();
//        mobile_label.setText("Mobile :");
//        mobile_text = new JTextField();
//
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
//        panel = new JPanel(new GridLayout(4, 1));
//
//        panel.add(user_label);
//        panel.add(userName_text);
//        panel.add(mobile_label);
//        panel.add(mobile_text);
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
//        setTitle("Registtation !");
//        setSize(400, 200);
//        setVisible(true);
//
//    }
//
//
//@Override
//public void actionPerformed(ActionEvent e) {
//	 String userName = userName_text.getText();
//	 String mobileNumber= mobile_text.getText();
//     String password = password_text.getText();
//     if(userName.length()>3 && mobileNumber.length()>8 && password.length()>2 )
//     {
//    	 if(userController.isUsernameValid(userName))
//    	 {
//    	 User user=new User(-1, userName, mobileNumber);
//    	 user.setPassword(password);
//    	 userController.registerUser(user);
//    	 message.setText("User Registered");
//    	 }
//    	 else
//    	 {
//    		 message.setText("Username Taken!");
//    	 }
//     }
//     else
//     {
//    	 message.setText("Enter Valid Details!!");
//     }
//
//}
//
//
//
//}
