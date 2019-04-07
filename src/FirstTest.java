import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception{

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity","main.MainActivity");
        capabilities.setCapability("app","C:/Users/nsochina/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }
    @After
    public void tearDown(){
        driver.quit();

    }
    @Test
    public void FirstTest() {

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find SearchLine",
                5);

        waitForElementPresent(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Cannot find text 'Search…'",
                5
        );
    }

    @Test
    public void testCancelSearch(){
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find SearchLine",
                5);
        waitForElementAndSendKeys (
                By.xpath("//*[contains(@text,'Search…')]"),
                "Apple",
                "Cannot enter Apple",
                15
        );
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Apple TV']"),
                "Cannot find Apple TV",
                5
                        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X ",
                5);

        waitForElementPresent(
                By.id("org.wikipedia:id/search_empty_image"),
                "Not search_empty_image",
                5
        );

    }

    private WebElement waitForElementPresent(By by,String error_message, long timoutInSec)
    {
        WebDriverWait wait = new WebDriverWait(driver, timoutInSec);
        wait.withMessage(error_message+"\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    private WebElement waitForElementPresent(By by, String error_message)
    {
        return waitForElementPresent(by, error_message, 5);

    }
    private WebElement waitForElementAndClick(By by,String error_message, long timoutInSec)
    {
        WebElement element = waitForElementPresent(by, error_message, timoutInSec);
        element.click();
        return element;
    }
    private WebElement waitForElementAndSendKeys(By by, String key_name, String error_message, long timoutInSec)
    {
        WebElement element = waitForElementPresent(by, error_message, timoutInSec);
        element.sendKeys(key_name);
        return element;
    }
    private boolean waitForElementNotPresent(By by, String error_message, long timoutInSec)
    {
        WebDriverWait wait = new WebDriverWait(driver, timoutInSec);
        wait.withMessage(error_message+'\n');
        return wait.until(
            ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }




}
