package newUi;

import controller.UserController;
import entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationUserUi extends JFrame implements ActionListener {

		JPanel panel;
	    JLabel user_label, password_label, message,mobile_label,confirm_password;
	    JTextField userName_text,mobile_text;
	    JPasswordField password_text,password2_text;
	    JButton submit, back;
	    UserController userController=new UserController();
         JButton goBack;
	
public RegistrationUserUi() {

    goBack=new JButton("Go Back!");

    goBack.addActionListener(e->{
        new ChoiceUi();
        this.dispose();
    });

    message = new JLabel();

    //Bottom Panel
         JPanel bottomPanel=new JPanel();
         bottomPanel.setPreferredSize(new Dimension(50,50));

    //top Panel
        JPanel topPanel=new JPanel();
        topPanel.add(message);
        topPanel.setPreferredSize(new Dimension(50,50));


        // User Label
        user_label = new JLabel();
        user_label.setText("User Name :");
        userName_text = new JTextField();
        
        //Mobile Label
        mobile_label = new JLabel();
        mobile_label.setText("Mobile :");
        mobile_text = new JTextField();
        
        
        // Password

        password_label = new JLabel();
        password_label.setText("Password :");
        password_text = new JPasswordField();
        

        //confirm Password
        confirm_password=new JLabel("Confirm Password");
        password2_text=new JPasswordField();


        // Submit button

        submit = new JButton("SUBMIT");


        //panel

        panel = new JPanel(new GridLayout(5, 1));

        panel.add(user_label);
        panel.add(userName_text);
        panel.add(mobile_label);
        panel.add(mobile_text);
        panel.add(password_label);
        panel.add(password_text);
        panel.add(confirm_password);
        panel.add(password2_text);



        panel.add(goBack);
        panel.add(submit);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Adding the listeners to components..
        submit.addActionListener(this);
          add(topPanel,BorderLayout.NORTH);
         add(panel, BorderLayout.CENTER);
         add(bottomPanel,BorderLayout.SOUTH);
        setTitle("User Registration");
        setSize(600, 400);
        setVisible(true);

    }


@Override
public void actionPerformed(ActionEvent e) {
	 String userName = userName_text.getText();
	 String mobileNumber= mobile_text.getText();
     String password = password_text.getText();
     String confirmPass=password2_text.getText();

     if(userName.length()>3 && mobileNumber.length()>8 && password.length()>2 && password.equals(confirmPass))
     {
    	 if(userController.isUsernameValid(userName))
    	 {
    	 User user=new User(-1, userName, mobileNumber);
    	 user.setPassword(password);
    	 userController.registerUser(user);
    	 message.setText("User Registered");
    	 }
    	 else 
    	 {
    		 message.setText("Username Taken!");
    	 }
     }
     else 
     {
         if(password.equals(confirmPass)==false)
         {
             message.setText("Passwords do not match!");
         }
         else {
             message.setText("Enter Valid Details!!");
         }
     }
	
}

    public static void main(String[] args) {
        new RegistrationUserUi();
    }

	
	
}
