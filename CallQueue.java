package callcenter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;

public class CallQueue
{
// ------------------------------ FIELDS ------------------------------

    private static CallQueue instance;

    public static int counter;

    private SimpleDateFormat formatter;

    public static LinkedBlockingQueue<Call> queue;

// -------------------------- STATIC METHODS --------------------------

    public static void queueCall( int duration )
    {
        try
        {
            Call call = new Call( getInstance().counter++, duration );
            log( "Adding call " + call.getNumber() + " to the queue...");
            getInstance().queue.put( call );
        }
        catch ( InterruptedException e )
        {
            log( "There was an error queueing the call" );
        }
    }

    public static Call retrieveCall()
    {
        Call call = getInstance().queue.poll();
        if ( call != null )
        {
            log( "Retrieving call " + call.getNumber() );
        }
        return call;
    }

    private static CallQueue getInstance()
    {
        if ( instance == null )
        {
            instance = new CallQueue();
        }
        return instance;
    }

    private static void log( String s )
    {
        Logs.Logging( "[" + getInstance().formatter.format( new Date() ) + "][CallQueue] " + s );
    }

// --------------------------- CONSTRUCTORS ---------------------------

    private CallQueue()
    {
        this.queue = new LinkedBlockingQueue<Call>();
        this.counter = 1;
        this.formatter = new SimpleDateFormat( "HH:mm:ss" );
    }
}
