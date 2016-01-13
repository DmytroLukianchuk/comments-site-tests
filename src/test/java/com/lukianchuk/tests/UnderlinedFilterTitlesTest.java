package com.lukianchuk.tests;

import com.lukianchuk.drivers.Drivers;
import org.junit.Test;

public class UnderlinedFilterTitlesTest extends Drivers {

    @Test
    public void checkNumberCommentTextActiveIsUnderlinedTest() {
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
