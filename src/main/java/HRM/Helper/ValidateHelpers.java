package HRM.Helper;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.JavascriptExecutor;


import java.time.Duration;
import java.util.List;


public class ValidateHelpers {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private Actions actions;

    public ValidateHelpers(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        js = (JavascriptExecutor) driver;
        actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public void SetText(By element, String valueText) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        driver.findElement(element).clear();
        driver.findElement(element).sendKeys(valueText);
    }

    public void CLickElement(By element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        driver.findElement(element).click();
    }


    public String getSignInPageTitle() {
        String pageTitle = this.driver.getTitle();
       // System.out.println(pageTitle+"1");
        return pageTitle;
    }

    public boolean verifySignInPageTitle() {
        String expectedTitle = "HRSALE | Log in";
        return getSignInPageTitle().equals(expectedTitle);
    }

    public boolean verifySignInPageText(WebElement element) {
        String pageText = element.getText();
        String expectedPageText = "Forgot Username/Password?";
        return pageText.contains(expectedPageText);
    }


    public boolean verifyUrl(String url)
    {
        System.out.println(driver.getCurrentUrl());
        System.out.println(url);

        return driver.getCurrentUrl().contains(url); //True/False
    }

    public boolean verifyElementText(By element, String textValue){
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element).getText().equals(textValue); //True/False
    }

    public boolean verifyElementExist(By element){
        //Tạo list lưu tất cả đối tượng WebElement
        List<WebElement> listElement = driver.findElements(element);

        int total = listElement.size();

        if(total > 0){
            return true;
        }

        return false;
    }

    public void clickElementJs(By element){
        waitForPageLoaded();
        driver.findElement(element);
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        js.executeScript("arguments[0].click();", driver.findElement(element));
    }

    public void movetoElement(By element){
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        actions.moveToElement(driver.findElement(element)).perform();
    }

    public void doubleClick(By element){
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        actions.doubleClick(driver.findElement(element)).build().perform();
    }

    public void rightClick(By element){
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        actions.contextClick(driver.findElement(element)).build().perform();
    }

    //Handle Dropdown
    public void SelectOptionByText(By element, String text) {
        Select select = new Select(driver.findElement(element));
        select.selectByVisibleText(text);
    }

    public void SelectOptionByValue(By element, String value) {
        Select select = new Select(driver.findElement(element));
        select.selectByValue(value);
    }

    public void SelectOptionByIndex(By element, int index) {
        Select select = new Select(driver.findElement(element));
        select.selectByIndex(index);
    }

    public void verifyOptionTotal(By element, int total){
        Select select = new Select(driver.findElement(element));
        //List<WebElement> options = select.getOptions();
       // System.out.println("Total options: " + options.size());
        //for(WebElement option : options){
        //    System.out.println(option.getText());
        //}
        Assert.assertEquals(total,select.getOptions().size());
    }

    // Wait

    public void waitForPageLoaded(){
        // wait for jQuery to loaded
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        // wait for Javascript to loaded
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };

        try {


            Duration timeoutWaitForPageLoaded = Duration.ofSeconds(30);
            wait = new WebDriverWait(driver, timeoutWaitForPageLoaded);
            wait.until(jQueryLoad);
            wait.until(jsLoad);
        } catch (Throwable error) {
            Assert.fail("Quá thời gian load trang.");
        }

    }
}
