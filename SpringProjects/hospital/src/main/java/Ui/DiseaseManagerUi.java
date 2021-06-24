//package Ui;
//
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.nio.channels.NonReadableChannelException;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//
//
//import controller.DiseaseController;
//import entity.Disease;
//
//public class DiseaseManagerUi {
//
//	DiseaseController diseaseController=new DiseaseController();
//	public DiseaseManagerUi()
//	{
//		diseaseController.loadDisease();
//		JFrame f=new JFrame();
//		f.setTitle("Disease Manager Ui Diptanil Sengupta 113");
//		JLabel[] label=new JLabel[15];
//		JPanel panel=new JPanel();
//		int i;
//		for( i=0;i<diseaseController.getSize();i++)
//		{
//		label[i]=new JLabel(diseaseController.getDisease(i).toString());
//		panel.add(label[i]);
//		}
//		for(;i<15;i++)
//		{
//			label[i]=new JLabel();
//			panel.add(label[i]);
//
//		}
//
//
//		JButton addButton=new JButton("Add Disease");
//		addButton.setBounds(130,300,150,80 );
//		f.add(addButton);
//
//
//		addButton.addActionListener(new ActionListener(){
//		    public void actionPerformed(ActionEvent e){
//		    	 String id=JOptionPane.showInputDialog(f,"Enter Disease Id");
//		    	 int i=10;
//		    	 try
//		    	 {
//			      i=Integer.parseInt(id);
//		    	 }
//		    	 catch(Exception exce)
//		    	 {
//		    		 System.out.println("Invalid Id entered");
//		    	 }
//		    	 String name=JOptionPane.showInputDialog(f,"Enter Disease Name");
//		    	 String symptom1= JOptionPane.showInputDialog(f,"Enter Symptom 1");
//		    	 String symptom2= JOptionPane.showInputDialog(f,"Enter Symptom 2");
//		    	 String symptom3= JOptionPane.showInputDialog(f,"Enter Symptom 3");
//		    	 if(name.length()>1 && symptom1.length()>1 && symptom2.length()>1 && symptom3.length()>1)
//		    	 {
//		    		 Disease obj = new Disease(i, name, symptom1, symptom2, symptom3);
//		    		 i--;
//		    		 label[i].setText(obj.toString());
//		    	 }
//
//		    }
//		    });
//
//
//		JButton removeButton=new JButton("Remove Disease");
//		removeButton.setBounds(500,300,150,80 );
//		f.add(removeButton);
//
//
//		removeButton.addActionListener(new ActionListener(){
//		    public void actionPerformed(ActionEvent e){
//
//		        String id=JOptionPane.showInputDialog(f,"Enter Disease Id");
//		        int i=Integer.parseInt(id);
//		        label[i-1].setText("");
//		        diseaseController.removeDisease(i);
//
//
//		    }
//		    });
//
//
//
//
//
//
//
//
//		f.add(panel);
//
//
//		f.setSize(900,600);//400 width and 500 height  /using no layout managers
//		f.setVisible(true);
//
//	}
//
//	public static void main(String args[]) {
//		new DiseaseManagerUi();
//
//	}
//
//
//
//
//
//}