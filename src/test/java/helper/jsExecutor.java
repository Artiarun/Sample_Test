package helper;

import baseClass.mainDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class jsExecutor extends mainDriver {

     JavascriptExecutor jse = (JavascriptExecutor) driver;

    public  void click(WebElement element, WebDriver driver) {

        jse.executeScript("arguments[0].click();", element);
    }

    public void typeText(By by, String value) {
        jse.executeScript("document.getElementById().value = '';");
    }

    public void waitElementShouldBeClickable(WebElement element) {

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

}
