package step_definitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import helper.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeClass;

import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class Hooks {
    public static WebDriver driver;
    public static String downloadfolderPath = System.getProperty("user.dir") + "\\DownloadData\\ExportData";
    public static String uploadfolderPath = System.getProperty("user.dir") + "\\testData";
    private static String driverDirectory = System.getProperty("user.dir") + "/webDrivers/usr/bin";
    private ChromeOptions chromeOptions = new ChromeOptions();
    String os = System.getProperty("os.name").toLowerCase();
    public String browser = System.getProperty("browser".toUpperCase());

    @BeforeClass
    public void openBrowser() {
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
        String os = System.getProperty("os.name").toLowerCase();


        if (browser == null) {
            browser = System.getenv("browser");
            if (os.contains("win")) {
                browser = "chrome";
            }
        }
        switch (browser.toLowerCase()) {
            case "chrome":
                HashMap<String, Object> chromePrefs = new HashMap<String, Object>();

                ChromeOptions options = new ChromeOptions();
                options.setPageLoadStrategy(PageLoadStrategy.NONE);
                chromeOptions.setExperimentalOption("prefs", chromePrefs);
                options.addArguments("start-maximized");
                options.addArguments("enable-automation");
                options.addArguments("dns-prefetch-disable");
                options.addArguments("no-sandbox");
                options.addArguments("disable-infobars");
                options.addArguments("disable-dev-shm-usage");
                options.addArguments("disable-browser-side-navigation");
                options.addArguments("disable-gpu");
                chromePrefs.put("profile.default_content_settings.popups", 0);
                chromePrefs.put("safebrowsing.enabled", "true"); // this is the needed configuration
                // chromePrefs.put("profile.default_content_settings.popups", 0);
                chromePrefs.put("download.default_directory", downloadfolderPath);
                if (os.contains("win")) {
                    System.setProperty("webdriver.chrome.driver", driverDirectory + "\\chrome\\chromedriver.exe");
                    driver = new ChromeDriver(chromeOptions);
                    driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
                    driver.manage().deleteAllCookies();
                    driver.manage().window().maximize();
                } else {

                    System.setProperty("webdriver.chrome.driver", driverDirectory + "/chrome/chromedriver.exe");
                    chromeOptions.addArguments("--start-maximized");
                    driver = new ChromeDriver(chromeOptions);
                    driver.manage().deleteAllCookies();
                }
                break;

            case "chromeWindows":
                System.setProperty("webdriver.chrome.driver", driverDirectory + "/chrome/chromedriver.exe");
                chromeOptions.addArguments("--start-maximized");
                driver = new ChromeDriver(chromeOptions);
                driver.manage().deleteAllCookies();
                break;

            case "firefox":
                if (os.contains("win")) {
                    System.setProperty("webdriver.gecko.driver", driverDirectory + "/geckoFirefox/geckodriver.exe");

                    FirefoxProfile profile = new FirefoxProfile();

                    //Set Location to store files after downloading.
                    profile.setPreference("browser.download.dir", downloadfolderPath);
                    profile.setPreference("browser.download.folderList", 2);
                    profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
                    profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/csv, text/csv, text/plain,application/octet-stream doc xls pdf txt");
                    profile.setPreference("browser.download.manager.focusWhenStarting", false);
                    profile.setPreference("browser.download.useDownloadDir", true);
                    profile.setPreference("browser.helperApps.alwaysAsk.force", false);
                    profile.setPreference("browser.download.manager.closeWhenDone", true);
                    profile.setPreference("browser.download.manager.showAlertOnComplete", false);
                    profile.setPreference("browser.download.manager.useWindow", false);
                    profile.setPreference("services.sync.prefs.sync.browser.download.manager.showWhenStarting", false);
                    profile.setPreference("pdfjs.disabled", true);
                    profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/csv/xls/xlsx");
                    profile.setPreference("browser.helperApps.neverAsk.openFile",
                            "text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
                    profile.setPreference("browser.helperApps.alwaysAsk.force", false);
                    profile.setPreference("browser.download.manager.showWhenStarting", false);
                    profile.setPreference("pdfjs.disabled", true);
                    //profile.setPreference("browser.helperApps.neverAsk.saveToDisk","text/csv");

                    FirefoxOptions option = new FirefoxOptions();
                    option.setProfile(profile);
                    driver = new FirefoxDriver(option);
                    driver.manage().window().maximize();

                } else {


                    System.setProperty("webdriver.gecko.driver", driverDirectory + "\\geckoFirefox\\geckodriver.exe");
                    driver = new FirefoxDriver();
                    driver.manage().deleteAllCookies();
                    driver.manage().window().maximize();
                }
                break;

            case "ie":
                System.setProperty("webdriver.ie.driver", driverDirectory + "/IEDriver/IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                driver.manage().deleteAllCookies();
                driver.manage().window().maximize();
                break;

            case "edge":

                System.setProperty("webdriver.edge.driver", driverDirectory + "\\edge\\msedgedriver.exe");
                driver = new EdgeDriver();
                driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
                driver.manage().deleteAllCookies();
                driver.manage().window().maximize();


                break;

        /* case "headless":

        driver = new HtmlUnitDriver();
        ((HtmlUnitDriver) driver).setJavascriptEnabled(true);*/

        }
        Log.startLog("Hooks");
        System.out.println("The Browser used for this test is: " + browser.toUpperCase() + "\nThe  Operating System used for this test is: " + os);
    }

    @After
    public void embedScreenshot(Scenario scenario) throws Exception {
        System.out.println(scenario.getStatus() + "<----" + " " + scenario.getName() + "  " + Collections.singletonList(scenario.getSourceTagNames()).toString());
        if (scenario.getStatus().equals("pass")) {
            System.out.println("<----------------------------Test is passed------------------------->");
        }
        if (scenario.isFailed()) {
            try {
                System.out.println("" + scenario);
                scenario.write("Current Page URL is " + new URL(driver.getCurrentUrl()));
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                System.err.println(somePlatformsDontSupportScreenshots.getMessage());
            }
        }
        if (driver != null) {
            Log.endLog("<---------------------------Test is ending------------------------------->");
            driver.quit();
        }

    }
}
