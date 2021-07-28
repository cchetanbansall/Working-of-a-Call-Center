package callcenter;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.Date;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class Logs{

public static Logger logger = Logger.getLogger("CallCenterLogs"); 

public static void generateLog()
{
 
      FileHandler fh; 
            try {  

        // This block configure the logger with handler and formatter 
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()); 
        String path="D:\\javaproject\\Call center stats\\Log_".concat(timeStamp);
        fh = new FileHandler(path);  
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();  
        fh.setFormatter(formatter);  

    }  
    catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
      
}

public static void Logging(String s)
{
 
            logger.info(s); 

  }  
}
       