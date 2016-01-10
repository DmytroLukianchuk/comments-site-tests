package com.lukianchuk.tests;

import com.lukianchuk.drivers.Drivers;
import org.junit.Test;

public class OrderingTest extends Drivers {

    @Test //#1
    public void checkASCOrderingByNumberTest() {
        System.out.println("Open Main Page");
        driver.get(MAIN_PAGE_URL);

        System.out.println("Click on Numbers Column odd times");
        clickNumberORCommentORActiveFilterCheckUrl(3, numberLinkColumnFilter, false);
    }

    @Test //#2
    public void checkDESCOrderingByNumberTest() {
        System.out.println("Open Main Page");
        driver.get(MAIN_PAGE_URL);

        System.out.println("Click on Numbers Column even times");
        clickNumberORCommentORActiveFilterCheckUrl(2, numberLinkColumnFilter, false);
    }

    @Test //#3
    public void checkDESCOrderingByCommentTextTest() {
        System.out.println("Open Main Page");
        driver.get(MAIN_PAGE_URL);

        System.out.println("Click on Comment Text Column odd times");
        clickNumberORCommentORActiveFilterCheckUrl(3, commentTextLinkColumnFilter, true);
        System.out.println("Current URL is " + driver.getCurrentUrl());
    }

    @Test //#4
    public void checkASCOrderingByCommentTextTest() {
        System.out.println("Open Main Page");
        driver.get(MAIN_PAGE_URL);

        System.out.println("Click on Comment Text Column even times");
        clickNumberORCommentORActiveFilterCheckUrl(2, commentTextLinkColumnFilter, true);
        System.out.println("Current URL is " + driver.getCurrentUrl());

    }

    @Test //#5
    public void checkASCOrderingByActiveTest() {
        System.out.println("Open Main Page");
        driver.get(MAIN_PAGE_URL);

        System.out.println("Click on Active Column odd times");
        clickNumberORCommentORActiveFilterCheckUrl(3, activeLinkColumnFilter, false);
    }

    @Test //#6
    public void checkDESCOrderingByActiveTest() {
        System.out.println("Open Main Page");
        driver.get(MAIN_PAGE_URL);

        System.out.println("Click on Active Column even times");
        clickNumberORCommentORActiveFilterCheckUrl(2, activeLinkColumnFilter, false);
    }


}
