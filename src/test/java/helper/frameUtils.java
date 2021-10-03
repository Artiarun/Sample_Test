/*Vijay Deshpande */
/*27-05-2020*/

package helper;

import baseClass.Utils;
import baseClass.mainDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class frameUtils extends mainDriver {
    public Wait wait = new Wait();
    public Utils utils = new Utils();

    public void switchToSYsAdminTopFrame(WebDriver driver) {
        wait.waitUntilElelemntShouldBeDisplay(driver, By.id("frmTop"), 50L);
        driver.switchTo().frame(driver.findElement(By.id("frmTop")));
        utils.log4j("System switched in main top frame...");
    }

    public void exitFromFrame(WebDriver driver) {
        driver.switchTo().defaultContent();
        utils.log4j("System exists from frame....");
    }

    public void switchToSysAdminBottom(WebDriver driver) {
        utils.waitUntilElelemntShouldBeDisplay(driver, By.id("frmBottom"), 50L);
        driver.switchTo().frame(driver.findElement(By.id("frmBottom")));
        utils.log4j("System switched to Bottom frame...");
    }

    public void switchToSysAdminLeftFrame(WebDriver driver) {
        utils.waitUntilElelemntShouldBeDisplay(this.driver, By.id("admin_left"), 50L);
        this.driver.switchTo().frame(this.driver.findElement(By.id("admin_left")));
        utils.log4j("System switched to admin left frame...");
    }

    public void switchToAdminRightFrame(WebDriver driver) {
        utils.waitUntilElelemntShouldBeDisplay(driver, By.xpath("//frame[@id='admin_right']"), 50L);
        driver.switchTo().frame(driver.findElement(By.xpath("//frame[@id='admin_right']")));
        utils.log4j("System switched to admin right frame...");
    }

    public void switchToCategoryPopUpFrame(WebDriver driver) {
        utils.waitUntilElelemntShouldBeDisplay(driver, By.xpath("//iframe[@id='iFrameDiv_DataFileCategory']"), 50L);
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='iFrameDiv_DataFileCategory']")));
        utils.log4j("System switched to category popUp frame... ");
    }

    public void switchToCategoryPrimaryTreeFrame(WebDriver driver) {
        utils.waitUntilElelemntShouldBeDisplay(driver, By.xpath("//iframe[@id='iFrameDiv_CommonSelectCategoryTree']"), 50L);
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='iFrameDiv_CommonSelectCategoryTree']")));
        utils.log4j("System switched to Category Primary Tree Frame...");

    }

    public void switchToSelectSupplierPopUp(WebDriver driver)
    {
        utils.waitUntilElelemntShouldBeDisplay(driver, By.xpath("//iframe[@id='iFrameDiv_CommonMultiSelectSupplier']"), 50L);

        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='iFrameDiv_CommonMultiSelectSupplier']")));

        utils.log4j("System switched to Select Supplier pop-up Frame...");
    }
}
