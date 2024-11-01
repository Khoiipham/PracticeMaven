package HRM.testcases;

import HRM.Ulities.PropertiesFile;
import HRM.base.BaseSetup;
import HRM.pages.SignInPage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class PropertiesTest {

    private WebDriver driver;
    private SignInPage signInPage;

    @BeforeClass
    public void createDriver() {
        // Gọi hàm để khởi tạo file properties
        PropertiesFile.setPropertiesFile();

        // Đọc data từ file properties với key là "browser"
        driver = new BaseSetup().setupDriver(PropertiesFile.getPropValue("browser"));
    }

    @Test
    public void signinCRM(ITestContext result) throws InterruptedException, IOException {
        signInPage = new SignInPage(driver);
        driver.get("https://app.hrsale.com/erp/login");

        // Đọc data từ file properties
        signInPage.signIn(PropertiesFile.getPropValue("email"), PropertiesFile.getPropValue("password"));

        // Tạo tham chiếu của TakesScreenshot với driver hiện
        TakesScreenshot ts = (TakesScreenshot) driver;
// Gọi hàm capture screenshot - getScreenshotAs
        File source = ts.getScreenshotAs(OutputType.FILE);
        File theDir = new File("./Screenshots/");
        if (!theDir.exists()) {
            theDir.mkdirs();
        }
        FileHandler.copy(source, new File("./Screenshots/" + result.getName() + ".png"));
        System.out.println("Screenshot taken: " + result.getName());
    }

    @AfterClass
    public void closeDriver() {
        driver.quit();
    }
}

