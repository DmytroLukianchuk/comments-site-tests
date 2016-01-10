package com.lukianchuk.tests;

import com.lukianchuk.drivers.Drivers;
import org.junit.Test;

public class DeleteCommentTest extends Drivers {

    // #1 - Check the dialog alert (comment was not chosen)
    // #2 - Check the dialog alert (comment was not chosen)

    public String deleteCommentPreconditions() {
        System.out.println("Open Main Page");
        driver.get(MAIN_PAGE_URL);

        System.out.println("Check N=" + NTH_COMMENT_CHECK_BOX + " Comment check-box");
        checkNthCommentCheckBox(NTH_COMMENT_CHECK_BOX);

        System.out.println("Storing Comment value of Nth Comment");
        String expectedCommentValue = findNthCommentValue(NTH_COMMENT_CHECK_BOX);

        System.out.println("Click on Delete Button Link");
        clickDeleteCommentButtonCheckAlert();
        return expectedCommentValue;
    }

    @Test //#1
    public void checkCommentsApplicationDialogOnDeleteLinkTest() {
        System.out.println("Open Main Page");
        driver.get(MAIN_PAGE_URL);

        System.out.println("Click on Delete Button Link without checking any Comment");
        clickDeleteCommentButtonCheckAlert();
    }

    @Test //#2
    public void checkCommentIsNotDeletedIfNoOnAlertTest() {
        String expectedCommentValue = deleteCommentPreconditions();

        System.out.println("Click NO button on Alert");
        clickYesOrNoButtonOnAlert("No");

        System.out.println("Check Comment Deleted Successful Green Line did NOT appear ");
        checkCommentDeletedSuccessfull();

        System.out.println("Find Comment is present / was not removed");
        checkCommentIsPresentOnPageNumber(expectedCommentValue, 1);
    }

    @Test //#3
    public void checkCommentIsDeletedIfYesOnAlertTest() {
        String expectedCommentValue = deleteCommentPreconditions();

        System.out.println("Click YES button on Alert");
        clickYesOrNoButtonOnAlert("Yes");

        System.out.println("Check Comment Deleted Successful Green Line appears ");
        checkCommentDeletedSuccessfull();

        System.out.println("Find Comment is NOT present / was removed on a first Page");
        checkCommentIsPresentOnPageNumber(expectedCommentValue, 1);
    }


}
