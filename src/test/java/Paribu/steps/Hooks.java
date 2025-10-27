package Paribu.steps;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import Paribu.utilities.Driver;
import org.junit.After;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {


    @After
    public void tearDown(Scenario scenario){
        final byte[] screenshot=((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
        if (scenario.isFailed()) {
            scenario.attach(screenshot, "image/png","Screenshots");
        }
        Driver.CloseDriver();
    }

    @Before(value =" not @api")
    public void setUp() {
        // Her senaryo başlamadan önce driver başlatılır
        Driver.getDriver();
    }

    @After
    public void tearDown() {
        // Her senaryo bittikten sonra driver kapatılır
        Driver.CloseDriver();
    }



}
