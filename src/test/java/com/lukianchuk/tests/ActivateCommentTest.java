package com.lukianchuk.tests;

import com.lukianchuk.drivers.Drivers;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class ActivateCommentTest extends Drivers {


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

    @Test //#1 - Check the Alert no comment is chosen appears
    public void checkCommentsApplicationDialogOnSelectAnActoinTest() {
        System.out.println("Open Main Page");
        driver.get(MAIN_PAGE_URL);

        System.out.println("Choose " + SELECT_AN_ACTION + " from Select an Action drop-down list");
        slectAnActionDropDown(SELECT_AN_ACTION);

        System.out.println("Check that Alert dialog appears");
        assertTrue("Element is not present / displayed", chechCommentAlertIsDisplayed());
    }


    @Test //#2 - Check the Alert no comment is chosen appears
    public void checkCommentsWasActivatedSuccessfullTest() {
        System.out.println("Open Main Page");
        driver.get(MAIN_PAGE_URL);


        System.out.println("Check N=" + NTH_COMMENT_CHECK_BOX + " Comment check-box");
        checkNthCommentCheckBox(NTH_COMMENT_CHECK_BOX);


        System.out.println("Storing Active value of Nth Comment");
        String currentActiveValue = activeColumn.get(NTH_COMMENT_CHECK_BOX).getText();

        System.out.println("Choose " + SELECT_AN_ACTION + " from Select an Action drop-down list");
        slectAnActionDropDown(SELECT_AN_ACTION);

        System.out.println("Check successful message appears");


        assertTrue("Comments was successful was NOT displayed", commentWasActivatedInactivatedSuccessful.isDisplayed());

        System.out.println("Check that the message correct");
        assertEquals("Message is not correct", createCommentsSuccessfulMessage(),
                commentWasActivatedInactivatedSuccessful.getText());


    }


}
