//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
import java.util.*;

public class RGP12020 {
    public static void main(String args[]){
        Scanner sc= new Scanner(System.in);
        int T=sc.nextInt();
        sc.nextLine();
        int t=1;
        while(t<=T)
        {
            String sequence=sc.nextLine();
            List<Integer> kickmaplist = extractKICK(sequence);
            List<Integer> startmaplist= extractSTART(sequence);
            int count=0;
            for(int i=0;i<kickmaplist.size();i++){
                for(int j=0;j<startmaplist.size();j++){
                    if(kickmaplist.get(i)<startmaplist.get(j))
                    {
                        count++;
                    }
                }
            }
            System.out.println("CASE #"+t+": "+count);


            t++;
        }

    }

    public static List<Integer> extractKICK(String sequence){
        List<Integer> kickmaplist =new ArrayList<>();
        for(int i=0;i<(sequence.length()-3);i++)
        {
            String word=sequence.substring(i,i+4);
            if(word.equals("KICK")){
                kickmaplist.add(i);
            }
        }
        return kickmaplist;
    }


    public static List<Integer> extractSTART(String sequence)
    {
        List<Integer> startmaplist =new ArrayList<>();
        for(int i=0;i<(sequence.length()-4);i++)
        {
            String word=sequence.substring(i,i+5);
            if(word.equals("START")){
                startmaplist.add(i);
            }
        }
        return startmaplist;
    }


}
