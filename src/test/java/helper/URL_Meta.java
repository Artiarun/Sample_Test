/*Vijay Deshpande */

package helper;

import baseClass.Utils;
import org.openqa.selenium.WebDriver;

public class URL_Meta extends Utils {
    public static String getTitle(WebDriver driver) throws Throwable {

        String Title = driver.getTitle();
        System.out.println(Title);
        return Title;
    }

    public static String getURL(WebDriver driver) throws Throwable {

        String URL = driver.getCurrentUrl();
        System.out.println(URL);
        return URL;
    }

}
