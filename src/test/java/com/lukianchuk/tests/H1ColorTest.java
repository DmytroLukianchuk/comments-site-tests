package com.lukianchuk.tests;

import com.lukianchuk.drivers.Drivers;
import org.junit.Test;

public class H1ColorTest extends Drivers {

    @Test
    public void CheckH1ColorTest() {
        System.out.println("Open Main Page");
        driver.get(MAIN_PAGE_URL);

        System.out.println("Check Color of h1");
        h1CommentsTextGetColor();
    }
}
