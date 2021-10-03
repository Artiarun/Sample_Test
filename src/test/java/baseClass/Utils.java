/*Vijay Deshpande
20/03/2020
Wednesday
14.46
EyvoAutomation*/
package baseClass;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import dataBase.DbHelper;
import helper.Log;
import org.apache.log4j.PropertyConfigurator;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Logger;

public class Utils extends mainDriver {
    public static String fileName = "";
    public WebDriverWait webDriverWait;
    public JavascriptExecutor jse = (JavascriptExecutor) driver;
    public String text;

    public String alerttext;
    public int i;
    public List<WebElement> links;
    public int j;
    public File file;
    public Log logs;
    public FileInputStream inputStream;
    public HSSFSheet sheet;
    public Row row;
    public Workbook workbook;
    public String txt;
    public List<WebElement> listCompanyName;
    public String str;
    public String linksText;
    public Properties obj = new Properties();
    public FileInputStream ojFile;
    public String date;
    public String strCurrentValue;
    public String str_sub;
    public static String saveFilePath;

    public String fakerData(String type, String value) {
        Faker faker = new Faker();
        FakeValuesService fakeValuesService;
        switch (type) {
            case "email":
                fakeValuesService = new FakeValuesService(new Locale("en-GB"), new RandomService());
                value = fakeValuesService.bothify(value + "##@mailinator.com");
                // System.out.println(value);
                break;
            case "address":
                String streetName = faker.address().streetName();
                String number = faker.address().buildingNumber();
                String city = faker.address().city();
                String country = faker.address().country();

                value = String.format("%s\n%s\n%s\n%s", number, streetName, city, country);
                break;
            case "firstName":
                value = faker.name().firstName();
                break;
            case "lastName":
                value = faker.name().lastName();
                break;
            case "amount":
                fakeValuesService = new FakeValuesService(new Locale("en-GB"), new RandomService());
                value = fakeValuesService.bothify("1#00000");
                break;
            case "companyName":
            case "cCode":
                value = faker.company().name();
                break;

            case "desc":
                value = faker.company().industry();

                break;
            case "singleNum":
                fakeValuesService = new FakeValuesService(new Locale("en-GB"), new RandomService());
                value = fakeValuesService.bothify("##");
                break;
        }
        return value;
    }

