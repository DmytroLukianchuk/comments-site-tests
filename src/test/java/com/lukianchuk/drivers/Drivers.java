package com.lukianchuk.drivers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static junit.framework.Assert.assertEquals;

public class Drivers {
    public static final String MAIN_PAGE_URL = "http://comments.azurewebsites.net/";
    public static final String NEWCOMMENT_PAGE_URL = "http://comments.azurewebsites.net/Editor/NewComment";
    public static final String VALID_COMMENT_TEXT = "WWW";
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



    public WebDriver driver = new FirefoxDriver();

    // MAIN PAGE DRIVERS
    public void clickNewCommentButtonCheckUrl() {
        driver.findElement(By.xpath("//input[@value='New...']")).click();
        assertEquals("URL is incorrect", "http://comments.azurewebsites.net/Editor/NewComment",
                driver.getCurrentUrl());
    }


    // NEWCOMMENT PAGE DRIVERS
    public void clickSaveButtonLink() {
        driver.findElement(By.xpath("//input[@value='Save']")).click();
    }

    public void clickSaveAndReturnButtonLink() {
        driver.findElement(By.xpath("//input[@value='Save & Return']")).click();
    }

    public void clickRefreshButtonLink() {
        driver.findElement(By.xpath("//a[@href='/Editor/Refresh?idObj=0&baseid=0']")).click();
    }

    public WebElement findCommentTextField() {
        return driver.findElement(By.name("Text"));
    }

    public WebElement findNumberInput() {
        return driver.findElement(By.xpath("//input[@id='Number']"));

    }

    public WebElement findCheckBoxN(int catN) {
        return driver.findElement(By.xpath("//input[@id='Categories' and @value='" + catN + "']"));
    }

    public WebElement findMoveRightActionButton() {
        return driver.findElement(By.xpath("//input[@name = 'CurSelect']"));
    }


    // GENERAL DRIVERS
    public void clickReturnButtonLink() {
        driver.findElement(By.xpath("//a[@href='/']")).click();
    }


    // ERRORS ON COMMENTS PAGE
    public void checkErrorCommentNotHaveCategory() {
        String errorText = driver.findElement(By.id("errorfield")).getText();
        assertEquals("Error is not appeared / correct", COMMENT_MUST_HAVE_GROUP_ERROR, errorText);
    }

    public void checkErrorCommentFieldRequired() {
        String errorText = driver.findElement(By.id("errorfield")).getText();
        assertEquals("Error is not appeared / correct", COMMENT_TEXT_EMPTY_ERROR,
                errorText);
    }

    public void checkErrorNumberFieldNotValid() {
        String errorText = driver.findElement(By.id("errorfield")).getText();
        assertEquals("Error is not appeared / correct", NUMBER_FIELD_VALUE_ERROR,
                errorText);
    }

    public void checkcheckErrorNotCorrectCommentFieldValue() {
        String errorText = driver.findElement(By.id("errorfield")).getText();
        assertEquals("Error is not appeared / correct", COMMENT_TEXT_NOT_ALPHA_NUM_ERROR,
                errorText);
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




}
