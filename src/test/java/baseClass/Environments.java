/*Vijay Deshpande
18/03/2020
Wednesday
14.46
EyvoAutomation*/
package baseClass;
import helper.Colors;
import java.util.logging.Level;


public class Environments extends mainDriver {

  public String env;
  public Environments() {

    java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
    System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");

    env = System.getProperty("env");

    if (env == null) {
      driver.navigate().to("http://localhost:8007/");
    }
    else {
      driver.navigate().to(env);
      System.out.println(Colors.ANSI_BLUE_BACKGROUND + "The test is running on " + env.toUpperCase() + " environment");
    }
  }
}
