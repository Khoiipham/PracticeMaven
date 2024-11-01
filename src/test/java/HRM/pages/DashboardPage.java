package HRM.pages;

import HRM.Helper.ValidateHelpers;
import HRM.base.BaseSetup;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BaseSetup {
    private WebDriver driver;
    private ValidateHelpers validateHelpers;

    private By openProject = By.xpath("//span[normalize-space()='Projects']");


    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        validateHelpers = new ValidateHelpers(this.driver);
    }

    public  ProjectPage openProjectPage(){
        validateHelpers.CLickElement(openProject);
        validateHelpers.waitForPageLoaded();
        return  new ProjectPage(driver);
    }
}
