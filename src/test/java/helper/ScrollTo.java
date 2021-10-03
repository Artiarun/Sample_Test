/*Vijay Deshpande */

package helper;

import baseClass.Utils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static step_definitions.Hooks.driver;

public class ScrollTo extends Utils {


    Actions actions = new Actions(driver);
    private JavascriptExecutor jse = (JavascriptExecutor) driver;

    public void scrollToView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollUp() {
        jse.executeScript("scroll(0, -250);");
    }

    public void scrollDown() {
        jse.executeScript("scroll(0, 500);");
    }

    public void scrollToMiddle() {
        jse.executeScript("scroll(0, 200);");
    }

    public void scrollRight() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(2000,0)");
    }

    public void scrollToViewAndClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    public void scrollToViewAndClickFalse(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
        element.click();
    }

    public void scrollDownExtreme() {
        jse.executeScript("scroll(0, 1000);");
    }

    public void scrollUpExtreme() {
        jse.executeScript("scroll(0, -500);");
    }

}
