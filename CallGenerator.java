package callcenter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.lang.*;

public class CallGenerator
   //  implements Runnable
{
// ------------------------------ FIELDS ------------------------------

    private SimpleDateFormat formatter;

    private Random random;

  //   private boolean running;
//     public long starttime;
    

   

// --------------------------- CONSTRUCTORS ---------------------------

    public CallGenerator()
    {
        random = new Random();
        formatter = new SimpleDateFormat( "HH:mm:ss" );
 
    }
    
// custom

public void recieveCall(int cnt){
   while(cnt>0){
   int duration = random.nextInt( 3 );
    if ( duration > 1)
            {
                log( "Recieving a call... ");cnt--;
                CallQueue.queueCall( duration );
                sleep();
                }
                
}


}    

// ------------------------ INTERFACE METHODS ------------------------

// --------------------- Interface Runnable ---------------------

   //  @Override
//     public void run()
//     {
//         while ( running == true  )
//         {
//             int duration = random.nextInt( 3 );
//             if ( duration > 1)
//             {
//                 log( "Recieving a call... ");
//                 CallQueue.queueCall( duration );
//                 
//                //  System.out.println(threadcount);
//                 sleep();
//             }
//             
//         }
//     }
// 
// // -------------------------- OTHER METHODS --------------------------
// 
//     public void start(long starttime)
//     {
//     long currenttime=System.currentTimeMillis();
// 
//         try{         if(starttime+1000000>=currenttime){
//         // long endtime=starttime+1000;
// //            log(System.currentTimeMillis()+"  "+endtime);
//         running = true;
//         new Thread( this ).start();}
//         else 
//         throw new InterruptedException();}
//         catch ( InterruptedException e )
//       {
//          long endtime=starttime+10000000;
//            log(currenttime+"  "+endtime);
//             log("CCallgenerator ended");        }
// 
//      }
//      
// 
//     public void stop()
//     {
//         running = false;
//     }

    private void log( String s )
    { 
     
        Logs.Logging("[" + formatter.format( new Date() ) + "] " + s );
    }

 private void sleep()
   {
         try
       {
            int sleep = random.nextInt( 30 );
            //  log( "going to sleep " + sleep + " seconds" );
            Thread.sleep( sleep * 100 );
        
        }
         catch ( InterruptedException e )
      {
            log("Callgenerator ended");        }
 }
}