    public void waitUntilAlertIsPresent() {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 1000);
        webDriverWait.until(ExpectedConditions.alertIsPresent());
    }

    public void loader() {
        //span[@id='loading_msg']
        waitUntilElelemntShouldBeDisplay(driver, By.id("modalprogress"), 10L);
        Assert.assertTrue("Modal progress is not displayed..", driver.findElement(By.xpath("  //div[@id='modalprogress']")).isDisplayed());

        Assert.assertTrue("Loading image is not displayed...", driver.findElement(By.xpath("//div[@class='loading-img']")).isDisplayed());

        isImageBroken(driver.findElement(By.xpath("//div[@class='loading-img']")));
    }

    public void duplicateString(String enterString, String enterMsg) {
        ArrayList<String> array = new ArrayList<>();
        array.add(enterString);

        for (int i = 0; i < array.size(); i++) {
            String array1 = array.get(i);
            for (String array2 : array) {
                if (array1.equals(array2)) {
                    System.out.println(enterMsg);
                }
            }
        }
    }

    public double calculateGST(double GST, double amt) {
        return (GST * amt) / 100;
    }

    public void handelAlert(String alertEvent) {

        switch (alertEvent) {
            case "gettext": {
                alerttext = driver.switchTo().alert().getText();
                System.out.println("This Is Alert Text: " + alerttext);
                break;
            }
            case "accept": {
                driver.switchTo().alert().accept();
                System.out.println("Popup Accepted Successfully: ");
            }
            case "cancel": {
                driver.switchTo().alert().dismiss();
                System.out.println("Popup Closed Successfully");
            }

        }
    }

    public void isImageBroken(WebElement image) {
        if (image.getAttribute("naturalWidth").equals("0")) {
            System.out.println(image.getAttribute("outerHTML") + " is broken.");
            Assert.fail();
        }
    }

    public void switchToFrame() {
        driver.switchTo().frame(driver.findElement(By.id("IframeMainContent")));
    }

    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } // try
        catch (NoAlertPresentException Ex) {
            return false;
        }
    }

    public void switchToAdminFrame() {
        driver.switchTo().frame(driver.findElement(By.id("admin_left")));
    }

    public void waitForFrame(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
    }

    public void handelWindows() {

        String parent = driver.getWindowHandle();
        Set<String> s1 = driver.getWindowHandles();

        for (String child_window : s1) {
            if (!parent.equals(child_window)) {
                driver.switchTo().window(child_window);
                System.out.println(driver.switchTo().window(child_window).getTitle());
                System.out.println(driver.getCurrentUrl());
            }
        }
    }


    public void click(WebElement element, WebDriver driver) {

        jse.executeScript("arguments[0].click();", element);
    }

    public void typeText(By by, String value) {
        jse.executeScript("document.getElementById().value = '';");
    }

    public void waitElementShouldBeClickable(WebElement element) {

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean isElementPresent(By by, WebDriver driver) {
        try {
            driver.findElement(by);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }


    public void logout() {
        click(driver.findElement(By.xpath("//*[@class='icon-login']")), driver);
        click(driver.findElement(By.id("btn_LogOut")), driver);
        boolean flag = isElementPresent(By.xpath("//*[@class='Company-Code']"), driver);
        if (flag) {
            System.out.println("LogOut successfully...");
        } else {
            driver.navigate().to(System.getProperty("env"));
        }

    }
public Boolean isCheckBoxSelected()
{
    boolean isSelected = driver.findElement(By.cssSelector("input:checked[type='checkbox']")).isSelected();
    Assert.assertTrue("Check box is not selected....",isSelected);
    log4j("Check box is selected...");
    return true;
}

public Boolean isCheckBoxIsNotSelected()
{
    boolean isNotSelected = driver.findElement(By.cssSelector("input:not(:checked)[type='checkbox']")).isSelected();
    Assert.assertFalse(isNotSelected);
    return  false;
}

    public void sysLogOut() throws SQLException {
        move(driver.findElement(By.xpath("//*[@id='lbl_LoggedIn_Name']")), driver);
        //driver.findElement(By.cssSelector(".fa-angle-down")).click();
        driver.findElement(By.id("btn_LogOut")).click();
        if (isElementPresent(By.xpath("//input[@id='txtCompanyName']"), driver)) {
            DbHelper.setCon();
            String env = System.getProperty("env");
            ResultSet res = DbHelper.query("select ClientAccessCode from DatabaseAccessControl where WebAddress='" + env + "'");
            res.next();

            driver.findElement(By.xpath("//input[@id='txtCompanyName']")).sendKeys(res.getString(1));
            driver.findElement(By.id("btn_CompanyLogin")).click();
            DbHelper.tearDown();
        } else {
            System.out.println("No need to enter Company ID");
        }

    }

    public void ScrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public String waitAndGetText(WebDriver driver, By by) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
        String text = driver.findElement(by).getText();
        System.out.println(text);
        return text;
    }

    public void waitUntilElelemntShouldBeDisplay(WebDriver driver, By by, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }


    public void workOnReportDropDown(WebDriver driver) throws IOException, InterruptedException {
        Select select = new Select(driver.findElement(By.id("dd_Export")));
        List<WebElement> dropDownValues = driver.findElements(By.xpath("//*[@id='dd_Export']/option"));
        for (int i = 0; i < dropDownValues.size(); i++) {
            String options = dropDownValues.get(i).getText();

            switch (options) {
                case "Export to PDF":
                    select.selectByVisibleText(options);
                    driver.findElement(By.id("btn_Go")).click();
                    workOnViewExportFile(driver);

                    break;
                case "Export to Excel":
                    select.selectByVisibleText(options);
                    driver.findElement(By.id("btn_Go")).click();
                    workOnViewExportFile(driver);
                    break;
                case "Export to Word":
                    select.selectByVisibleText(options);
                    driver.findElement(By.id("btn_Go")).click();
                    workOnViewExportFile(driver);
                    break;
                case "Export to HTML":
                    select.selectByVisibleText(options);
                    driver.findElement(By.id("btn_Go")).click();
                    workOnViewExportFile(driver);
                    break;
                case "Export to RTF":
                    select.selectByVisibleText(options);
                    driver.findElement(By.id("btn_Go")).click();
                    workOnViewExportFile(driver);
                    break;
            }
        }
    }

    public void workOnViewExportFile(WebDriver driver) throws IOException, InterruptedException {
        driver.switchTo().defaultContent();

        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='iFrameDiv_Report']")));
        waitUntilElelemntShouldBeDisplay(driver, By.id("PopUpTop1_lblPopUpHeader"), 50L);
        String headerText = driver.findElement(By.id("PopUpTop1_lblPopUpHeader")).getText();
        Assert.assertTrue("File export header is not matched", headerText.equals("View Export File"));
        log4j("heading is matched");
        String successfullyMsg = driver.findElement(By.id("lbl_ExportSuccessfully")).getText();
        Assert.assertTrue(
                "Successfully message is not matched",
                successfullyMsg.equals("File exported successfully ..."));
        log4j("Successfully message is matched...");
        click(driver.findElement(By.id("lnk_Click")), driver);
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // driver.switchTo().defaultContent();
        // driver.switchTo().defaultContent();

        handelWindows();

        String title = driver.getTitle();
        System.out.println(title);

        String url = driver.getCurrentUrl();
        // readPDF(url);
        downloadFile(url);

        Thread.sleep(50000L);
        // readPDF(url);
        // System.out.println("+++++++++++++++++++++++" + pdfData);
    }

    public static void downloadFile(String fileURL) throws IOException {
        String saveDir = "I:\\EyvoAutomation\\DownloadData\\";
        final int BUFFER_SIZE = 4096;
        URL url = new URL(fileURL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            String disposition = httpConn.getHeaderField("Content-Disposition");
            String contentType = httpConn.getContentType();
            int contentLength = httpConn.getContentLength();

            if (disposition != null) {
                // extracts file name from header field
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    fileName = disposition.substring(index + 10, disposition.length() - 1);
                }
            } else {
                // extracts file name from URL
                fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1, fileURL.length());
            }

            System.out.println("Content-Type = " + contentType);
            System.out.println("Content-Disposition = " + disposition);
            System.out.println("Content-Length = " + contentLength);
            System.out.println("fileName = " + fileName);

            // opens input stream from the HTTP connection
            InputStream inputStream = httpConn.getInputStream();

            saveFilePath = saveDir + File.separator + fileName;

            // opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);

            int bytesRead = -1;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.close();
            inputStream.close();

            System.out.println("File downloaded");
            if (contentType.contains("pdf")) {
                readPDF(saveFilePath);
            } else {
                System.out.println("File type is other");
            }
        } else {
            System.out.println("No file to download. Server replied HTTP code: " + responseCode);
        }
        httpConn.disconnect();
    }

    public static void readPDF(String url) {
        String pfdText = null;
        // Loading an existing document
        File file = new File(url);
        PDDocument document = null;
        try {
            document = PDDocument.load(file);

            // Instantiate PDFTextStripper class
            PDFTextStripper pdfStripper = null;

            pdfStripper = new PDFTextStripper();
            // Retrieving text from PDF document
            pfdText = pdfStripper.getText(document);
            System.out.println("--------------------------" + pfdText);
            // Closing the document
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void retryingToFindElement(WebElement element, String event) {
        boolean result = false;
        int attemptCount = 0;
        switch (event) {
            case "getText":
                while (attemptCount < 4) {
                    try {

                        str_sub = element.getText();
                        log4j("Retrying to find element");
                        // System.out.println("Line number 244");
                        result = true;
                        break;

                    } catch (org.openqa.selenium.StaleElementReferenceException ignored) {

                    }
                    attemptCount++;
                }
                break;
            case "click":
                while (attemptCount < 4) {
                    try {

                        click(element, driver);
                        log4j("Retrying to find element");
                        result = true;
                        break;

                    } catch (org.openqa.selenium.StaleElementReferenceException ignored) {

                    }
                    attemptCount++;
                }
                break;
        }
    }


    public String retryingToText(WebElement element) {
        String Str_IS = null;
        boolean result = false;
        int attemptCount = 0;

        while (attemptCount < 4) {
            try {

                Str_IS = element.getText();
                log4j("Retrying to find element");
                // System.out.println("Line number 244");
                result = true;
                break;

            } catch (org.openqa.selenium.StaleElementReferenceException ignored) {

            }
            attemptCount++;
        }


        return Str_IS;
    }


    public void toFocousOnElement(WebDriver driver, WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element);
        action.click();
        action.build().perform();
    }

    public void focusOnTab() {
        // ((JavascriptExecutor) driver).executeScript("window.focus();");
        ((JavascriptExecutor) driver).executeScript("window.focus()");
    }

    // 20-04-2020
    public static void connectToDataBase(
            String url, String userName, String password, String passQuery) {
        try {
            // Load mysql jdbc driver
            Class.forName("com.mysql.jdbc.Driver");
            // Create Connection to DB
            Connection con = DriverManager.getConnection(url, userName, password);
            // Create Statement Object
            Statement stmt = con.createStatement();

            // Execute the SQL Query. Store results in ResultSet
            ResultSet rs = stmt.executeQuery(passQuery);

            // While Loop to iterate through all data and print results
            while (rs.next()) {
                String myName = rs.getString(1);
                String myAge = rs.getString(2);
                System.out.println(myName + "  " + myAge);
            }
            // closing DB Connection
            con.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void switchToTab() throws InterruptedException {
        String parent = driver.getWindowHandle();
        // This will return the number of windows opened by Webdriver and will return Set of Strings
        Set<String> s1 = driver.getWindowHandles();
        // Now we will iterate using Iterator
        for (String child_window : s1) {
            // Here we will compare if parent window is not equal to child window then we            will
            // close

            if (!parent.equals(child_window)) {
                driver.switchTo().window(parent);
                System.out.println(driver.switchTo().window(parent).getTitle());
                Thread.sleep(3000);
            }
        }
    }


    public void popUpWindow() {
        String mainWinHander = driver.getWindowHandle();

        // code for clicking button to open new window is ommited

        // Now the window opened. So here reture the handle with size = 2
        Set<String> handles = driver.getWindowHandles();

        for (String handle : handles) {
            if (!mainWinHander.equals(handle)) {
                // Here will block for ever. No exception and timeout!
                WebDriver popup = driver.switchTo().window(handle);
                // do something with popup
            }
        }
    }

    public void SwitchToFrame(By by) {
        driver.switchTo().frame(driver.findElement(by));
    }

    public void log4j(String msg) {
        Logger log = Logger.getLogger(String.valueOf(Utils.class.getClasses()));
        PropertyConfigurator.configure("Log4j.properties");

        // logs.startLog("--------------------------------------------------------------------------------");
        logs.info(msg);
    }

    public void setWebDriverWait(int timeToWait, By element) {
        WebDriverWait wait;

        wait = new WebDriverWait(driver, timeToWait);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        try {
            if ((wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isDisplayed()))
                System.out.println("Element is displayed within " + timeToWait + " sec");
        } catch (StaleElementReferenceException e) {
            String exe = e.getMessage();
            System.out.println("This is exception message: " + exe);
            timeToWait++;
            System.out.println("Time elapsed " + timeToWait + " sec extra");
        }
    }

    public File[] finder(String dirName, String extension) {
        File dir = new File(dirName);
        return dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String filename) {
                return filename.endsWith(extension);
            }
        });
    }

    /* Get the latest file*/
    public File getLatestFileFromDir(String dirPath) {
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return null;
        }

        File lastModifiedFile = files[0];
        for (int i = 1; i < files.length; i++) {
            if (lastModifiedFile.lastModified() < files[i].lastModified()) {
                lastModifiedFile = files[i].getAbsoluteFile();
            }
        }
        return lastModifiedFile;
    }

    /* Check the file from a directory with extension */
    public boolean isFileDownloaded_Ext(String dirPath, String ext) {
        boolean bool = false;
        File dir = new File(dirPath);
        System.out.println("Line number 602: " + dir);
        File[] files = dir.listFiles();
        // System.out.println("Line number 604: "+files.toString());
        assert files != null;
        System.out.println("Line number 608: " + files.length);
        for (int i = 1; i < files.length; i++) {

            if (files[i].getName().contains(ext)) {
                bool = true;
            }
        }
        dir.delete();
        return bool;
    }

    public void readExcel(String filePath, String fileName, String sheetName) throws IOException {

        file = new File(filePath + "" + "" + fileName);
        inputStream = new FileInputStream(file);
        workbook = new HSSFWorkbook(inputStream);
        sheet = (HSSFSheet) workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum();
        System.out.println("Row Count: " + rowCount);
        for (int i = 0; i < rowCount; i++) {
            row = sheet.getRow(i);

            // Create a loop to print cell values in a row

            for (j = 0; j < row.getLastCellNum(); j++) {

                // Print Excel data in console
                String excelValues = row.getCell(j).getStringCellValue();
                System.out.println("Excel values: " + excelValues);
            }

            System.out.println();
        }
        file.delete();
    }

    public void writeExcel(String filePath, String fileName, String[] dataToWrite)
            throws IOException {

        // Create an object of File class to open xlsx file

        File file = new File(filePath + "\\" + fileName);

        // Create an object of FileInputStream class to read excel file

        FileInputStream inputStream = new FileInputStream(file);

        Workbook workbook = null;

        // Find the file extension by splitting  file name in substring and getting only extension name

        String fileExtensionName = fileName.substring(fileName.indexOf("."));

        // If it is xls file then create object of XSSFWorkbook class

        workbook = new HSSFWorkbook(inputStream);

        // Read excel sheet by sheet name

        Sheet sheet = workbook.getSheet("Sheet1");

        // Get the current count of rows in excel file

        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

        // Get the first row from the sheet

        Row row = sheet.getRow(0);

        // Create a new row and append it at last of sheet

        Row newRow = sheet.createRow(rowCount);

        // Create a loop over the cell of newly created Row
        System.out.println("row.getLastCellNum(): " + row.getLastCellNum());
        for (int j = 0; j < row.getLastCellNum(); j++) {

            // Fill data in row

            Cell cell = newRow.createCell(j);

            cell.setCellValue(dataToWrite[j]);
        }

        // Close input stream

        inputStream.close();

        // Create an object of FileOutputStream class to create write data in excel file

        FileOutputStream outputStream = new FileOutputStream(file);

        // write data in the excel file

        workbook.write(outputStream);

        // close output stream

        outputStream.close();
    }

    public void move(WebElement element, WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    public void deleteOldFile(String filePath) {
        File file = new File(filePath);

        if (file.delete()) {
            System.out.println("File deleted successfully......");
        } else {
            System.out.println("Failed to delete the file, File may be open or there is no file");
        }
    }

    public void deleteAllFileFromDir(String directoryName) {
        File directory = new File(directoryName);

        // Get all files in directory

        File[] files = directory.listFiles();
        assert files != null;
        for (File file : files) {
            // Delete each file

            if (file.isFile() && file.delete()) {
                // Failed to delete file
                System.out.println("All file deleted from folder....");

            } else if (!file.isFile()) {
                System.out.println("Failed to delete there is no file or file is open");
            }
        }
    }

    public String getCurrentTime() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm a");
        LocalDateTime now = LocalDateTime.now();

        return dtf.format(now);
    }

    public void writeFile(String filePath, String fileName) throws IOException {

        File file = new File(filePath + "\\" + fileName);
        FileInputStream inputStream = new FileInputStream(file);
        HSSFWorkbook workbook = null;
        String fileExtensionName = fileName.substring(fileName.indexOf("."));
        System.out.println("File Extension is: " + fileExtensionName);
        try {
            workbook = new HSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert workbook != null;
        sheet = workbook.getSheet("Sheet1");
        row = sheet.getRow(0);
        System.out.println("row.getLastCellNum(): " + row.getLastCellNum());

        inputStream.close();
        FileOutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);
    }

    public void splitString(String enterStr) {
        String[] str = enterStr.split("to");
        for (String s : str) {
            System.out.println("Split String is:++++++++++++++++++++++++++++--------------- " + s);
        }
    }

    public void typeText(WebElement ele, String values, WebDriver driver) {
        jse.executeScript("arguments[0].value='values';", ele);
    }

    public void handelMonth(String mont_year) {
        List<WebElement> month_Element = driver.findElements(By.xpath("//*[@class='month']"));
        for (WebElement webElement : month_Element) {
            System.out.println("Month data is: " + webElement.getText());
            String month = webElement.getText();

            if (!month.equals(mont_year)) {
                driver.findElement(By.xpath("//*[@class='prev available']")).click();
            }
        }
    }

    public void handelDropDown(WebElement element, String str) {
        Select select = new Select(element);
        select.selectByVisibleText(str);
    }

    public void CloseCurrentTabAndSwitch() {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

        System.out.println("Tabs are: " + tabs.size());

        driver.switchTo().window(tabs.get(0));

        focusOnTab();
    }

    public String getFileExtension(File fileURL) {
        String name = fileURL.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }

    public String getHiddenText(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // System.out.println("Hidden text: " + text);
        return (String) js.executeScript("return arguments[0].value;", element);
    }
}
