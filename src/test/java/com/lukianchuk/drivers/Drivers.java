package com.lukianchuk.drivers;

import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public static final String NUMBER_FIELD_VALUE_ERROR = "The Number field should contain value from 0 to 999 " +
            "and should be unique";
    public static final String COMMENT_TEXT_NOT_ALPHA_NUM_ERROR = "The Comment Text field should contain " +
            "alphanumeric characters only";

    public static final String COMMENT_MUST_HAVE_GROUP_ERROR = "Comments must be assigned to at least one category. " +
            "Please select a category before trying to save this comment again.";
    public static final int NTH_COMMENT_CHECK_BOX = 9;
    public WebDriver driver = new FirefoxDriver();


    @FindBy(xpath = "//input[@value='New...']")
    WebElement newCommentButton;

    @FindBy(xpath = "//input[@value='Edit...']")
    WebElement editCommentButton;

    @FindBy(xpath = "//input[@value='Delete']")
    WebElement deleteCommentButton;

    @FindBy(xpath = "//input[@value='Save']")
    WebElement saveButtonLink;

    @FindBy(xpath = "//input[@value='Save & Return']")
    WebElement saveAndReturnButtonLink;

    @FindBy(xpath = "//a[@href='/']")
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

    @FindBy(id = "infoField")
    WebElement commentDeletedSuccessfull;

    @FindBy(xpath = "//div[@role='dialog']")
    WebElement commentAlert;

    @FindBy(xpath = "//div[@id='selectedCategories']/div[@class='categoryitem']")
    List<WebElement> selectedCategoriesOnEditCommentPage;

    @FindBy(xpath = "//input[@type='checkbox']")
    List<WebElement> catCheckboxes;

    @FindBy(xpath = "//tr/td[5]")
    List<WebElement> categoriesOfComment;

    @FindBy(xpath = "//tr/td[3]")
    List<WebElement> nthCommentValue;

    @FindBy(xpath = "//input[@type='checkbox' and @id='Categories']")
    List<WebElement> availableCatCheckBoxes;

    @FindBy(xpath = "//tr/td[2]")
    List<WebElement> numbers;

    @FindBy(xpath = "//tr/td[3]")
    List<WebElement> commentText;

    @FindBy(xpath = "//span[@class='ui-button-text']")
    List<WebElement> alertButtonsOnDeleteAlert;



    public Drivers() {
        PageFactory.initElements(driver, this);
    }

//    @After
//    public void tearDown() throws Exception {
//        driver.close();
//
//    }

    @Before
    public void setUp() throws Exception {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

    }

    // ********** MAIN PAGE DRIVERS
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
        if (alertButton.equals("Yes")) {
            alertButtonsOnDeleteAlert.get(0).click();
        } else if (alertButton.equals("No")) {
            alertButtonsOnDeleteAlert.get(1).click();
        } else {
            System.out.println("You have not specified Yes or No");
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

    // ******* NEWCOMMENT PAGE DRIVERS
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

}
