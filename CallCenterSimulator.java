package callcenter;

import java.io.*;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.Date;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class CallCenterSimulator
{
// --------------------------- main() method ---------------------------

    public static void main( String... args ) throws Exception
    { 
        int flag=0;
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String statsFileName= ("D:\\java project\\Call center stats_".concat(timeStamp)).concat(".txt");
        try {
            BufferedWriter out = new BufferedWriter(
                          new FileWriter(statsFileName));
            out.write("################CALL CENTER STATS################\n");
            out.close();
        }
        catch (IOException e) {
            System.out.println("Exception Occurred" + e);
        }
        
        Scanner s = new Scanner(System.in);
        Logs.generateLog();
        Logs.Logging("########Welcome to the Call Center########");
        System.out.println("Please Enter the number of agents in attendence today");
        int numberOfAgents = s.nextInt();
        System.out.println("Please Enter the maximum number of Calls: ");
        int numberOfCalls = s.nextInt();
        Logs.Logging("Number of Service Agents in attendence today: " +numberOfAgents);
        Logs.Logging("Max Number of calls to be taken by Service Agents in attendence today: "+ numberOfCalls);
       ServiceAgent Agent[]=new ServiceAgent[numberOfAgents]; //array of objects(agents)
       if(numberOfAgents>numberOfCalls)
         numberOfAgents=numberOfCalls;
         
         
        for ( int i = 0; i < numberOfAgents; i++ )
        {
           
            Agent[i]=new ServiceAgent( i + 1);
            Agent[i].start();
           
        }
            
           
          CallGenerator obj= new CallGenerator();
         
          obj.recieveCall(numberOfCalls);
          
          int temp=numberOfCalls;
          int i=0;
          while(true)
           {
           Call x=CallQueue.queue.peek();
          
            if(i<numberOfAgents){
            if (Agent[i].status==ServiceAgentStatus.FREE && x==null && i<numberOfAgents)
              { 
              try {
  
            // Open given file in append mode.
            BufferedWriter out = new BufferedWriter(
                   new FileWriter(statsFileName, true));
            out.write("[Agent "+(i+1)+"]\n");
            out.write("Total Duration: "+(Agent[i].totalDuration/60000) +" mins\n");
            out.write("Total Number of Calls: "+ Agent[i].totalCalls+"\n");
            out.close();
        }
        catch (IOException e) {
            System.out.println("exception occoured" + e);
        }
              
              Agent[i].stop();
              
              i++;temp--;
              }}
              if(temp<=0 && i >=numberOfAgents)
               break;
               
              
         }
        
            
         

            
            
    }
}
