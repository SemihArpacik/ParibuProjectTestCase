package Paribu.steps;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import Paribu.page.LoginPage;
import Paribu.utilities.ConfigReader;
import Paribu.utilities.Driver;
import Paribu.utilities.ReasuableMethods;
import org.junit.Assert;
import org.openqa.selenium.interactions.Actions;

public class stepDefinitions extends Paribu.page.BasePageManager{


    LoginPage LoginsPage= new LoginPage();


    String totalValueStr;
    String totals;
    String uiTotalValueStr;

    double amount;
    double priceValue;
    double uiTotalValue;
    double totalValue;




    Actions actions = new Actions(Driver.getDriver());


/*******************************************/

    @Given("Go to the Paribu homepage.")
    public void goToTheParibuHomepage() {

        Driver.getDriver().get(ConfigReader.getProperty("url"));

        ReasuableMethods.waitFor(1);

    }

    @When("Cookies are accepted.")
    public void cookiesAreAccepted() {

        actions.moveToElement(LoginsPage.piyasalar, 50, 50).perform();
        ReasuableMethods.waitFor(1);

        LoginsPage.cerezlerKabul.click();

        ReasuableMethods.waitFor(0.5);
    }

    @When("the Markets heading is selected.")
    public void theMarketsHeadingIsSelected() {

        LoginsPage.piyasalar.click();
        ReasuableMethods.waitFor(0.5);
    }

    @When("the FAN heading is selected on the page that opens.")
    public void theFANHeadingIsSelectedOnThePageThatOpens() {
        LoginsPage.fanPage.click();
        ReasuableMethods.waitFor(0.5);
    }

    @And("Select the token in the {int}rd row on the FAN TOKEN page.")
    public void selectTheTokenInTheRdRowOnTheFANTOKENPage(int arg0) {
        LoginsPage.threeToken.click();
        ReasuableMethods.waitFor(1);
    }

    @Then("On the newly opened page, enter the Current Price in the Unit Price field on the Buy-Sell panel.")
    public void onTheNewlyOpenedPageEnterTheCurrentPriceInTheUnitPriceFieldOnTheBuySellPanel() {

        LoginsPage.currentPrice.click();

        String price = LoginsPage.currentPriceText.getAttribute("value");

        priceValue = Double.parseDouble(price.trim());

        System.out.println("tokenın fiyatı="+priceValue);

    }

    @And("Enter the number {int} as the Amount.")
    public void enterTheNumberAsTheAmount(int arg0) {
        LoginsPage.amount.sendKeys("5.00");

        amount=5.00;

        System.out.println("miktar="+amount);


        ReasuableMethods.waitFor(2);
    }

    @When("The total price is calculated.")
    public void theTotalPriceIsCalculated() {
        totalValue = amount * priceValue;

        System.out.println("fiyat * miktar="+totalValue);

        totalValueStr = String.format("%.2f", totalValue);

        System.out.println("fiyat * miktar="+totalValueStr);
    }

    @Then("The Total Price field should show the correct mathematical value.")
    public void theTotalPriceFieldShouldShowTheCorrectMathematicalValue() {
        totals = LoginsPage.total.getAttribute("value").trim();
        totals = totals.replace(",", ".");
        uiTotalValue = Double.parseDouble(totals);

        uiTotalValueStr=String.format("%.2f", uiTotalValue);

        System.out.println("total="+uiTotalValueStr);
    }

    @And("the total amount should be calculated using the entered values.")
    public void theTotalAmountShouldBeCalculatedUsingTheEnteredValues() {
        Assert.assertEquals("UI'daki toplam değer hesaplanan değerle eşleşiyor!",
                uiTotalValueStr, totalValueStr);
    }

    @And("Close the browser.")
    public void closeTheBrowser() {

        Driver.getDriver().quit();
    }


    @When("Click the Login button in the menu.")
    public void clickTheLoginButtonInTheMenu() {

        LoginsPage.login.click();
    }

    @When("The user enters their phoneNumber.")
    public void theUserEntersTheirPhoneNumber() {
LoginsPage.invalidPhoneNumber.sendKeys("+905555555555");

    }

    @Then("They enter their Password value.")
    public void theyEnterTheirPasswordValue() {
        LoginsPage.invalidPassword.sendKeys("123456");
    }

    @And("Click the Login button.")
    public void clickTheLoginButton() {
       LoginsPage.loginButton.click();
        ReasuableMethods.waitFor(2);
    }

    @And("Assert that the error message for incorrect credentials appears and verify")
    public void assertThatTheErrorMessageForIncorrectCredentialsAppearsAndVerify() {
        Assert.assertTrue(LoginsPage.errorMessage.isDisplayed());
        String message="Girdiğiniz bilgileri kontrol edin";
        Assert.assertEquals(message, LoginsPage.errorMessage.getText());
        System.out.println(LoginsPage.errorMessage.getText());


    }

    @And("Closed the browser.")
    public void closedTheBrowser() {
        Driver. getDriver().quit();
    }

    @When("It is confirmed that we are on the Paribu homepage.")
    public void itIsConfirmedThatWeAreOnTheParibuHomepage() {

        Assert.assertTrue(LoginsPage.paribuIcon.isDisplayed());
    }



}

