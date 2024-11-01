package HRM.pages;


import HRM.Helper.ValidateHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignInPage {
    private WebDriver driver;
    private DashboardPage dashboardPage;
    private ProjectPage projectPage;
    private ValidateHelpers validateHelpers;
    private WebDriverWait wait;

    private By emailInput = By.xpath("//input[@id='iusername']");
    private By passInput = By.id("ipassword");
    private By signInBtn = By.xpath("//button[@type='submit']");

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        validateHelpers = new ValidateHelpers(driver);
    }

    public DashboardPage signIn(String email, String password) throws InterruptedException {
        validateHelpers.waitForPageLoaded();
       // Assert.assertTrue(validateHelpers.verifySignInPageTitle(),"Wrong page");
        validateHelpers.SetText(emailInput,email);
        validateHelpers.SetText(passInput,email);
        validateHelpers.CLickElement(signInBtn);
        Thread.sleep(4000);
      //  Assert.assertTrue(validateHelpers.verifyUrl("/desk"),"not DashboardPage");
        return new DashboardPage(driver);
    }

}
