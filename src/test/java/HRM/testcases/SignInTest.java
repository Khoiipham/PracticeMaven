package HRM.testcases;

import HRM.Helper.ExcelHelpers;
import HRM.Helper.ValidateHelpers;
import HRM.base.BaseSetup;

import HRM.pages.DashboardPage;
import HRM.pages.ProjectPage;
import HRM.pages.SignInPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SignInTest extends BaseSetup {
    private WebDriver driver;
    public SignInPage signInPage;
    public ValidateHelpers validateHelpers;
    public DashboardPage dashboardPage;
    public ProjectPage projectPage;
    public ExcelHelpers excelHelpers;



    @BeforeClass
    public void setupBrowser() {
      //  driver = getDriver();
        driver = new BaseSetup().setupDriver("Chrome");
        validateHelpers = new ValidateHelpers(driver);
        excelHelpers = new ExcelHelpers();
        }

    @Test (priority = 1)
    public void testSignIn() throws InterruptedException {
        driver.get("https://app.hrsale.com/erp/projects-grid");
        signInPage = new SignInPage(driver);
        System.out.println(driver);
        dashboardPage = signInPage.signIn("kelly.flynn","kelly.flynn");
        System.out.println(validateHelpers.getSignInPageTitle());

    }

    @Test(priority = 2)
    public void openProjectTest() throws InterruptedException {
        projectPage = dashboardPage.openProjectPage();
        Thread.sleep(4000);
        Assert.assertTrue(validateHelpers.verifyUrl("/project"),"this is not ProjectPage");
    }

    @Test(priority = 3)
    public void addProjectTest() throws InterruptedException {
        projectPage.addProjectclick();

    }

    @Test
    public void TestExcelData() throws Exception {
        excelHelpers.setExcelFile("src/test/resources/Book1.xlsx","Sheet1");
        signInPage = new SignInPage(driver);
        driver.get("https://app.hrsale.com/erp/login");
        //gan gia tri tu file excel
        signInPage.signIn(excelHelpers.getCellData("username",1),excelHelpers.getCellData("password",1));

        //ghi gia tri vao file excel
        excelHelpers.setCellData("khoi",3,1);
    }

    @AfterClass
    public void tearDown() throws Exception {
        Thread.sleep(2000);
        driver.quit();
    }
}
