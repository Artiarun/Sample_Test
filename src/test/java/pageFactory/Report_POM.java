/*Upendra */

package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import step_definitions.Hooks;

import java.util.List;

public class Report_POM {

    //Category Listing Report

    @FindBy(xpath = "//*[@class='menudropBoxNav']")
    public static WebElement getQuickLink;

    @FindBy(xpath = "//*[@id='ctl09_lst_Report']/li[1]/a")
    public static WebElement getCategoryListingReport;

    @FindBy(xpath = "//*[@id='ui-id-1']")
    public static WebElement getCategoryListingReportHeading;

    @FindBy(xpath = "//*[@id='ReportCaption1']/table//tr/td/table//tr/td/span")
    public static WebElement getCategoryCodeCaption;

    @FindBy(xpath = "//*[@id='gvSearchResult']//td[1]")
    public List<WebElement> getCategoryCode;


    @FindBy(id = "dd_Export")
    public WebElement getExportIntoDropDown;

    @FindBy(xpath = "//*[@id='dd_Export']/option")
    public List<WebElement> getDropDownOptions;

    @FindBy(id = "btn_Go")
    public WebElement getButtonGo;




public Report_POM(WebDriver driver)
{
    driver = Hooks.driver;
    // This initElements method will create all WebElements
    PageFactory.initElements(driver, this);
}
}
