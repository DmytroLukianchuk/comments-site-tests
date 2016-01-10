package com.lukianchuk.tests;

import com.lukianchuk.drivers.Drivers;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class EditCommentTest extends Drivers {

    // #1 - Check the dialog alert (comment was not chosen)
    // #2 - Check the dialog alert (comment was not chosen)

    @Test //#1
    public void checkCommentsApplicationDialogTest() {
        System.out.println("Open Main Page");
        driver.get(MAIN_PAGE_URL);

        System.out.println("Click on Edit Button Link without checking any Comment");
        clickEditCommentButtonCheckUrlOrAlert();

        assertTrue("Element is not present / displayed", chechCommentAlertIsDisplayed());
    }

    @Test //#2
    public void checkCategoriesWhenEditCommentTest() {
        System.out.println("Open Main Page");
        driver.get(MAIN_PAGE_URL);

        System.out.println("Check N=" + NTH_COMMENT_CHECK_BOX + " Comment check-box");
        checkNthCommentCheckBox(NTH_COMMENT_CHECK_BOX);

        System.out.println("Storing Cat value of Nth Comment");
        String expectedCatValue = getNthCatValueOfNthComment(NTH_COMMENT_CHECK_BOX);

        System.out.println("Click on Edit Button Link");
        clickEditCommentButtonCheckUrlOrAlert();

        System.out.println("Check Cat value");
        assertEquals("Cat value is incorrect", expectedCatValue,
                getSelectedCatValueOnEditCommentPage());
        System.out.println("Selected Cat was " + getSelectedCatValueOnEditCommentPage());
    }

    @Test //#3
    public void editCommentValueSaveReturnFindTest() {
        System.out.println("Open Main Page");
        driver.get(MAIN_PAGE_URL);

        System.out.println("Check N=" + NTH_COMMENT_CHECK_BOX + " Comment check-box");
        checkNthCommentCheckBox(NTH_COMMENT_CHECK_BOX);

        System.out.println("Click on Edit Button Link");
        clickEditCommentButtonCheckUrlOrAlert();

        System.out.println("Change Comment Text");
        findCommentTextField().clear();
        findCommentTextField().sendKeys(VALID_COMMENT_TEXT);

        System.out.println("Click Save&Reutrn Button Link");
        clickSaveAndReturnButtonLinkCheckUrlMain();

        System.out.println("Find Edited Comment is present");
        findCommentByCommentAllPages();
    }


}
