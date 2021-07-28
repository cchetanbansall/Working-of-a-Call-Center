package callcenter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ServiceAgent
    implements Runnable
{
// ------------------------------ FIELDS ------------------------------

    private long callExpiration;

    private SimpleDateFormat formatter;

    private int id;

    private boolean running;

    public ServiceAgentStatus status;
    
    public long totalDuration;
    
    public int totalCalls;
   
// --------------------------- CONSTRUCTORS ---------------------------

    public ServiceAgent( int id )
    {
        this.id = id;
        this.status = ServiceAgentStatus.START;
        formatter = new SimpleDateFormat( "HH:mm:ss" );
        this.totalDuration=0;
        this.totalCalls=0;
    }

// ------------------------ INTERFACE METHODS ------------------------

// --------------------- Interface Runnable ---------------------

    @Override
    public void run()
    {
        while ( running )
        {
            if ( status == ServiceAgentStatus.FREE || status == ServiceAgentStatus.START )
            {
                Call call = CallQueue.retrieveCall();
                if ( call != null )
                {
                    log( "Answering call " + call.getNumber() );
                    totalDuration=totalDuration+( call.getDuration() * 60 * 1000 );
                    totalCalls++;
                    callExpiration = System.currentTimeMillis() + ( call.getDuration() * 60 * 1000 );
                    status = ServiceAgentStatus.IN_A_CALL;
                }
            }
            else
            {
                if ( System.currentTimeMillis() > callExpiration )
                {
                    log( "hanging up" );
                    status = ServiceAgentStatus.FREE;
                }
            }
            sleep();
        }
    }

// -------------------------- OTHER METHODS --------------------------

    public void start()
    {
        running = true;
        new Thread( this ).start();
    }

    public void stop()
    {
        running = false;
    }

    public void log( String s )
    {
        System.out.println( "[" + formatter.format( new Date() ) + "][ServiceAgent][Agent " + id + "] " + s );
    }

    public void sleep()
    {
        try
        {
            Thread.sleep( 5000 );
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        }
    }
}