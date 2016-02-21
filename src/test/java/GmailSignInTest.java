import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by wojdaa on 2016-02-19.
 */
public class GmailSignInTest {

    WebDriver driver = new FirefoxDriver();

    @Test
    public void gmailLogInShouldBeSuccessfull(){
        //Go to Gmail website
        driver.get("https://gmail.com");

        //Fill in username
        WebElement usernameTextbox = driver.findElement(By.id("Email"));
        usernameTextbox.clear();
        usernameTextbox.sendKeys("knedel1981@gmail.com");

        //click Next button
        WebElement nextButton = driver.findElement(By.id("next"));
        nextButton.click();

        //Wait till page opens
//          driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Passwd")));

        //Fill in password
        WebElement passwordTextbox = driver.findElement(By.name("Passwd"));
        passwordTextbox.sendKeys("testuser1234");

        //click Login button
        WebElement loginButton = driver.findElement(By.xpath(".//*[@id='signIn']"));
        loginButton.click();

        //Wait till page opens
//          driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Odebrane")));

        //verify that user did sign in
        Assert.assertTrue("Inbox should exist", driver.findElements(By.partialLinkText("Odebrane")).size() >0);

        //sign out
//          WebElement userIcon = driver.findElement(By.xpath(".//*[@id='gb']/div[1]/div[1]/div[2]/div[4]"));
        WebElement userIcon = driver.findElement(By.cssSelector("span[class='gb_Za gbii']"));
        userIcon.click();
        WebElement logoutButton = driver.findElement(By.id("gb_71"));
        logoutButton.click();

        //wait & verify that user did sign out
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("signIn")));

        Assert.assertTrue("Login button should exist", driver.findElements(By.id("signIn")).size() >0);
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
