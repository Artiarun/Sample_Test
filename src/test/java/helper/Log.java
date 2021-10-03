/*vijay Deshpande */

package helper;

import org.apache.log4j.Logger;

public class Log{
  //Initialize Log4j instance
  private static Logger Log = Logger.getLogger(Log.class.getName());

  //We can use it when starting tests
  public static void startLog (String testClassName){
    Log.info("<-------------------------------------Test is Starting...--------------------------------------------->");
  }

  //We can use it when ending tests
  public static void endLog (String testClassName){
    Log.info("<-------------------------------------Test is Ending...----------------------------------------------->");
  }

  //Info Level Logs
  public static void info (String message) {
    Log.info(message);
  }


}
