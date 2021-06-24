package newUi;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoiceUi extends JFrame implements ActionListener {
	
	JPanel panel;
	JButton docReg,docLog,userReg,userLog;

	  
	public ChoiceUi() 
	{
		docReg=new JButton("Doctor Registration");
		docLog=new JButton("Doctor Login");
		userReg=new JButton("User Registration");
		userLog=new JButton("User Login");
		panel=new JPanel(new GridLayout(2, 2,20,20));
		panel.add(docReg);
		panel.add(docLog);
		panel.add(userReg);
		panel.add(userLog);
		docLog.addActionListener(this);
		userLog.addActionListener(this);
		docReg.addActionListener(this);
		userReg.addActionListener(this);
	    add(panel, BorderLayout.CENTER);
	    setTitle("Hospital Management System ");
	    setResizable(false);
	    setSize(1024, 768);
	    setVisible(true);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource().equals(docReg)) 
		{
			this.dispose();
			RegistrationDoctorUi regDocUi = new RegistrationDoctorUi();
		}
		else if(e.getSource().equals(docLog))
		{
			this.dispose();
			LoginUi docLoginUi = new LoginUi(true);
		}
		else if(e.getSource().equals(userLog)) 
		{
			this.dispose();
			LoginUi userLoginUi = new LoginUi(false);
		}
		else 
		{
			this.dispose();
			RegistrationUserUi regUserUi = new RegistrationUserUi();
		}
	}
	
	public static void main(String args[]) {
		new ChoiceUi();
	}
	
}
