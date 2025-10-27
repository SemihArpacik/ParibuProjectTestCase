package Paribu.utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ReasuableMethods {

    public static void waitFor(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }





    public static void waitFor(double seconds) {
        try {
            Thread.sleep((long) (seconds * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }







}
