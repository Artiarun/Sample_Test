/*Vijay Deshpande */

package helper;

import baseClass.Utils;
import baseClass.mainDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import pageFactory.Report_POM;

import java.io.IOException;
import java.util.*;

public class Report extends mainDriver {
  public Utils utils = new Utils();
  Log log=new Log();
  Report_POM reportPom = new Report_POM(driver);
  public static ArrayList<String> arrayList1;
  public static ArrayList<String>arrayList2;
  public void categoryListingReport() throws IOException, InterruptedException {

    driver.switchTo().defaultContent();
    driver.switchTo().frame(driver.findElement(By.id("IframeMainContent")));
    utils.waitUntilElelemntShouldBeDisplay(driver,By.xpath("//*[@id='gvSearchResult']//td[1]"),50L);
    utils.waitUntilElelemntShouldBeDisplay(driver, By.id("lbl_Group"), 50L);


    Actions actions = new Actions(driver);
    String text = driver.findElement(By.id("lbl_Group")).getText();
    System.out.println(text);
    actions.moveToElement(reportPom.getQuickLink).build().perform();

    utils.waitUntilElelemntShouldBeDisplay(
        driver, By.xpath("//*[@id='ctl09_lst_Report']/li[1]/a"), 50L);
    utils.click(reportPom.getCategoryListingReport, driver);
    utils.log4j("I click on Category Listing report link");
    driver.switchTo().defaultContent();
    utils.waitUntilElelemntShouldBeDisplay(driver, By.xpath("//*[@id='ui-id-1']"), 50L);
    text = reportPom.getCategoryListingReportHeading.getText();
    Assert.assertTrue(
        "Category Listing report heading is not matched", text.equals("Category Listing Report"));
    utils.log4j("Category Listing report heading is verified successfully...");
    driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='iFrameDiv_Report']")));
    // driver.switchTo().defaultContent();
    utils.waitUntilElelemntShouldBeDisplay(driver, By.xpath("//*[contains(@id,'_iframe')]"), 50L);
    driver.switchTo().frame(driver.findElement(By.xpath("//*[contains(@id,'_iframe')]")));


    List<WebElement> list = driver.findElements(By.xpath("//*[contains(@id,'Text')]/div/div/span"));

    for (WebElement element : list) {
      element.getText();
    }

    utils.waitUntilElelemntShouldBeDisplay(
        driver, By.xpath("//*[@id='CategoryCode1']/table//tr/td/table//tr/td"), 200L);
    arrayList1 = new ArrayList<String>();
    List<WebElement> report =
        driver.findElements(By.xpath("//*[@id='CategoryCode1']/table//tr/td/table//tr/td"));
    for (WebElement element : report) {

      text=element.getText();
      System.out.println(text);
      arrayList1.add(text);
    }
    System.out.println("Array size: " + arrayList1.size());
driver.switchTo().defaultContent();
driver.switchTo().defaultContent();
driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='iFrameDiv_Report']")));
    utils.workOnReportDropDown(driver);


  }
}
