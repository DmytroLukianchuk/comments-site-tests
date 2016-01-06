package com.lukianchuk.drivers;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;

public class Drivers {
    public static final String MAIN_PAGE_URL = "http://comments.azurewebsites.net/";
    public static final String NEWCOMMENT_PAGE_URL = "http://comments.azurewebsites.net/Editor/NewComment";
    public static final String DUPLICATE_PAGE_URL = "http://comments.azurewebsites.net/Editor/DuplicateComment";
    public static final String VALID_COMMENT_TEXT = "0123";
    public static final String INVALID_COMMENT_TEXT = "*&^%$";
    public static final String VALID_NUMBER_VALUE = "999";
    public static final String INVALID_NUMBER_VALUE = "abcd";
    public static final String COMMENT_TEXT_EMPTY_ERROR = "The Comment Text field is required.";
    public static final String NUMBER_FIELD_VALUE_ERROR = "The Number field should contain value from 0 to 999 " +
            "and should be unique";

    public static final String COMMENT_TEXT_NOT_ALPHA_NUM_ERROR = "The Comment Text field should contain " +
            "alphanumeric characters only";
    public static final String COMMENT_MUST_HAVE_GROUP_ERROR = "Comments must be assigned to at least one category. " +
            "Please select a category before trying to save this comment again.";

    public static final int NTH_COMMENT_CHECK_BOX = 1;
    public WebDriver driver = new FirefoxDriver();


    @FindBy(xpath = "//input[@value='New...']")
    WebElement newCommentButton;

    @FindBy(xpath = "//input[@value='Save']")
    WebElement saveButtonLink;

    @FindBy(xpath = "//input[@value='Save & Return']")
    WebElement saveAndReturnButtonLink;

    @FindBy(xpath = "//a[@href='/Editor/Refresh?idObj=0&baseid=0']")
    WebElement refreshButtonLink;

    @FindBy(name = "Text")
    WebElement commentTextField;

    @FindBy(xpath = "//input[@id='Number']")
    WebElement numberInput;

    @FindBy(xpath = "//input[@name = 'CurSelect']")
    WebElement moveRightActionButton;

    @FindBy(xpath = "//a[@href='/']")
    WebElement returnButton;

    @FindBy(id = "errorfield")
    WebElement errorCommentNotHaveCategory;

    @FindBy(id = "errorfield")
    WebElement errorCommentFieldRequired;

    @FindBy(id = "errorfield")
    WebElement errorNumberFieldNotValid;

    @FindBy(id = "errorfield")
    WebElement errorNotCorrectCommentFieldValue;

    @FindBy(xpath = "//input[@value='Duplicate...']")
    WebElement duplicateButtonLink;

    @FindBy(id = "Text")
    WebElement commentsFieldOnDuplicatePage;

    @FindBy(id = "Number")
    WebElement numberFieldValueOnDuplicatePage;

    @FindBy(xpath = "//div[@role='dialog']")
    WebElement commentAlert;



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
    public void clickNewCommentButtonCheckUrl() {
        newCommentButton.click();
        assertEquals("URL is incorrect", "http://comments.azurewebsites.net/Editor/NewComment",
                driver.getCurrentUrl());
    }

    public String findNthCommentValue(int NTH_COMMENT_CHECK_BOX) {
        return driver.findElement(By.xpath("//tr[" + NTH_COMMENT_CHECK_BOX + "]/td[3]")).getText();
    }


    // ******* NEWCOMMENT PAGE DRIVERS
    public void clickSaveButtonLink() {
        saveButtonLink.click();
    }

    public void clickSaveAndReturnButtonLink() {
        saveAndReturnButtonLink.click();
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

    public WebElement findCheckBoxN(int catN) {
        return driver.findElement(By.xpath("//input[@id='Categories' and @value='" + catN + "']"));
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
            if (checkCommentIsPresentOnPageNumber(VALID_NUMBER_VALUE, pageNumber)) {
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

    public boolean checkCommentIsPresentOnPageNumber(String VALID_NUMBER_VALUE, int pageNumber) {
        driver.get("http://comments.azurewebsites.net/?page=" + pageNumber);
        for (int lineCounter = 1; lineCounter < 10; lineCounter++) {
            String currentNumber = driver.findElement(By.xpath("//tr[" + lineCounter + "]/td[2]")).getText();
            if (currentNumber.equals(VALID_NUMBER_VALUE)) {
                System.out.println("Your Comment " + VALID_COMMENT_TEXT + " is found on " + pageNumber + " Page and" +
                        " on the " + lineCounter + " Line");
                return true;
            }
        }
        return false;
    }


    // ****** DUPLICATE PAGE
    public void checkNthCommentCheckBox(int checkBox) {
        driver.findElement(By.xpath("//input[@type='checkbox' and @value='" + checkBox + "']")).click();
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

}
