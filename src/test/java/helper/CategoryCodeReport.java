/*Upendra */

package helper;

import baseClass.Utils;
import baseClass.mainDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import pageFactory.Report_POM;

import java.util.List;

public class CategoryCodeReport extends mainDriver {
    Report_POM report_pom=new Report_POM(driver);
    Utils utils=new Utils();


    public void categoryListingReport()
    {
        utils.waitUntilElelemntShouldBeDisplay(driver, By.xpath("//i[@class='fa fa-data-file']"), 500L);
        Actions actions = new Actions(driver);
        actions
                .moveToElement(driver.findElement(By.xpath("//i[@class='fa fa-data-file']")))
                .build()
                .perform();
        driver.findElement(By.id("uc_Menu_link_10")).click();
        driver.switchTo().frame(driver.findElement(By.id("IframeMainContent")));
        utils.waitUntilElelemntShouldBeDisplay(driver,By.id("lbl_Group"),500L);
        String text = driver.findElement(By.id("lbl_Group")).getText();
        System.out.println(text);
        actions.moveToElement(driver.findElement(By.xpath("//*[@class='menudropBoxNav']"))).build().perform();

        List<WebElement> listReport = driver.findElements(By.xpath("//*[contains(@id,'ctl')]/li"));
        for (WebElement webElement :listReport ) {
            String txt = webElement.getText();
            if(txt.equals("Category Listing Report"))
            {
                webElement.click();
                break;
            }

        }
    }
}
