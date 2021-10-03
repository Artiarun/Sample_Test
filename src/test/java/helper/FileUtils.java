/*Vijay Deshpande */

package helper;

import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.w3c.dom.*;
import step_definitions.Hooks;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import static org.junit.Assert.assertEquals;

public class FileUtils {
  public ArrayList<String> arrayList;
  public static StringBuffer sb;
  public String str;
  public static Element eElement;
  public NodeList ele;

  public void readTextFile(String fileUrl) throws IOException {
    try {
      Scanner s = new Scanner(new File(fileUrl));
      ArrayList<String> list = new ArrayList<String>();
      while (s.hasNext()) {
        list.add(s.next());
      }
      s.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public void readXmlFile(String xmlFile, String tagName)
      throws FileNotFoundException, XMLStreamException {
    try {
      // creating a constructor of file class and parsing an XML file
      File file = new File(xmlFile);
      // an instance of factory that gives a document builder
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      // an instance of builder to parse the specified xml file
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document doc = db.parse(file);
      doc.getDocumentElement().normalize();
      System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
      NodeList nodeList = doc.getElementsByTagName("Category");
      // nodeList is not iterable, so we are using for loop
      for (int itr = 0; itr < nodeList.getLength(); itr++) {
        Node node = nodeList.item(itr);
        // System.out.println("\nNode Name :" + node.getNodeName());
        if (node.getNodeType() == Node.ELEMENT_NODE) {
          Element eElement = (Element) node;
          ele = eElement.getElementsByTagName(tagName);
          // System.out.println(eElement.getElementsByTagName("Active").getLength());

        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }




public ArrayList<String> readCSV(String csvFile) throws FileNotFoundException {
  Scanner sc = new Scanner(new File(csvFile));
  sc.useDelimiter(",");   //sets the delimiter pattern
  while (sc.hasNext())  //returns a boolean value
  {
    String data = sc.next();
      arrayList=new ArrayList<>();
      arrayList.add(data);
    System.out.print("++++++++++++++++++++++++++++++++++++++++++"+sc.next());  //find and returns the next complete token from this scanner
  }
    System.out.println(arrayList.size());
  sc.close();  //closes the scanner
    return arrayList;
}



  public static List<String> getAllContentFromCSV(String fileName) throws Exception{
    List<String> dataList = new ArrayList<>();
    String[] nextLine;
    boolean isFileFound = false;
    File[] files = new File(Hooks.downloadfolderPath).listFiles();
    for (File file : files) {
      if (file.isFile()) {
        if (FilenameUtils.getExtension(file.getName()).equals("csv")
                && file.getName().contains(fileName)) {
          isFileFound = true;
          CSVReader csvReader = new CSVReader(new FileReader(file.getPath()));
          while ((nextLine = csvReader.readNext()) != null)
            for(String token : nextLine)
              dataList.add(token);
          csvReader.close();
        }
      }
      if (isFileFound)
        file.delete();
    }
    Assert.assertTrue("File not downloaded properly", isFileFound);
    System.out.println(dataList);
    return dataList;
  }


  public void checkFileDownload(String fileName, String fileExtension) {

    boolean isFileFound = false;

    File[] files = new File(Hooks.downloadfolderPath).listFiles();
    System.out.println(files + "\t" + Hooks.downloadfolderPath);
    for (File file : files) {
      if (file.isFile()) {
        if (FilenameUtils.getExtension(file.getName()).equals(fileExtension)
            && file.getName().contains(fileName)) {
          isFileFound = true;
          file.delete();
          break;
        }
      }
    }
    assertEquals("File not downloaded properly", isFileFound, true);
  }

  public String getContentFromPDFDocument(String fileName) throws Exception {
    boolean isFileFound = false;
    String documentContent = null;

    File[] files = new File(Hooks.downloadfolderPath).listFiles();
    for (File file : files) {
      if (file.isFile()) {
        if (FilenameUtils.getExtension(file.getName()).equals("pdf")
            && file.getName().contains(fileName)) {
          isFileFound = true;
          PDDocument pdDocument = PDDocument.load(file);
          PDFTextStripper pdfTextStripper = new PDFTextStripper();
          documentContent = pdfTextStripper.getText(pdDocument);
          pdDocument.close();
          file.delete();
          break;
        }
      }
    }
    assertEquals("File not downloaded properly", isFileFound, true);
    return documentContent;
  }


  public void deleteDocument(String fileName, String fileExtension) throws Exception {
    File[] files = new File(Hooks.downloadfolderPath).listFiles();
    for (File file : files)
      if (file.isFile())
        if (FilenameUtils.getExtension(file.getName()).equals(fileExtension)
            && file.getName().contains(fileName)) file.delete();
  }
@Test
  public Properties readDbConfig()
  {
     String DbConfigUrl = System.getProperty("user.dir");

    //File file = new File("D:/Dev/ReadData/src/datafile.properties");
    File file = new File(DbConfigUrl+"/DbConfig.properties");
    System.out.println(file);
    FileInputStream fileInput = null;
    try {
      fileInput = new FileInputStream(file);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    Properties prop = new Properties();

    //load properties file
    try {
      prop.load(fileInput);
    } catch (IOException e) {
      e.printStackTrace();
    }
return prop;
  }

}
