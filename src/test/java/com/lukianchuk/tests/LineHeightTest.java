package com.lukianchuk.tests;

import com.lukianchuk.drivers.Drivers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LineHeightTest extends Drivers {

    @Test
    public void checkHrLineDeviderHeightTest() {
        System.out.println("Open Main Page");
        driver.get(MAIN_PAGE_URL);

        System.out.println("Check the hrLineDevider height");
        assertEquals("Height is not as expected - 2px", "2px", hrLineDevider.getCssValue("height"));
    }


}
