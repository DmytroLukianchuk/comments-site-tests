package com.lukianchuk.tests;

import com.lukianchuk.drivers.Drivers;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FilteringByCatActiveTest extends Drivers {

    @Test //#1 - Cat + Status
    public void chooseCetegoryNameAndStatusFilteringTest() {
        System.out.println("Open Main Page");
        driver.get(MAIN_PAGE_URL);

        System.out.println("Choose " + CATEGORY_NAME_DROPDOWN + " from Category Name DropDown");
        chooseCategoryNameDropDown(CATEGORY_NAME_DROPDOWN);

        System.out.println("Choose " + STATUS_DROPDOWN + " from Status DropDown");
        chooseStatusNameDropDown(STATUS_DROPDOWN);

        System.out.println("Click Apply Button");
        applyButton.click();

        System.out.println("Check that Active Column contains " + STATUS_DROPDOWN + " only value");
        assertTrue(checkActiveColumnValue(STATUS_DROPDOWN));

        System.out.println("Check that all Comments line have Categories " + CATEGORY_NAME_DROPDOWN);
        assertTrue(checkCategoriesHaveFilteringValue(CATEGORY_NAME_DROPDOWN));
    }

    @Test //#2 - Cat
    public void chooseCetegoryNameFilteringTest() {
        System.out.println("Open Main Page");
        driver.get(MAIN_PAGE_URL);

        System.out.println("Choose " + CATEGORY_NAME_DROPDOWN + " from Category Name DropDown");
        chooseCategoryNameDropDown(CATEGORY_NAME_DROPDOWN);

        System.out.println("Click Apply Button");
        applyButton.click();

        System.out.println("Check that all Comments line have Categories " + CATEGORY_NAME_DROPDOWN);
        assertTrue(checkCategoriesHaveFilteringValue(CATEGORY_NAME_DROPDOWN));

    }

    @Test //#3 - Status
    public void chooseStatusFilteringTest() {
        System.out.println("Open Main Page");
        driver.get(MAIN_PAGE_URL);

        System.out.println("Choose " + STATUS_DROPDOWN + " from Status DropDown");
        chooseStatusNameDropDown(STATUS_DROPDOWN);

        System.out.println("Click Apply Button");
        applyButton.click();

        System.out.println("Check that all Comments line have Status as " + STATUS_DROPDOWN);
        assertTrue(checkActiveColumnValue(STATUS_DROPDOWN));

    }


}
