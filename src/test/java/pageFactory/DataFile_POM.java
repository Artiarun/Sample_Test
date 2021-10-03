/*VIJAY DESHPANDE */

package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import step_definitions.Hooks;

import java.util.List;

public class DataFile_POM {
    @FindBy(xpath = "//li[@id='uc_Menu_li_DataFile']//a[@class='parent-menu']")
    public WebElement getDataFileMenu;

    @FindBy(xpath = "//*[@id='uc_Menu_link_10']")
    public WebElement getCategoryCodeLink;

    @FindBy(xpath = "//*[@id='lbl_Group']")
    public WebElement getDataTableHeadingText;

    @FindBy(xpath = "//a[@id='btn_SingleAddNew']")
    public WebElement getAddButton;

    @FindBy(xpath = "//*[@id='GV_Selected']//td[4]/a/i")
    public WebElement getRemoveButtonFromSelectSupplier;


    @FindBy(xpath = "//*[@id='lbl_QuickLinks']")
    public WebElement getQuickLinkReport;

    @FindBy(name = "txtSearch")
    public WebElement getSearchTextBox;

    @FindBy(xpath = "//*[contains(text(), 'No records available')] |//*[@id='frmsearch']//td[3]")
    public List<WebElement> getDescriptionData;

    @FindBy(xpath = "//*[contains(text(), 'No records available')] |//*[@id='frmsearch']//td[4]")
    public List<WebElement> getBudgetSearchData;

    @FindBy(xpath = "//*[contains(text(), 'No records available')] |//*[@id='frmsearch']//td[5]")
    public List<WebElement> getActiveSearchData;

    //Parent Category Code
    @FindBy(xpath = "//i[@class='fa fa-folder-open']")
    public WebElement getParentCategoryCode;

    //list of codes
    @FindBy(xpath = "//*[@class='CssParentNode']")
    public List<WebElement> getAllParentCode;

    //+ icon
    @FindBy(xpath = "//span[@class='CssChildNode']")
    public List<WebElement> getChildCode;

    //Add supplier button from category popup
    @FindBy(xpath = "//*[@id='cmdAddSuppCatDet']")
    public WebElement getAddSupplierButtonFromCatPopUp;

    //search button from select supplier
    @FindBy(id = "btn_FindNow")
    public WebElement getButtonFind;

    //Add button
    @FindBy(id = "btn_AddNew")
    public WebElement getAddNewButton;

    //Remove All button
    @FindBy(id = "lnk_RemoveAll")
    public WebElement getRemoveAllButton;

    //Total Records text
    @FindBy(xpath = "//*[@id='lbl_Total_Records']")
    public WebElement getTotalRecordsText;

    //save button
    @FindBy(id = "btn_Save")
    public WebElement getSaveButtonAddSupplier;

    //Close button
    @FindBy(id = "btn_Close")
    public WebElement getCloseButtonSelectSupplier;


    @FindBy(name = "lst_Available")
    public WebElement getExportFieldDropDown;

    @FindBy(id = "btn_Add")
    public WebElement getAddSingleCriteriaButton;

    @FindBy(id = "btn_Remove")
    public WebElement getRemoveSingleCriteriaButton;

    @FindBy(id = "btn_Add_All")
    public WebElement getAddAllCriteriaButton;

    @FindBy(id = "btn_Remove_All")
    public WebElement getRemoveAllCriteriaButton;

    @FindBy(xpath = "//*[@id='lst_FileFormat']/option")
    public List<WebElement> getSelectFileFormatDropDownValues;

    //close button of export popup
    @FindBy(xpath = "//a[@id='lnk_Close']")
    public WebElement getExportCloseButton;

    //save export template //a[@id='btnSave']
    @FindBy(xpath = "//a[@id='btnSave']")
    public WebElement getExportTemplateSaveButton;

    //error msg
    @FindBy(id = "lblErrMsg")
    public WebElement getErrorMsg;

    //Select file format
    @FindBy(id = "lst_FileFormat")
    public WebElement getFileFormatDropDown;

