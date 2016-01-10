package com.lukianchuk.tests;

import com.lukianchuk.drivers.Drivers;
import org.junit.Test;

public class RefreshPageTest extends Drivers {

    @Test
    public void refreshPageWithPageButtonTest() {
        System.out.println("Open Main Page");
        driver.get(MAIN_PAGE_URL);

        System.out.println("Click on Refresh Button Link");
        clickRefreshButtonLink();
    }

    @Test
    public void refreshPageWithDriverTest() {
        System.out.println("Open Main Page");
        driver.get(MAIN_PAGE_URL);

        System.out.println("Refresh Page using WebDriver");
        driver.navigate().refresh();
    }


}
