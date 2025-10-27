package Paribu.page;

import Paribu.utilities.ReasuableMethods;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BasePageManager {
    public void clickTextInList(List<WebElement> list, String text) {
        ReasuableMethods.waitFor(0.5);
        if (text.equals(""))
            return;
        for (WebElement webElement : list) {
            if (webElement.getAttribute("innerText").contains(text)) {
                webElement.click();
                return;
            }
        }
        try {
            throw new Exception("text is not in list!!!");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Please check list locator!!!");

        }

    }

}