    @FindBy(id = "lbl_File_Exported")
    public WebElement getFileExportValidationMsg;
//add cat cancel

    @FindBy(xpath = "//a[@id='cmdCancel']")
    public WebElement getCancelButtonFromAddCat;
    //
    @FindBy(id = "lnk_Issue")
    public WebElement getClickOnViewDownloadFileButton;

    @FindBy(id = "lnk_Back")
    public WebElement getBackButton;

    @FindBy(id = "btn_Close")
    public WebElement getCloseButton;

    @FindBy(id = "lblAvailableFields")
    public WebElement getAvailableExportFields;

    @FindBy(id = "lblSelectedFields")
    public WebElement getSelectedExportFields;

   /* @FindBy(id = "lbl_Export_Text")
    public WebElement getLabelExportText;*/

    @FindBy(xpath = "//td[@class='font_8 red_font']")
    public WebElement getExportNoteText;

    @FindBy(id = "btn_Export")
    public WebElement getExportButtonOnExportDataPopUp;

    @FindBy(name = "txtBudget$txtCtrlNumeric")
    public WebElement getBudgetTextBox;

    @FindBy(xpath = "//a[@id='btnSearch']")
    public WebElement getSearchButton;

    @FindBy(name = "ddl_Searchlist")
    public WebElement getCategoryCodeDropDown;

  /*  @FindBy(xpath = "//*[@id='ddl_Searchlist']/option")
    public WebElement getCategoryCodeDropDownOptions;*/

    @FindBy(xpath = "//*[@id='gvSearchResult']//tr[1]/th")
    public List<WebElement> getTableHeading;

    @FindBy(id = "btn_Clear")
    public WebElement getClearButton;

    @FindBy(xpath = "//a[@class='btn_blue_br quick-menu']")
    public WebElement getExportButton;

 /*   @FindBy(xpath = "//*[text()='Category - Add']")
    public WebElement getAddCategoryPopUpHeading;*/

    @FindBy(id = "txtCategoryCode")
    public WebElement getCategoryCodeTextBox;

    @FindBy(id = "txtDescription")
    public WebElement getDescriptionTextBox;

    //Add supplier button
    @FindBy(xpath = "//a[@id='cmdAddSuppCatDet']")
    public WebElement getAddSupplierButton;

    @FindBy(id = "chkActive")
    public WebElement getActiveButton;

 /*   @FindBy(xpath = "//*[@id='gvSearchResult']//tr")
    public List<WebElement> getTableDataCount;*/

    @FindBy(id = "updatepaneltotal")
    public WebElement getRecordCount;

    @FindBy(id = "btnImport")
    public WebElement getImportButton;

    @FindBy(id = "btnShowCriteria")
    public WebElement getShowAdvanceSearchButton;

    @FindBy(xpath = "//*[@id='frmsearch']//td[2]")
    public List<WebElement> getCategoryCodeSearchData;

    @FindBy(xpath = "//*[@id='VSForm']/ul/li")
    public List<WebElement> getListErrorMsg;

    @FindBy(id = "PopUpRegion1_dd_Region")
    public WebElement getSiteDropDown;


    //delete button
    @FindBy(xpath = "//a[@id='cmdDelete']")
    public WebElement getDeleteButton;

    //save button
    @FindBy(xpath = "//a[@id='cmdSave']")
    public WebElement getSaveButton;

    @FindBy(id = "cmdCancel")
    public WebElement getCancelButton;

    @FindBy(xpath = "//*[@id='gvSearchResult']//tr/td[3]")
    public List<WebElement> getBudgetAmount;

    @FindBy(xpath = "//*[@id='btBudgetMonthly']")
    public WebElement getViewMonthlyBreakup;

    @FindBy(xpath = "//*[contains(@name,'$txt_Budget$txtCtrlNumeric')]")
    public List<WebElement> getMonthlyBudgetValues;

  /*  @FindBy(xpath = "//*[contains(@id,'rpt_BudgetMonthly_lbl_MonthName_Value_')]")
    public List<WebElement> getMontName;*/

    public DataFile_POM() {
        WebDriver driver = Hooks.driver;
        // This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }
}
