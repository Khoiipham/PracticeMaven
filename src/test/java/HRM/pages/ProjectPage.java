package HRM.pages;

import HRM.Helper.ValidateHelpers;
import HRM.base.BaseSetup;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class ProjectPage extends BaseSetup {
    private WebDriver driver;
    private ValidateHelpers validateHelpers;

    private By openProject = By.xpath("//span[normalize-space()='Projects']");
private By addProject = By.xpath("//a[normalize-space()='Add Project']");

    public ProjectPage(WebDriver driver) {
        this.driver = driver;
        validateHelpers = new ValidateHelpers(this.driver);
    }

    public void addProjectclick(){
        validateHelpers.CLickElement(addProject);
    }

    public void checkSearchTableColumn(int cloumn, String value){
        List<WebElement> row = driver.findElements(By.xpath(""));
        int total = row.size();
        System.out.println("the total row: "+ total);

        for(int i = 1; i < total; i++){
            WebElement elementCheck = row.get(i).findElement(By.xpath(""));

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", elementCheck);

            System.out.print(value + " - ");
            System.out.println(elementCheck.getText());
            Assert.assertTrue(elementCheck.getText().toUpperCase().contains(value.toUpperCase()), "Dòng số " + i + " không chứa giá trị tìm kiếm.");        }
    }
}
