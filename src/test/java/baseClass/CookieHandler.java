/*Vijay Deshpande
18/03/2020
Wednesday
14.46
EyvoAutomation*/
package baseClass;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import java.io.*;
import java.util.Date;
import java.util.StringTokenizer;

public class CookieHandler extends mainDriver {


    public void getCookieInfoAndWrite(WebDriver driver) {

        // create file named Cookies to store Login Information
        File file = new File("Cookies.data");
        try {
            // Delete old file if exists
            file.delete();
            file.createNewFile();
            FileWriter fileWrite = new FileWriter(file);
            BufferedWriter Bwrite = new BufferedWriter(fileWrite);
            // loop for getting the cookie information

            for (Cookie ck : driver.manage().getCookies()) {
                Bwrite.write((ck.getName() + ";" + ck.getValue() + ";" + ck.getDomain() + ";" + ck.getPath() + ";" + ck.getExpiry()));
                Bwrite.newLine();
            }
            Bwrite.flush();
            Bwrite.close();
            fileWrite.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void readCookie(WebDriver driver) {
        try {
            File file = new File("Cookies.data");
            FileReader fileReader = new FileReader(file);
            BufferedReader Buffreader = new BufferedReader(fileReader);
            String strline;
            while ((strline = Buffreader.readLine()) != null) {
                StringTokenizer token = new StringTokenizer(strline, ";");
                while (token.hasMoreTokens()) {
                    String name = token.nextToken();
                    String value = token.nextToken();
                    String domain = token.nextToken();
                    String path = token.nextToken();
                    Date expiry = null;

                    String val;
                    if (!(val = token.nextToken()).equals("null")) {
                        expiry = new Date(val);
                    }
                    Boolean isSecure = new Boolean(token.nextToken()).booleanValue();
                    Cookie ck = new Cookie(name, value, domain, path, expiry, isSecure);
                    driver.manage().addCookie(ck); // This will add the stored cookie to your current session
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

