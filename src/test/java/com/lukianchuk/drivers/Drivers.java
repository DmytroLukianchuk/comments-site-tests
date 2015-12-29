package com.lukianchuk.drivers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static junit.framework.Assert.assertEquals;

public class Drivers {
    public WebDriver driver = new FirefoxDriver();

    public void clickNewCommentButtonCheckUrl() {
        driver.findElement(By.xpath("//input[@value='New...']")).click();
        assertEquals("URL is incorrect", "http://comments.azurewebsites.net/Editor/NewComment",
                driver.getCurrentUrl());
    }

    public void clickSaveButtonLink() {
        driver.findElement(By.xpath("//input[@value='Save']")).click();
    }

    public void clickSaveAndReturnButtonLink() {
        driver.findElement(By.xpath("//input[@value='Save & Return']")).click();
    }

    public void clickRefreshButtonLink() {
        driver.findElement(By.xpath("//a[@href='/Editor/Refresh?idObj=0&baseid=0']")).click();
    }

    public void clickReturnButtonLink() {
        driver.findElement(By.xpath("//a[@href='/']")).click();
    }

    // ERRORS ON COMMENTS PAGE

    public void checkErrorCommentNotHaveCategory() {
        String errorText = driver.findElement(By.id("errorfield")).getText();
        assertEquals("Error is not appeared / correct", "Comments must be assigned to at least one category. " +
                "Please select a category before trying to save this comment again.", errorText);
    }

    public void checkErrorCommentFieldRequired() {
        String errorText = driver.findElement(By.xpath("//span[@htmlfor='Text']")).getText();
        assertEquals("Error is not appeared / correct", "The Comment Text field is required.",
                errorText);
    }

    public WebElement findCommentTextField() {
        return driver.findElement(By.name("Text"));
    }


}
