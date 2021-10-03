/*Vijay Deshpande */

package helper;

import baseClass.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Dropdown extends Utils {

  public void selectValueFromUnorderedList(By by, WebDriver driver, final String value) {
   Select select=new Select(driver.findElement(by));
   select.selectByVisibleText(value);
  }

  public String selectValueFromInput(WebElement element, final String value) {
    Select select = new Select(element);
    select.selectByVisibleText(value);
    String str_Selected = checkDefaultSelected(element);
    return str_Selected;
  }

  public void selectAllValuesFromUnorderedList(WebElement webElement) {
    List<WebElement> options = webElement.findElements(By.tagName("li"));
    for (WebElement option : options) option.click();
  }

  public void getAllSelectedValuesFromUnorderedList(WebElement webElement) {
    List<WebElement> options = webElement.findElements(By.tagName("li"));
    for (WebElement option : options) option.click();
  }
public void unchecked(WebElement element,String value)
{
    Select select=new Select(element);
    select.deselectByValue(value);
}

  public String checkDefaultSelected(WebElement element) {
    Select select = new Select(element);
    String selectedStr = select.getFirstSelectedOption().getText();
    return selectedStr;
  }
}
