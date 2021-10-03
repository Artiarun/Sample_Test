/*Created By vijay deshpande */
/*05-05-2020*/

package helper;

import baseClass.Utils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class Wait extends Utils {

    private int waitTime = 45;
    private int longwaitTime = 90;
    private WebDriverWait webDriverWait = new WebDriverWait(driver, waitTime);
    private WebDriverWait longWebDriverWait = new WebDriverWait(driver, longwaitTime);
    private WebDriverWait webDriverLongWait = new WebDriverWait(driver, longwaitTime);


    public void setWebDriverFluentWait(int poolInterval, int timeOutDuration, By by) {
        FluentWait<WebDriver> wait =
                new FluentWait<WebDriver>(driver)
                        .withTimeout(timeOutDuration, TimeUnit.SECONDS)
                        .pollingEvery(poolInterval, TimeUnit.SECONDS)
                        .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(by)));
    }
    public WebElement waitUntilIsPresentAndClickableAndReturnElement(WebElement element2) {
        checkPageIsReady();

        WebElement elements = null;
        try {

            elements = webDriverWait.until(ExpectedConditions.visibilityOf(element2));
            elements = webDriverWait.until(ExpectedConditions.elementToBeClickable(element2));
            //        Thread.sleep(3000);
        } catch (Exception e) {
            elements = webDriverWait.until(ExpectedConditions.visibilityOf(element2));
            elements = webDriverWait.until(ExpectedConditions.elementToBeClickable(element2));
        }
        return elements;
    }

    public WebElement waitUntilAttributeIsPresent(
            WebElement element2, String attribute, String text) {

        checkPageIsReady();
        WebElement elements = waitUntilIsPresentAndClickableAndReturnElement(element2);
        webDriverWait.until(ExpectedConditions.attributeContains(elements, attribute, text));
        return elements;
    }

    public Boolean longWaitUntilIsPresent(By by) {
        webDriverLongWait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return null;
    }

    public WebElement waitUntilAttributeIsPresent(By element2, String attribute, String text) {

        checkPageIsReady();
        WebElement elements = waitUntilIsPresentAndClickableAndReturnElement(element2);
        webDriverWait.until(ExpectedConditions.attributeContains(elements, attribute, text));
        return elements;
    }

    public String waitUntilWebElementShouldBeDisplayToGetText(WebElement element)
    {
        String string = webDriverLongWait.until(ExpectedConditions.visibilityOf(element)).getText();
       return string;
    }



    public WebElement waitUntilIsPresentAndClickableAndReturnElement(By by) {
        checkPageIsReady();
        WebElement elements = null;
        try {

            elements = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
            elements = webDriverWait.until(ExpectedConditions.elementToBeClickable(elements));
            //        Thread.sleep(3000);
        } catch (Exception e) {
            elements = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
            elements = webDriverWait.until(ExpectedConditions.elementToBeClickable(elements));
        }
        return elements;
    }

    public Boolean waitUntilIsPresentAndClickable(By by) {

        checkPageIsReady();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(by));

        return null;
    }

    public Boolean waitUntilIsPresentAndClickable(WebElement element) {
        checkPageIsReady();
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        //        Thread.sleep(3000);
        return null;
    }

    public void checkPageIsReady() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Initially bellow given if condition will check ready state of page.
        if (js.executeScript("return document.readyState").toString().equals("complete")) {
            System.out.println("Page Is loaded.");
        }

    }

    public void waitAndClick(WebDriver driver, By by) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
        checkPageIsReady();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(by).click();
    }

    public WebElement waitAndClick(WebElement element) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        return element;
    }

    public void waitAndRefresh(WebElement element) {
        WebElement foo =
                webDriverWait.until(
                        new Function<WebDriver, WebElement>() {
                            public WebElement apply(WebDriver driver) {
                                driver.navigate().refresh();
                                return element;
                            }
                        });
    }

    public void waitForanElement(WebElement element) {

        WebElement foo =
                webDriverWait.until(
                        new Function<WebDriver, WebElement>() {
                            public WebElement apply(WebDriver driver) {
                                return element;
                            }
                        });
    }

    public String waitAndGetText(WebDriver driver, By by) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
        String text = driver.findElement(by).getText();
        return text;
    }

    public String waitAndGetValue(WebElement element) {
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        String text = element.getAttribute("value");
        return text;
    }

    public String waitAndGetText(WebElement element) {
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        String text = element.getText();
        return text;
    }

    public Boolean waitUntilIsPresent(By by) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return null;
    }

    public Boolean waitUntilIsPresent(WebElement by) {
        webDriverWait.until(ExpectedConditions.visibilityOf(by));
        return null;
    }

    public WebElement waitUnitlIsPresent(WebElement element) {
        element = webDriverWait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public WebElement longWaitUnitlIsPresent(WebElement element) {
        element = longWebDriverWait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public Boolean longWaitUnitlIsPresent(By by) {
        longWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return null;
    }

    public void frameIsPresentAndSwitch(By by) throws InterruptedException {
        Thread.sleep(3000);
        driver.getWindowHandles();
        webDriverWait.until(
                ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("monitoring-ddiq-frame")));
    }

    public void iFrameAndSwitch(String frameName) throws InterruptedException {
        Thread.sleep(3000);
        driver.getWindowHandles();
        By by = By.id(frameName);
        try {
            webDriverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
            // your actions on iframe here

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void waitUntilNotPresent(By by) {
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public void longwaitUntilNotPresent(By by) {
        longWebDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public Boolean longWaitUntilNotPresent(By by) {
        longWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return null;
    }

    public void waitUntilNotPresent(WebElement element) {
        webDriverWait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void longWaitUntilNotPresent(WebElement element) {
        longWebDriverWait.until(ExpectedConditions.invisibilityOf(element));
    }

//  public void waitUntilGridSpinnersNotPresent() {
//
//    //        System.out.println(driver.findElements(By.xpath("//div[@data-bind='visible:
//    // SubjectLoading']")).size());
//    if (driver.findElements(By.id("gridSpinner")).size() != 0) {
//      if (driver.findElements(By.id("gridSpinner")).size() != 0) {
//        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("gridSpinner")));
//      } else if (driver
//              .findElements(By.cssSelector("div.loading:nth-child(1) > img:nth-child(1)"))
//              .size()
//          != 0) {
//
//        webDriverWait.until(
//            ExpectedConditions.invisibilityOfElementLocated(
//                By.cssSelector("div.loading:nth-child(1) > img:nth-child(1)")));
//        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("load_jqGrid")));
//      } else if (driver.findElements(By.xpath("//div[@data-bind='visible: SubjectLoading']")).size()
//          != 0) {
//        webDriverWait.until(
//            ExpectedConditions.invisibilityOfElementLocated(
//                By.xpath("//div[@data-bind='visible: SubjectLoading']")));
//      } else if (driver.findElements(By.xpath("//*[@id=\"aggregateSpinner\"]")).size() != 0) {
//        System.out.println("over Here");
//        webDriverWait.until(
//            ExpectedConditions.invisibilityOfElementLocated(
//                By.xpath("//*[@id=\"aggregateSpinner\"]")));
//      }
//      // webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"aggregateSpinner\"]")));
//
//    }
//    if (driver.findElements(By.xpath("//*[@id=\"cqmquestionnaire\"]/div[@class='loading']")).size()
//        != 0) {
//      webDriverWait.until(
//          ExpectedConditions.invisibilityOfElementLocated(
//              By.xpath("//*[@id=\"cqmquestionnaire\"]/div[@class='loading']")));
//    }
//  }


    public void waitUntilElementEnabled(WebElement element) {
        //        WebDriverWait wait = new WebDriverWait(driver, 30);

        webDriverWait.until(
                new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {

                        if (element.isEnabled()) {
                            return true;
                        } else return false;
                    }
                });
    }

    public void waitUntilTextVisible(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        wait.until(
                new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        String enabled = element.getText();
                        //                System.out.println(enabled);
                        if (!enabled.isEmpty()) {
                            //                    System.out.println("text ------ " + enabled);
                            return true;
                        } else return false;
                    }
                });
    }


    public void waitUntilSubjectLoadingNotPresent(By by) {
        if (driver.findElements(by).size() != 0) {
            webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        }
    }

    public void waitAndSwitchToNewTab() {

        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
    }

    public void waitAndClickOnElement(WebDriver driver, WebElement element) {
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        element.click();
    }

    public void waitUntilTextIs(WebElement element, String text) {
        webDriverWait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public void waitUntilInputIs(WebElement element, String text) {
        WebElement element1 = waitUntilIsPresentAndClickableAndReturnElement(element);
        System.out.println(element1.getAttribute("value"));
        webDriverWait.until(ExpectedConditions.textToBePresentInElementValue(element1, text));
    }

    public WebElement waitAndReturnElement(WebDriver driver, By by) {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
        WebElement element = driver.findElement(by);
        return element;
    }

    public WebElement longWaitAndReturnElement(WebDriver driver, By by) {
        longWebDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
        WebElement element = driver.findElement(by);
        return element;
    }

    public WebElement waitAndReturnElement(WebElement element) {
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public WebElement longWaitAndReturnElement(WebElement element) {
        longWebDriverWait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public void waitToGetTitle(String title) {
        webDriverWait.until(ExpectedConditions.titleIs(title));
    }

    public void waitToGetTitleContains(String title) {
        webDriverWait.until(ExpectedConditions.titleContains(title));
    }

    public List<WebElement> waitAndReturnListElements(WebDriver driver, By by) {
        webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        List<WebElement> elements = driver.findElements(by);
        return elements;
    }

    public List<WebElement> waitUntilListIsPresent(WebElement elements) {
        List<WebElement> test =
                webDriverWait.until(ExpectedConditions.visibilityOfAllElements(elements));
        return test;
    }

    public List<WebElement> waitUntilListIsPresent(List<WebElement> elements) {
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(elements));
        return elements;
    }

    public List<WebElement> longWaitUntilListIsPresent(List<WebElement> elements) {
        longWebDriverWait.until(ExpectedConditions.visibilityOfAllElements(elements));
        return elements;
    }

    public void waitAndSendKeys(WebDriver driver, By by, String keys) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
        driver.findElement(by).sendKeys(keys);
    }

    public void waitAndSendKeysAndPressEnter(WebDriver driver, By by, String keys) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
        driver.findElement(by).sendKeys(keys + Keys.ENTER);
    }

    public void waitAndSendKeysByElement(WebElement element, String keys) {
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(keys);
    }

    public void waitAndClearTextArea(WebElement element) {
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
    }

    public void waitAndSwitchToNewWindow(WebDriver driver, By by) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
        if (driver.findElement(by).isDisplayed()) {
            driver.switchTo().activeElement();
        }
    }

    public void waitUntilElementListIsPresent(List<WebElement> elements) {
        int count = 0;
        while (elements.isEmpty() && count < 5)
            try {
                webDriverWait.until(ExpectedConditions.visibilityOfAllElements(elements));
            } catch (Exception e) {
                count++;
            }
    }

    public void waitForJQuery(WebDriver driver) {
        final Boolean until =
                webDriverWait.until(
                        new ExpectedCondition<Boolean>() {
                            public Boolean apply(WebDriver d) {
                                JavascriptExecutor js = (JavascriptExecutor) d;
                                return (Boolean)
                                        js.executeScript("return !!window.jQuery && window.jQuery.active == 0");
                            }
                        });
    }
}
