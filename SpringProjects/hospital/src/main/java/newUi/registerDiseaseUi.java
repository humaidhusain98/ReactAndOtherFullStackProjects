package newUi;

import entity.Disease;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class registerDiseaseUi extends JFrame implements ActionListener {

	JPanel panel;
    JLabel name_label, symptom1_label, symptom2_label,symptom3_label,message;
    JTextField  name_text, symptom1_text, symptom2_text,symptom3_text;
    JButton submit, goBack;

    public void registerUi()  {
        
    	//go back
        goBack=new JButton("Go Back");
        goBack.addActionListener(e->{
            new ChoiceUi();
            this.dispose();
        });

    	//name
    	name_label = new JLabel();
    	name_label.setText("Disease Name :");
    	name_text = new JTextField();
    	
        // User Label
    	 symptom1_label = new JLabel();
    	 symptom1_label.setText("Symptom 1:");
    	 symptom1_text = new JTextField();
        
        // Password

    	 symptom2_label = new JLabel();
    	 symptom2_label.setText("Symptom 2:");
    	 symptom2_text = new JTextField();
        
        //Mobile Label
    	 symptom3_label = new JLabel();
    	 symptom3_label.setText("Symptom 3:");
    	 symptom3_text = new JTextField();


        
        // Submit

        submit = new JButton("SUBMIT");

        panel = new JPanel(new GridLayout(5, 1));

        panel.add(name_label);
        panel.add(name_text);
        panel.add(symptom1_label);
        panel.add(symptom1_text);
        panel.add(symptom2_label);
        panel.add(symptom2_text);
        panel.add(symptom3_label);
        panel.add(symptom3_text);
       

        message = new JLabel();
        panel.add(message);
        panel.add(submit);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Adding the listeners to components..
        submit.addActionListener(this);
        add(panel, BorderLayout.CENTER);
        setTitle("Disease Registration !");
        setSize(400, 200);
        setVisible(true);

    }
    
	
	@Override
	public void actionPerformed(ActionEvent e) {
		 String name = name_text.getText();
		 String symptom1 = symptom1_text.getText();
		 String symptom2= symptom2_text.getText();
		 String symptom3 = symptom3_text.getText();
		 Disease diseaseobj=new Disease(-1, name, symptom1, symptom2, symptom3);
//		 if(name.length()>1 && symptom1.length()>1 && symptom2.length()>1 && symptom3.length()>1) 
//		 {
//			 Disease diseaseobj=new Disease(-1, name, symptom1, symptom2, symptom3);
//			 boolean resp = DiseaseController.addDisease(diseaseobj);
//			 if(resp==true)
//			 {
//				 this.dispose();
//			 	JFrame successMessage = new JFrame();
//			 	JOptionPane.showMessageDialog(successMessage, "Disease Registered!");
//			 	
//			 }
//			 else 
//			 {
//				 message.setText("Server Down!");
//			 }
//			 
//		 }
//		 else 
//		 {
//			 message.setText("Enter Valid Details");
//		 }
		
	}

    public static void main(String[] args) {
        new registerDiseaseUi();
    }

	
}
