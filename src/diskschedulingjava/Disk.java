
package diskschedulingjava;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Disk {
    public ArrayList<Integer>tracks;
    public int head,upperboundry;
    public float seek_t_cyl=5;
    public String direction;

    public Disk() 
    {
        tracks=new ArrayList<>();
    }
    
    public void inputsetup() // this a method that takes the user inputs and set values to class attributes 
    {
        int req_n,i=0;  //variable to store the number of requests decided by user 
        Scanner scn=new Scanner(System.in); 
        System.out.println("Enter number of requests on cylinder"); 
        req_n=scn.nextInt();
        System.out.println("Enter your disk's upper boundary");
        upperboundry=scn.nextInt(); // assigning upperboundry 
        System.out.println("Enter your requests");
        while(i<req_n) 
        {
            
           int request=scn.nextInt(); // taking requests' inputs 
            
            if(request<upperboundry) // check whether the request is within the boundaries 
            {
            tracks.add(request);  //add the request 
            }
            else
            {
            System.out.println("Request not considered ,it's outside disk boundaries");
            }
            i++;
        }
        System.out.println("Enter initial head position ");
        head=scn.nextInt();
        
        System.out.println("Enter seek time per cylinder in ms ");
        seek_t_cyl=scn.nextFloat();
    }
    
    public void fcfs()
    {
        int total_mov=0;
        System.out.println("Seek sequence : ");
        for(int i=0;i<tracks.size();i++)
        {
            System.out.println(tracks.get(i));
            total_mov+=Math.abs(tracks.get(i)-head); //adding absolute distances to calculate no of head movements 
            head=tracks.get(i); //change head to current request 
        }
        System.out.println("Total number of head movements  :" +total_mov);
        System.out.println("Total seek time  :" +total_mov*seek_t_cyl + " msec");
    }
    
    public void scan()
        {
            ArrayList <Integer> left = new ArrayList<>(); // arraylist to keep requests before head 
            ArrayList <Integer> right = new ArrayList<>(); // arraylist to keep requests after head 
            int total_mov=0;
            switch (direction) //check direction of head
            {
                case"left" :
                    left.add(0); //adding a lower boundry to  the left arraylist
                    break;
                case"right":
                    right.add(upperboundry); //adding a upper boundry to the right arraylist
                    break;
            }
            
             for(int i=0;i<tracks.size();i++) // splitting requests into left and right arraylists 
            {
               if(tracks.get(i)<head)
               left.add(tracks.get(i));
               else if(tracks.get(i)>head)
               right.add(tracks.get(i));
            }
            Collections.sort(left); //sorting the left arraylist 
            Collections.sort(right); //sorting the right arrayliist
            System.out.println("Seek sequence : ");
            for(int j=0;j<2;j++)
            {
                if("left".equals(direction)) //serve tracks in left direction 1 by 1 
                {
                    for(int i=left.size()-1;i>=0;i--)
                    {
                        total_mov+=Math.abs(left.get(i)-head);
                        head=left.get(i);
                        System.out.println(left.get(i));
                    }
                    direction="right"; // direction is reversed  
                }
                else
                {
                    for(int i=0;i<right.size();i++) //serve tracks in right direction 1 by 1 
                    {
                        total_mov+=Math.abs(right.get(i)-head);
                        head=right.get(i);
                        System.out.println(right.get(i));
                    }
                    direction="left"; //direction is reversed 
                }
            }
           
             System.out.println("Total number of head movements  :" +total_mov);
             System.out.println("Total seek time  :" +total_mov*seek_t_cyl+" msec");
        }
    public void cscan()
        {
           ArrayList <Integer> left = new ArrayList<>(); // arraylist to keep requests before head 
            ArrayList <Integer> right = new ArrayList<>(); // arraylist to keep requests after head 
            int total_mov=0;
            left.add(0); //adding a lower boundry to  the left arraylist
            right.add(upperboundry); //adding a upper boundry to the right arraylist
            for(int i=0;i<tracks.size();i++) // splitting requests into left and right arraylists 
            {
               if(tracks.get(i)<head)
               left.add(tracks.get(i));
               else if(tracks.get(i)>head)
               right.add(tracks.get(i));
            }
            Collections.sort(left); //sorting the left arraylist 
            Collections.sort(right); //sorting the right arrayliist
            System.out.println("Seek sequence : ");
         
                if("left".equals(direction)) 
                {
                      for(int i=left.size();i>0;i--) //serve left array tracks in left direction 1 by 1 
                    {   
                          
                          
                        total_mov+=Math.abs(left.get(i-1)-head);
                        head=left.get(i-1);
                        System.out.println(left.get(i-1));
                    }
                      
                        for(int i=right.size();i>0;i--) //serve right array tracks in left direction 1 by 1 
                    {
                        total_mov+=Math.abs(right.get(i-1)-head);
                        head=right.get(i-1);
                        System.out.println(right.get(i-1));
                    }
                   System.out.println("Total number of head movements  :" +total_mov);
                   System.out.println("Total seek time  :" +total_mov*seek_t_cyl+" msec");
                }
                else
                {
                    
                    for(int i=0;i<right.size();i++) //serve right array tracks in right direction 1 by 1 
                    {
                        total_mov+=Math.abs(right.get(i)-head); 
                        head=right.get(i);
                        System.out.println(right.get(i));
                    }
                   
                
                 for(int i=0;i<left.size();i++) //left array tracks in right direction 1 by 1 
                    {
                        
                        total_mov+=Math.abs(left.get(i)-head);
                        head=left.get(i);
                        System.out.println(left.get(i));
                    }
                  System.out.println("Total number of head movements  :" +total_mov);
                  System.out.println("Total seek time  :" +total_mov*seek_t_cyl+" msec");
               
            }
           
             
        }
    
        public void look()
        {
            ArrayList <Integer> left = new ArrayList<>(); // arraylist to keep requests before head 
            ArrayList <Integer> right = new ArrayList<>(); // arraylist to keep requests after head 
            int total_mov=0;
            
             for(int i=0;i<tracks.size();i++) // splitting requests into left and right arraylists 
            {
               if(tracks.get(i)<head)
               left.add(tracks.get(i));
               else if(tracks.get(i)>head)
               right.add(tracks.get(i));
            }
            Collections.sort(left); //sorting the left arraylist 
            Collections.sort(right); //sorting the right arrayliist
            System.out.println("Seek sequence : ");
            for(int j=0;j<2;j++)
            {
                if("left".equals(direction)) //serve tracks in left direction 1 by 1 
                {
                    for(int i=left.size()-1;i>=0;i--)
                    {
                        total_mov+=Math.abs(left.get(i)-head);
                        head=left.get(i);
                        System.out.println(left.get(i));
                    }
                    direction="right"; // direction is reversed  
                }
                else
                {
                    for(int i=0;i<right.size();i++) //serve tracks in right direction 1 by 1 
                    {
                        total_mov+=Math.abs(right.get(i)-head);
                        head=right.get(i);
                        System.out.println(right.get(i));
                    }
                    direction="left"; //direction is reversed 
                }
            }
           
             System.out.println("Total number of head movements  :" +total_mov);
             System.out.println("Total seek time  :" +total_mov*seek_t_cyl+" msec");
        }
    
        
          public void clook()
        {
           ArrayList <Integer> left = new ArrayList<>(); // arraylist to keep requests before head 
            ArrayList <Integer> right = new ArrayList<>(); // arraylist to keep requests after head 
            int total_mov=0;
            for(int i=0;i<tracks.size();i++) // splitting requests into left and right arraylists 
            {
               if(tracks.get(i)<head)
               left.add(tracks.get(i));
               else if(tracks.get(i)>head)
               right.add(tracks.get(i));
            }
            Collections.sort(left); //sorting the left arraylist 
            Collections.sort(right); //sorting the right arrayliist
            System.out.println("Seek sequence : ");
         
                if("left".equals(direction)) 
                {
                      for(int i=left.size();i>0;i--) //serve left array tracks in left direction 1 by 1 
                    {   
                          
                          
                        total_mov+=Math.abs(left.get(i-1)-head);
                        head=left.get(i-1);
                        System.out.println(left.get(i-1));
                    }
                      
                        for(int i=right.size();i>0;i--) //serve right array tracks in left direction 1 by 1 
                    {
                        total_mov+=Math.abs(right.get(i-1)-head);
                        head=right.get(i-1);
                        System.out.println(right.get(i-1));
                    }
                   System.out.println("Total number of head movements  :" +total_mov);
                   System.out.println("Total seek time  :" +total_mov*seek_t_cyl+" msec");
                }
                else
                {
                    
                    for(int i=0;i<right.size();i++) //serve right array tracks in right direction 1 by 1 
                    {
                        total_mov+=Math.abs(right.get(i)-head); 
                        head=right.get(i);
                        System.out.println(right.get(i));
                    }
                   
                
                 for(int i=0;i<left.size();i++) //left array tracks in right direction 1 by 1 
                    {
                        
                        total_mov+=Math.abs(left.get(i)-head);
                        head=left.get(i);
                        System.out.println(left.get(i));
                    }
                  System.out.println("Total number of head movements  :" +total_mov);
                  System.out.println("Total seek time  :" +total_mov*seek_t_cyl+" msec");
               
            }
           
             
        }
        public void SSTF()
        {
            ArrayList <Integer> left = new ArrayList<>(); // arraylist to keep requests before head 
            ArrayList <Integer> right = new ArrayList<>(); // arraylist to keep requests after head 
            int total_mov=0;
            System.out.println("Seek sequence : ");
            for(int i=0;i<tracks.size();i++) // splitting requests into left and right arraylists 
            {
               if(tracks.get(i)<head)
               left.add(tracks.get(i));
               else if(tracks.get(i)>head)
               right.add(tracks.get(i));
            }
            Collections.sort(left); //sorting the left arraylist 
            Collections.sort(right); //sorting the right arrayliist
            for(int k=0;k<tracks.size();k++)         
            {
                
                int Ldistance=head-left.get(left.size()-1);   //calculating distance between head and the first request on it's left side 
                int Rdistance=right.get(0)-head;               //calculating distance between head and the first request on it's right side 
                if(Ldistance<Rdistance)                        //checking which distance is smaller 
                {
                    total_mov+=Math.abs(left.get(left.size()-1)-head);   //adding absolute distances to calculate no of head movements 
                    head=left.get(left.size()-1);                            //changing head to current request 
                    left.remove(left.size()-1);                              // remove already visited request
                    System.out.println(head);                                
                    if(left.isEmpty())                                       // this happens when all left requests are served . 
                    {
                        for(int r=0;r<right.size();r++)                       //serve requests in right direction 
                        {
                            total_mov+=Math.abs(right.get(r)-head);
                            head=right.get(r);
                            System.out.println(head);
                        }
                        break;
                    }
                }
                else                                                    
                {
                    total_mov+=Math.abs(right.get(0)-head); 
                    head=right.get(0);
                    right.remove(0);
                    System.out.println(head);
                    if(right.isEmpty())                  // this happens when all right requests are served .
                    {
                        for(int l=left.size();l>0;l--)        //serve requests in left direction 
                        {
                            total_mov+=Math.abs(left.get(l-1)-head);   //adding absolute distances to calculate no of head movements
                            head=left.get(l-1);
                            System.out.println(head);
                        }
                        break;
                    }
                }
            }
                    System.out.println("Total number of head movements  :" +total_mov);
                    System.out.println("Total seek time  :" +total_mov*seek_t_cyl+" msec");
        }
    
}

    



