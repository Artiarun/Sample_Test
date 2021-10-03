package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import step_definitions.Hooks;

import java.util.List;

public class Login_POM {

    @FindBy(xpath = "//input[@id='CompanyCode']")
    public static WebElement getCompanyCode;

    @FindBy(xpath = "//input[@id='Passowrd']")
    public static WebElement getPassword;

    @FindBy(xpath = "//input[@id='btngo']")
    public static WebElement getLoginButton;
    public Login_POM() {
        WebDriver driver = Hooks.driver;
        // This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }
}
