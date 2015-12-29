package com.lukianchuk.drivers;

import org.junit.After;
import org.junit.Test;

public class Tests extends Drivers {

    @After
    public void tearDown() throws Exception {
        driver.close();

    }

    @Test
    public void createComment() {
        System.out.println("Go to http://comments.azurewebsites.net/");
        driver.get("http://comments.azurewebsites.net/");

        System.out.println("Click New... button");
        clickNewCommentButtonCheckUrl();

        System.out.println("Check Comment Text field value");
        findCommentTextField().sendKeys("111");

        clickSaveButtonLink();
        checkErrorCommentNotHaveCategory();


    }

}
