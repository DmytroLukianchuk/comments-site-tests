package com.lukianchuk.tests;

import com.lukianchuk.drivers.Drivers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;

public class CreateCommentTest extends Drivers {


    @After
    public void tearDown() throws Exception {
        driver.close();

    }

    @Before
    public void setUp() throws Exception {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

    }

    // #1 Create Comments Positive Test (New > CText / Number / Cat / Save)
    // #2 Create Comment Negative Test > Empty Comment Text value (NewCommentUrl > Save)
    // #3 Create Comment Negative Test > Number field error > 999 (NewCommentUrl > Enter 1000 to Number > Save)
    // #4 Create Comment Negative Test > No Categories assigned > (NewCommentUrl > Valid Comment > Valid Number > Save)
    // #5 Create Comment Negative Test > Not correct Comment Text value (NewCommentUrl > Incorrect Comment value > Save)


    @Test //#1
    public void createCommentPositiveTest() {
        System.out.println(MAIN_PAGE_URL);
        driver.get(MAIN_PAGE_URL);

        System.out.println("Click New... button");
        clickNewCommentButtonCheckUrl();

        System.out.println("Enter valid Comment Text");
        findCommentTextField().clear();
        findCommentTextField().sendKeys(VALID_COMMENT_TEXT);

        System.out.println("Enter valid Number value");
        findNumberInput().clear();
        findNumberInput().sendKeys(VALID_NUMBER_VALUE);

        System.out.println("Check CatN check-box");
        findCheckBoxN(2).click();
        findCheckBoxN(3).click();

        System.out.println("Click > button to move Cat");
        findMoveRightActionButton().click();

        System.out.println("Click Save button link");
        clickSaveButtonLink();

        System.out.println("Check that Comment field is erased");
        assertEquals("Comment field is not empty", "", findCommentTextField().getText());

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

    @Test //#2
    public void checkErrorEmptyCommentFieldTest() {
        System.out.println(NEWCOMMENT_PAGE_URL);
        driver.get(NEWCOMMENT_PAGE_URL);

        System.out.println("Click Save button link");
        clickSaveButtonLink();

        System.out.println("Check required error appears");
        checkErrorCommentFieldRequired();
    }

    @Test //#3
    public void checkErrorNumberFieldNotNumberTest() {
        System.out.println(NEWCOMMENT_PAGE_URL);
        driver.get(NEWCOMMENT_PAGE_URL);

        System.out.println("Enter valid Comment Text");
        findCommentTextField().clear();
        findCommentTextField().sendKeys(VALID_COMMENT_TEXT);

        System.out.println("Enter INvalid Number value > abcd");
        findNumberInput().clear();
        findNumberInput().sendKeys(INVALID_NUMBER_VALUE);

        System.out.println("Click Save button link");
        clickSaveButtonLink();

        System.out.println("Check required Comment not valid error appears");
        checkErrorNumberFieldNotValid();
    }

    @Test //#4
    public void checkErrorNoCategoryChosenTest() {
        System.out.println(NEWCOMMENT_PAGE_URL);
        driver.get(NEWCOMMENT_PAGE_URL);

        System.out.println("Enter valid Comment Text");
        findCommentTextField().clear();
        findCommentTextField().sendKeys(VALID_COMMENT_TEXT);

        System.out.println("Enter valid Number value");
        findNumberInput().clear();
        findNumberInput().sendKeys(VALID_NUMBER_VALUE);

        System.out.println("Click Save button link");
        clickSaveButtonLink();

        System.out.println("Check required comment not have Cat error appears");
        checkErrorCommentNotHaveCategory();
    }

    @Test //#5
    public void checkErrorNotCorrectCommentFieldValueTest() {
        System.out.println(NEWCOMMENT_PAGE_URL);
        driver.get(NEWCOMMENT_PAGE_URL);

        System.out.println("Enter INvalid Comment Text");
        findCommentTextField().clear();
        findCommentTextField().sendKeys(INVALID_COMMENT_TEXT);

        System.out.println("Click Save button link");
        clickSaveButtonLink();

        System.out.println("Check required error appears");
        checkcheckErrorNotCorrectCommentFieldValue();
    }

}