package com.lukianchuk.tests;

import com.lukianchuk.drivers.Drivers;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;

public class DuplicateCommentTest extends Drivers {

// #1 Positive > Click on Duplicate > Check URL & Copy of + Comment
// #2 Negative > Check Alert not chosen any Comment
// #3 Negative > Check Alert not chosen any Comment

    @Before
    public void setUp() throws Exception {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @Test
    public void checkDuplicateCommentLink() {
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

        System.out.println("Check Comment Text has Copy of" + currentCommentValue);
        assertEquals("Comment Text Value is incorrect", "Copy of" + currentCommentValue,
                getCommentsValueOnDuplicatePage());

    }



}
