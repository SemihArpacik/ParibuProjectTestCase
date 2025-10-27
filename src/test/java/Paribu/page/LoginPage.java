package Paribu.page;



import Paribu.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage() {


        PageFactory.initElements(Driver.getDriver(), this);;
    }


    @FindBy(xpath = "//button[normalize-space(.)='Çerezleri kabul et']")
    public WebElement cerezlerKabul;


    @FindBy(xpath = "//a[normalize-space(.)='Piyasalar']")
    public WebElement piyasalar;


    @FindBy(css = "a.p-segmented-control__item[href=\"/markets?group=fantoken_chz\"]")
    public WebElement fanPage;

    @FindBy(xpath = "(//section[@class='market-list mt-12']/a)[3]")
    public WebElement threeToken;

    @FindBy(xpath = "//span[@class='p-text-input__append']")
    public WebElement currentPrice;

    @FindBy(xpath = "//input[@id='price']")
    public WebElement currentPriceText;

    @FindBy(xpath = "//input[@id='amount']")
    public WebElement amount;

    @FindBy(xpath = "//input[@id='total']")
    public WebElement total;

    @FindBy(xpath = "//a[text()='Giriş yap']")
    public WebElement login;

    @FindBy(xpath = "//input[@id='phoneNumber']")
    public WebElement invalidPhoneNumber;

    @FindBy(xpath = "//input[@id='password']")
    public WebElement invalidPassword;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement loginButton;

    @FindBy(xpath = "//p[text()='Girdiğiniz bilgileri kontrol edin']")
    public WebElement errorMessage;


    @FindBy(xpath = "//a[@class='router-link-active router-link-exact-active place-items-center header-logo']\n")
    public WebElement paribuIcon;














}