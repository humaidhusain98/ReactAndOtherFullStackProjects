package newUi;

import controller.DiseaseController;
import controller.DoctorController;
import entity.Disease;
import entity.Doctor;

import javax.swing.*;
import java.util.List;

public class allDiseaseUi extends JFrame {

    JTable diseaseTable;
    JScrollPane diseaseScroll;
    String[][] diseaseData;
    String[] columns={"ID","DISEASE","SYMPTOM-1","SYMPTOM-2","SYMPTOM-3"};

    public allDiseaseUi()
    {
        this.setTitle("Disease List");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(800,600);

        DiseaseController diseaseController=new DiseaseController();
        List<Disease> diseaseList=diseaseController.getAllDiseases();
        diseaseData = new String[diseaseList.size()][5];
        for(int i=0;i<diseaseList.size();i++)
        {

            diseaseData[i][0]=String.valueOf(diseaseList.get(i).getId());
            diseaseData[i][1]=diseaseList.get(i).getName();
            diseaseData[i][2]=diseaseList.get(i).getSymptom1();
            diseaseData[i][3]=diseaseList.get(i).getSymptom2();
            diseaseData[i][4]=diseaseList.get(i).getSymptom3();
        }
        diseaseTable=new JTable(diseaseData,columns);
        diseaseTable.setBounds(30, 40, 700, 500);
        diseaseScroll = new JScrollPane(diseaseTable);
        add(diseaseScroll);
        this.setResizable(false);
        this.setVisible(true);

    }

    public static void main(String[] args) {
        new allDiseaseUi();
    }


}
