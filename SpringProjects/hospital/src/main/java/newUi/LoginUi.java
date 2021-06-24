package newUi;

import controller.DoctorController;
import controller.UserController;
import entity.Doctor;
import entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUi extends JFrame implements ActionListener {

    JPanel panel;
    JLabel user_label, password_label, message;
    JTextField userName_text;
    JPasswordField password_text;
    JButton submit, cancel;
    boolean doctor;
    UserController userController=new UserController();
    DoctorController doctorController=new DoctorController();
    JButton goBack;


    public LoginUi(boolean doc) {

        goBack=new JButton("Go Back!");

        goBack.addActionListener(e->{
            new ChoiceUi();
            this.dispose();
        });

        this.doctor=doc;
        // User Label
        user_label = new JLabel();
        user_label.setText("User Name :");
        userName_text = new JTextField();
        
        // Password

        password_label = new JLabel();
        password_label.setText("Password :");
        password_text = new JPasswordField();
        

        // Submit

        submit = new JButton("SUBMIT");
        message = new JLabel();


        panel = new JPanel(new GridLayout(3, 1));

        panel.add(user_label);
        panel.add(userName_text);
        panel.add(password_label);
        panel.add(password_text);

        panel.add(goBack);

        panel.add(submit);

        //top Panel
        JPanel topPanel=new JPanel();
        topPanel.add(message);
        topPanel.setPreferredSize(new Dimension(50,50));

        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel bottomPanel=new JPanel();
        bottomPanel.setPreferredSize(new Dimension(50,50));
        // Adding the listeners to components..
        submit.addActionListener(this);



        add(topPanel,BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(bottomPanel,BorderLayout.SOUTH);
        setTitle("Please Login Here !");
        setSize(600, 400);
        setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        String userName = userName_text.getText();
        String password = password_text.getText();
        if(doctor==false)
        {
        	User user =userController.validatePasswordandGetUser(userName, password);
        	if(user==null) {
        		message.setText(" Invalid user.. ");
        	}
        	else 
        	{
        		message.setText(" User Validated");
        		new UserDashboardUi(user);
        		this.dispose();
        	}
        }
        else 
        {
        	Doctor doc= doctorController.validatePasswordandGetDoctor(userName, password);
        	if(doc==null) 
        	{
        		message.setText("Invalid Doctor");
        	}
        	else 
        	{
        		message.setText(" Doctor Validated");
        		this.dispose();
        	}
        	
        }
        

    }

    public static void main(String[] args) {
        new LoginUi(true);
    }

}