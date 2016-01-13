package com.lukianchuk.drivers;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class Drivers {
    public static final String MAIN_PAGE_URL = "http://comments.azurewebsites.net/";
    public static final String NEWCOMMENT_PAGE_URL = "http://comments.azurewebsites.net/Editor/NewComment";
    public static final String DUPLICATE_PAGE_URL = "http://comments.azurewebsites.net/Editor/DuplicateComment";
    public static final String EDIT_NEW_COMMENT_URL = "http://comments.azurewebsites.net/Editor/NewComment";
    public static final String VALID_COMMENT_TEXT = "0123";
    public static final String INVALID_COMMENT_TEXT = "*&^%$";
    public static final String VALID_NUMBER_VALUE = "999";
    public static final String INVALID_NUMBER_VALUE = "abcd";
    public static final String COMMENT_TEXT_EMPTY_ERROR = "The Comment Text field is required.";
    public static final String CATEGORY_NAME_DROPDOWN = "Cat1";
    public static final String STATUS_DROPDOWN = "All";
    public static final String SELECT_AN_ACTION = "Activate";
    public static final String NUMBER_FIELD_VALUE_ERROR = "The Number field should contain value from 0 to 999 " +
            "and should be unique";
    public static final String COMMENT_TEXT_NOT_ALPHA_NUM_ERROR = "The Comment Text field should contain " +
            "alphanumeric characters only";
    public static final String COMMENT_MUST_HAVE_GROUP_ERROR = "Comments must be assigned to at least one category. " +
            "Please select a category before trying to save this comment again.";
    public static final String COMMENT_WAS_ACTIVETED_MESSAGE = "Comments was activated successfull";
    public static final int NTH_COMMENT_CHECK_BOX = 9;

    public WebDriver driver = new FirefoxDriver();

    @FindBy(css = "hr")
    protected WebElement hrLineDevider;
    @FindBy(xpath = "//th/a[contains(@href,'/?sort=Number')]")
    protected WebElement numberLinkColumnFilter;
    @FindBy(xpath = "//th/a[contains(@href,'/?sort=Text')]")
    protected WebElement commentTextLinkColumnFilter;
    @FindBy(xpath = "//th/a[contains(@href,'/?sort=Active')]")
    protected WebElement activeLinkColumnFilter;
    @FindBy(xpath = "//tr/td[@class='categorycolumn']")
    protected List<WebElement> categoriesValueColumn;
    @FindBy(id = "SelectedCateg")
    protected WebElement categoryNameDropDown;
    @FindBy(id = "commandSelect")
    protected WebElement selectAnAction;
    @FindBy(id = "SelectedStatus")
    protected WebElement statusDropDown;
    @FindBy(id = "applybutton")
    protected WebElement applyButton;
    @FindBy(xpath = "//tr/td[@class='inactivecolumn']")
    protected List<WebElement> activeColumn;
    @FindBy(id = "infoField")
    protected WebElement commentWasActivatedInactivatedSuccessful;
    @FindBy(xpath = "//input[@value='New...']")
    protected WebElement newCommentButton;
    @FindBy(xpath = "//input[@value='Edit...']")
    protected WebElement editCommentButton;
    @FindBy(xpath = "//input[@value='Delete']")
    protected WebElement deleteCommentButton;
    @FindBy(xpath = "//input[@value='Save']")
    protected WebElement saveButtonLink;
    @FindBy(xpath = "//input[@value='Save & Return']")
    protected WebElement saveAndReturnButtonLink;
    @FindBy(xpath = "//a[@href='/']")
    protected WebElement refreshButtonLink;
    @FindBy(name = "Text")
    protected WebElement commentTextField;
    @FindBy(xpath = "//input[@id='Number']")
    protected WebElement numberInput;
    @FindBy(xpath = "//input[@name = 'CurSelect']")
    protected WebElement moveRightActionButton;
    @FindBy(xpath = "//a[@href='/']")
    protected WebElement returnButton;
    @FindBy(id = "errorfield")
    protected WebElement errorCommentNotHaveCategory;
    @FindBy(id = "errorfield")
    protected WebElement errorCommentFieldRequired;
    @FindBy(id = "errorfield")
    protected WebElement errorNumberFieldNotValid;
    @FindBy(id = "errorfield")
    protected WebElement errorNotCorrectCommentFieldValue;
    @FindBy(xpath = "//input[@value='Duplicate...']")
    protected WebElement duplicateButtonLink;
    @FindBy(id = "Text")
    protected WebElement commentsFieldOnDuplicatePage;
    @FindBy(id = "Number")
    protected WebElement numberFieldValueOnDuplicatePage;
    @FindBy(id = "infoField")
    protected WebElement commentDeletedSuccessfull;
    @FindBy(xpath = "//div[@role='dialog']")
    protected WebElement commentAlert;
    @FindBy(xpath = "//div[@id='selectedCategories']/div[@class='categoryitem']")
    protected List<WebElement> selectedCategoriesOnEditCommentPage;
    @FindBy(xpath = "//input[@type='checkbox']")
    protected List<WebElement> catCheckboxes;
    @FindBy(xpath = "//tr/td[5]")
    protected List<WebElement> categoriesOfComment;
    @FindBy(xpath = "//tr/td[3]")
    protected List<WebElement> nthCommentValue;
    @FindBy(xpath = "//input[@type='checkbox' and @id='Categories']")
    protected List<WebElement> availableCatCheckBoxes;
    @FindBy(xpath = "//tr/td[2]")
    protected List<WebElement> numbers;
    @FindBy(xpath = "//tr/td[3]")
    protected List<WebElement> commentText;
    @FindBy(xpath = "//span[@class='ui-button-text']")
    protected List<WebElement> alertButtonsOnDeleteAlert;
    @FindBy(xpath = "//div/h1")
    protected WebElement h1CommentsText;

    public Drivers() {
        PageFactory.initElements(driver, this);
    }

    @After
    public void tearDown() throws Exception {
        driver.close();
    }

    @Before
    public void setUp() throws Exception {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    // ********** MAIN PAGE DRIVERS
    public void checkTitleIsUnderlined(WebElement webElement) {
        Assert.assertEquals("Element " + webElement + " is not underlined", "underline",
                webElement.getCssValue("text-decoration"));
    }

    public boolean checkCategoriesHaveFilteringValue(String CATEGORY_NAME_DROPDOWN) {
        for (int i = 1; i <= 10; i++) {
            String commentRow = driver.findElement(By.xpath("//tr[" + i
                    + "]/td[@class='categorycolumn']")).getText();
            System.out.println(commentRow);
            if (!commentRow.contains(CATEGORY_NAME_DROPDOWN)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkActiveColumnValue(String STATUS_DROPDOWN) {
        for (int i = 0; i < activeColumn.size() - 1; i++) {
            if (STATUS_DROPDOWN.equals("Inactive")) {
                System.out.println(activeColumn.get(i).getText()); // debug
                if (!activeColumn.get(i).getText().isEmpty()) {
                    System.out.println("I've found Status as ACTIVE");
                    return false;
                }
            } else if (STATUS_DROPDOWN.equals("Active")) {
                System.out.println(activeColumn.get(i).getText()); // debug
                if (activeColumn.get(i).getText().isEmpty()) {
                    System.out.println("I've found Status as INACTIVE");
                    return false;
                }
            }
            System.out.println(activeColumn.get(i).getText()); // debug
        }
        return true;
    }

    public void chooseCategoryNameDropDown(String CATEGORY_NAME_DROPDOWN) {
        Select droplist = new Select(categoryNameDropDown);
        droplist.selectByVisibleText(CATEGORY_NAME_DROPDOWN);
    }

    public void slectAnActionDropDown(String SELECT_AN_ACTION) {
        Select droplist = new Select(selectAnAction);
        droplist.selectByVisibleText(SELECT_AN_ACTION);
    }

    public void chooseStatusNameDropDown(String STATUS_DROPDOWN) {
        Select droplist = new Select(statusDropDown);
        droplist.selectByVisibleText(STATUS_DROPDOWN);
    }

    public void clickNewCommentButtonCheckUrl() {
        newCommentButton.click();
        assertEquals("URL is incorrect", EDIT_NEW_COMMENT_URL,
                driver.getCurrentUrl());
    }

    public void clickEditCommentButtonCheckUrlOrAlert() {
        editCommentButton.click();
        if (driver.getCurrentUrl().equals(MAIN_PAGE_URL)) {
            System.out.println("Checking dialog alert appears");
            assertTrue("Element is not present / displayed", chechCommentAlertIsDisplayed());
        } else {
            assertEquals("URL is incorrect", "http://comments.azurewebsites.net/Editor/EditComment",
                    driver.getCurrentUrl());
        }
    }

    public void clickDeleteCommentButtonCheckAlert() {
        deleteCommentButton.click();
        assertTrue("Element is not present / displayed", chechCommentAlertIsDisplayed());
    }

    public String getNthCatValueOfNthComment(int NTH_COMMENT_CHECK_BOX) {
        return categoriesOfComment.get(NTH_COMMENT_CHECK_BOX).getText();
    }

    public String findNthCommentValue(int NTH_COMMENT_CHECK_BOX) {
        return nthCommentValue.get(NTH_COMMENT_CHECK_BOX).getText();
    }

    public void clickYesOrNoButtonOnAlert(String alertButton) {
        switch (alertButton) {
            case "Yes":
                alertButtonsOnDeleteAlert.get(0).click();
                break;
            case "No":
                alertButtonsOnDeleteAlert.get(1).click();
                break;
            default:
                System.out.println("You have not specified Yes or No");
                break;
        }
    }

    public boolean checkCommentDeletedSuccessfull() {
        if (commentDeletedSuccessfull.isDisplayed()) {
            System.out.println("Green line appeared - comment is deleted");
            return true;
        } else {
            System.out.println("Green line did NOT appear");
            return false;
        }
    }

//    private boolean isSorted(List<? extends Comparable> list, boolean asc) {
//        for (int i = 0; i < list.size() - 1; i++) {
//            if (asc && (list.get(i).compareTo(list.get(i + 1))) > 0) {
//                return false;
//            }
//        }
//        return true;
//    }

    public void clickNumberORCommentORActiveFilterCheckUrl(int clickCounter, WebElement numberORCommentORActive,
                                                           boolean isCommentField) {

        int i = 0;
        while (i < clickCounter) {
            numberORCommentORActive.click();
            i++;
        }
        String partualURL;
        if (numberORCommentORActive.equals(numberLinkColumnFilter)) {
            partualURL = "NumberValue&Text";
        } else if (numberORCommentORActive.equals(commentTextLinkColumnFilter)) {
            partualURL = "Text&Text";
        } else {
            partualURL = "Active&Text";
        }


        if (clickCounter % 2 == 1 ^ isCommentField) {
            assertEquals("Url is not correct", MAIN_PAGE_URL + "?sort=" + partualURL + "=ASC", driver.getCurrentUrl());
//            isSorted(listOfInts, true);

        } else {
            assertEquals("Url is not correct", MAIN_PAGE_URL + "?sort=" + partualURL + "=DESC", driver.getCurrentUrl());
        }

//        List<Integer> listOfInts = numbers.stream().map(e -> Integer.valueOf(e.getText())).collect(Collectors.toList());
//        List<String> listOfStrings = numbers.stream().map(WebElement::getText).collect(Collectors.toList());

//        Integer a = 1, b = 2;
//        String as = "1", bs = "2";
//        if (a.compareTo(b) < 0) {
//            System.out.println("a is smaller than b");
//        }
//        as.compareTo(bs);
    }


    // ******* NEWCOMMENT PAGE DRIVERS
    public String createCommentsSuccessfulMessage() {
        String successfulMessage = "";
        if (SELECT_AN_ACTION.equals("Activate")) {
            successfulMessage = "Comments was activated successfull";
            System.out.println(successfulMessage);
        } else if (SELECT_AN_ACTION.equals("Inactivate")) {
            successfulMessage = "Comments was inactivated successfull";
            System.out.println(successfulMessage);
        }
        return successfulMessage;
    }

    public void clickSaveButtonLink() {
        saveButtonLink.click();
    }

    public void clickSaveAndReturnButtonLinkCheckUrlMain() {
        saveAndReturnButtonLink.click();
        assertEquals("URL is not Main", "http://comments.azurewebsites.net/",
                driver.getCurrentUrl());
    }

    public void clickRefreshButtonLink() {
        refreshButtonLink.click();
    }

    public WebElement findCommentTextField() {
        return commentTextField;
    }

    public WebElement findNumberInput() {
        return numberInput;

    }

    public void clickAvailableCatCheckBox(int catN) {
        availableCatCheckBoxes.get(catN).click();
    }

    public WebElement findMoveRightActionButton() {
        return moveRightActionButton;
    }


    // ******* GENERAL DRIVERS
    public void clickReturnButtonLink() {
        returnButton.click();
    }

    public void findCommentByNumberAllPages() {
        System.out.println("Find added Comment by Number field within All Pages");
        for (int pageNumber = 1; pageNumber <= 4; pageNumber++) {
            if (checkNumberIsPresentOnPageNumber(VALID_NUMBER_VALUE, pageNumber)) {
                break;
            } else {
                System.out.println("Sorry... your comment is not present on " + pageNumber + " Page. Trying to" +
                        "find on the next Page");
            }
        }
    }

    public void findCommentByCommentAllPages() {
        System.out.println("Find added Comment by Comment field within All Pages");
        for (int pageNumber = 1; pageNumber <= 4; pageNumber++) {
            if (checkCommentIsPresentOnPageNumber(VALID_COMMENT_TEXT, pageNumber)) {
                break;
            } else {
                System.out.println("Sorry... your comment is not present on " + pageNumber + " Page. Trying to" +
                        "find on the next Page");
            }
        }
    }

    public boolean chechCommentAlertIsDisplayed() {
        return commentAlert.isDisplayed();
    }


    // ****** ERRORS ON COMMENTS PAGE
    public void checkErrorCommentNotHaveCategory() {
        assertEquals("Error is not appeared / correct", COMMENT_MUST_HAVE_GROUP_ERROR,
                errorCommentNotHaveCategory.getText());
    }

    public void checkErrorCommentFieldRequired() {
        assertEquals("Error is not appeared / correct", COMMENT_TEXT_EMPTY_ERROR,
                errorCommentFieldRequired.getText());
    }

    public void checkErrorNumberFieldNotValid() {
        assertEquals("Error is not appeared / correct", NUMBER_FIELD_VALUE_ERROR,
                errorNumberFieldNotValid.getText());
    }

    public void checkcheckErrorNotCorrectCommentFieldValue() {
        assertEquals("Error is not appeared / correct", COMMENT_TEXT_NOT_ALPHA_NUM_ERROR,
                errorNotCorrectCommentFieldValue.getText());
    }

    public boolean checkNumberIsPresentOnPageNumber(String VALID_NUMBER_VALUE, int pageNumber) {
        driver.get("http://comments.azurewebsites.net/?page=" + pageNumber);

        for (int lineCounter = 0; lineCounter < 10; lineCounter++) {
            String currentNumber = numbers.get(lineCounter).getText();
            if (currentNumber.equals(VALID_NUMBER_VALUE)) {
                System.out.println("Your Comment " + VALID_COMMENT_TEXT + " is found on " + pageNumber + " Page and" +
                        " on the " + lineCounter + " Line");
                return true;
            }
        }
        return false;
    }

    public boolean checkCommentIsPresentOnPageNumber(String VALID_COMMENT_TEXT, int pageNumber) {
        driver.get("http://comments.azurewebsites.net/?page=" + pageNumber);

        for (int lineCounter = 0; lineCounter < 10; lineCounter++) {
            String currentNumber = commentText.get(lineCounter).getText();
            if (currentNumber.equals(VALID_COMMENT_TEXT)) {
                System.out.println("Your Comment " + VALID_COMMENT_TEXT + " is found on " + pageNumber + " Page and" +
                        " on the " + lineCounter + " Line");
                return true;
            }
        }
        return false;
    }


    // ****** DUPLICATE PAGE
    public void checkNthCommentCheckBox(int checkBox) {
        catCheckboxes.get(checkBox).click();
    }

    public void clickDuplicateButtonLink() {
        duplicateButtonLink.click();
    }

    public String getCommentsValueOnDuplicatePage() {
        return commentsFieldOnDuplicatePage.getAttribute("value");

    }

    public String numberFieldValueOnDuplicatePage() {
        return numberFieldValueOnDuplicatePage.getAttribute("value");

    }


    // ***** EDIT COMMENT PAGE
    public String getSelectedCatValueOnEditCommentPage() {
        String result = "";
        for (WebElement selectedCategory : selectedCategoriesOnEditCommentPage) {
            if (!result.isEmpty()) {
                result += ", ";
            }
            result += selectedCategory.getText();
        }
        return result;
    }

    // ***** OTHER
    public void h1CommentsTextGetColor() {
        String h1Color = h1CommentsText.getCssValue("color");
        System.out.println(h1Color);

        System.out.println("Checking that the color of h1 is white");
        assertEquals("Colors are not equal", "rgba(255, 255, 255, 1)", h1Color);

    }

}
