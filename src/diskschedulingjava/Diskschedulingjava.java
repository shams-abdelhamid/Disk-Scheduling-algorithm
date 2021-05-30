
package diskschedulingjava;

import java.util.ArrayList;
import java.util.Scanner;


public class Diskschedulingjava
{

    public static void main(String[] args) {
      
        int choice,repeat;
        
        Scanner s=new Scanner(System.in);
    
        do
        {
          
            Disk d=new Disk();
            System.out.println("Enter your desired disk scheduling algorithms : ");
            System.out.println("1-FCFS");
            System.out.println("2-SCAN");
            System.out.println("3-CSCAN");
            System.out.println("4-LOOK");
            System.out.println("5-CLOOK");
            System.out.println("6-SSTF");
            choice= s.nextInt();
             
            switch (choice) // validation to make sure user choose valid algorithm 
            {
                case 1:
                    d.inputsetup(); //calling method that is responsible for user inputs 
                    d.fcfs();
                    break;
                
                case 2:
                    d.inputsetup();
                    System.out.println("Enter the direction of the head");
                    d.direction=s.next();
                    d.scan();
                    break;
                case 3:
                    d.inputsetup();
                    System.out.println("Enter the direction of the head");
                    d.direction=s.next();
                    d.cscan();
                    break;
                case 4:
                    d.inputsetup();
                    System.out.println("Enter the direction of the head");
                    d.direction=s.next();
                    d.look();
                    break;
                 case 5:
                    d.inputsetup();
                    System.out.println("Enter the direction of the head");
                    d.direction=s.next();
                    d.clook();
                    break;
                case 6:
                    d.inputsetup();
                    d.SSTF();
                    break;
                    default:
                    System.out.println("invalid input");
                
            }
            do 
            {
            System.out.println("Do you want to start over ?");
            System.out.println("1-Yes");
            System.out.println("2-Exit ");
            repeat=s.nextInt();
            }
            while(repeat!=1 && repeat!=2);
        } while(repeat!=2); 
           
        
         
    }
}
