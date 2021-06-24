/* package codechef; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.regex.Pattern;

/* Name of the class has to be "Main" only if the class is public. */
class Atm
{
    public static void main (String[] args) throws java.lang.Exception
    {
        // your code goes here
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your full name");
        String fullname = sc.nextLine();
        System.out.println("Enter your age");
        int age=sc.nextInt();
        sc.nextLine();
        System.out.println("Enter your fathers full name");
        String fathersname= sc.nextLine();
        System.out.println(fullname);
        System.out.println(age);
        System.out.println(fathersname);


    }

    //    public static void TestCaseGenerator() {
//
//        List<String> random=new ArrayList<>();
//        random.add("KICK");
//        random.add("START");
//        random.add("MAJSNEJDO");
//        try
//        {
//            FileWriter fw=new FileWriter("/home/humaid/Desktop/SpringProjects/competitive/src/output.txt");
//            for(int j=1;j<=100;j++) {
//                StringBuffer src = new StringBuffer();
//                for (int i = 1; i <= 5000; i++) {
//                    int k = i % 3;
//
//                    String text = random.get(k);
//                    src.append(text);
//
//
//                }
//
//                System.out.println(src.toString());
//                fw.write(src.toString()+"\n");
//            }//first for loop
//
//        }
//        catch(IOException e){
//            e.printStackTrace();
//        }
//
//
//    }
}


