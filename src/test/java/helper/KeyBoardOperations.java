/*Vijay Deshpande */
/*26-05-2020*/

package helper;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.event.KeyEvent;

public class KeyBoardOperations {




    public void keyPress(String passKey, WebDriver driver) throws AWTException {
        Actions actions=new Actions(driver);

        switch (passKey)
        {
            case"arrowDown":
            actions.sendKeys(Keys.ARROW_DOWN).build().perform();
            break;
            case"enter":
                actions.sendKeys(Keys.ENTER).build().perform();
                break;
            case"esc":
                actions.sendKeys(Keys.ESCAPE);
                break;
        }

    }
}

