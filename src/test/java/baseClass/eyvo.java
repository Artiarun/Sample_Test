package baseClass;

import cucumber.api.java.After;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.http.HttpConnection;
import org.junit.Before;

import java.net.HttpURLConnection;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class eyvo {
    ChromeDriver driver;
























































    @Test
    public void linkBroken() throws IOException, InterruptedException {
        Utils utils = new Utils();
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();

        driver.navigate().to("http://dev.paymate.in/Beta/PMXUAE/");
        driver.findElement(By.name("UserName")).sendKeys("dm.cat@yopmail.com");
        driver.findElement(By.name("Password")).sendKeys("Paymate@123");
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@type='submit']")));


        Thread.sleep(3000L);
        // ArrayList<WebElement> arrayList=new ArrayList<WebElement>();
        List<WebElement> listLinks = driver.findElements(By.tagName("a"));
        System.out.println("Total links are: " + listLinks.size());
        for (WebElement element : listLinks) {

            if (element.getAttribute("href") != null && !element.getAttribute("href").startsWith("javascript")) {
                String linksAre = element.getAttribute("href");

                //System.out.println("Company Name: "+companyName);
                System.out.println("Urls are: \n" + linksAre);
                brokenLinks(linksAre);
            }
        }


    }

    public String brokenLinks(String url) throws IOException {
        //String url=null;
        WebDriverWait wait = new WebDriverWait(driver, 300 /*timeout in seconds*/);
        if(wait.until(ExpectedConditions.alertIsPresent())==null)
            System.out.println("alert was not present");
        else
            System.out.println("alert was present");
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.connect();
        String msg = connection.getResponseMessage();
        Assert.assertEquals(msg, "OK", "Link not working invalid response message " + msg);
        int code = connection.getResponseCode();
       // Assert.assertEquals(code, "200", "Link not working invalid response code " + code);
        System.out.println("Responce msg is: " + msg + "\n Respoance code is: " + code);
        connection.disconnect();
        return url;
    }

}

