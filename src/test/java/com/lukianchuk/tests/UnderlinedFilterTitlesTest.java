package com.lukianchuk.tests;

import com.lukianchuk.drivers.Drivers;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;

public class UnderlinedFilterTitlesTest extends Drivers {

    public void checkTitleIsUnderlined(WebElement webElement) {
        assertEquals("Element " + webElement + " is not underlined", "underline",
                webElement.getCssValue("text-decoration"));
    }

    @Test
    public void checkNumberCommentTextActiveIsUnderlined() {
        System.out.println("Open Main Page");
        driver.get(MAIN_PAGE_URL);

        System.out.println("Check Number title is underlined");
        checkTitleIsUnderlined(numberLinkColumnFilter);

        System.out.println("Check CommentText title is underlined");
        checkTitleIsUnderlined(commentTextLinkColumnFilter);

        System.out.println("Check Active title is underlined");
        checkTitleIsUnderlined(activeLinkColumnFilter);
    }
}
