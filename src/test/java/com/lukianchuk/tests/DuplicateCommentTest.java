package com.lukianchuk.tests;

import com.lukianchuk.drivers.Drivers;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class DuplicateCommentTest extends Drivers {
    private WebElement commentAlert;

// #1 Positive > Click on Duplicate > Check URL & Copy of + Comment
// #2 Negative > Check Alert not chosen any Comment
// #3 Negative > Check Alert not chosen any Comment

    private String duplicateCommentPreconditions() {
        System.out.println(MAIN_PAGE_URL);
        driver.get(MAIN_PAGE_URL);

        System.out.println("Check " + NTH_COMMENT_CHECK_BOX + " check-box next to Comment");
        checkNthCommentCheckBox(NTH_COMMENT_CHECK_BOX);

        System.out.println("Store " + NTH_COMMENT_CHECK_BOX + " Comments value");
        String currentCommentValue = findNthCommentValue(NTH_COMMENT_CHECK_BOX);

        System.out.println("Click on Duplicate button link");
        clickDuplicateButtonLink();

        System.out.println("Check URL is /Editor/DuplicateComment");
        assertEquals("URL is not correct", DUPLICATE_PAGE_URL, driver.getCurrentUrl());

        return currentCommentValue;
    }

    @Test //#1
    public void checkCommentTextTest() {
        String currentCommentValue = duplicateCommentPreconditions();

        System.out.println("Check Comment Text has Copy of" + currentCommentValue);
        assertEquals("Comment Text Value is incorrect", "Copy of" + currentCommentValue,
                getCommentsValueOnDuplicatePage());
    }

    @Test //#2
    public void checkNumberFieldDefaultValueTest() {
        duplicateCommentPreconditions();
        String currentNumberValue = numberFieldValueOnDuplicatePage();

        System.out.println("Check Number field has 0 default value. Value = " + currentNumberValue);
        assertEquals("Comment Number Value is incorrect", currentNumberValue,
                numberFieldValueOnDuplicatePage());
    }

    @Test //#3
    public void checkNumberNotUniqueErrorTest() {
        duplicateCommentPreconditions();

        System.out.println("Click on Save button link");
        clickSaveButtonLink();

        System.out.println("Checking Error");
        checkErrorNumberFieldNotValid();
    }

    @Test //#4
    public void checkEnterUniqueNumberAndSaveReturnTest() {
        duplicateCommentPreconditions();

        System.out.println("Enter Unique Number Value > " + VALID_NUMBER_VALUE);
        findNumberInput().sendKeys(VALID_NUMBER_VALUE);

        System.out.println("Click on Save & Return button link");
        clickSaveAndReturnButtonLink();

        System.out.println("Check user is on the Main Page " + MAIN_PAGE_URL);
        assertEquals("URL is not Main", MAIN_PAGE_URL, driver.getCurrentUrl());

        System.out.println("Check that Comment is present on all pages");
        findCommentByNumberAllPages();
    }

    @Test //#5
    public void commentsApplicationDialog() {
        System.out.println("Open Main Page");
        driver.get(MAIN_PAGE_URL);

        System.out.println("Click on Duplicate Button Link");
        clickDuplicateButtonLink();

        assertTrue("Element is not present / displayed", chechCommentAlertIsDisplayed());
    }
}
