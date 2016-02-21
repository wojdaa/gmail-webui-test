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
        // 1. Go to Gmail website
        driver.get("https://gmail.com");

        // 2. Fill in username
        WebElement usernameTextbox = driver.findElement(By.id("Email"));
        usernameTextbox.clear();
        usernameTextbox.sendKeys("knedel1981@gmail.com");

        // 3. Click Next button
        WebElement nextButton = driver.findElement(By.id("next"));
        nextButton.click();

        // 4. Wait & Fill in password
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Passwd")));

        WebElement passwordTextbox = driver.findElement(By.name("Passwd"));
        passwordTextbox.sendKeys("testuser1234");

        // 5. Click Login button
        WebElement loginButton = driver.findElement(By.xpath(".//*[@id='signIn']"));
        loginButton.click();

        // 6. Wait & verify that user did sign in
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Inbox")));
        Assert.assertTrue("Inbox should exist", driver.findElements(By.partialLinkText("Inbox")).size() >0);

        // 7. Sign out
        WebElement userIcon = driver.findElement(By.cssSelector("span[class='gb_Za gbii']"));
        userIcon.click();
        WebElement logoutButton = driver.findElement(By.id("gb_71"));
        logoutButton.click();

        // 8. Wait & verify that user did sign out
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("signIn")));
        Assert.assertTrue("Login button should exist", driver.findElements(By.id("signIn")).size() >0);
    }

    @Test
    public void gmailSendAndReceiveEmailTest() {
        // 1. Click sign in
            // 1. Go to Gmail website
        driver.get("https://gmail.com");

            // 2. Fill in username
        WebElement usernameTextbox = driver.findElement(By.id("Email"));
        usernameTextbox.clear();
        usernameTextbox.sendKeys("knedel1981@gmail.com");

            // 3. Click Next button
        WebElement nextButton = driver.findElement(By.id("next"));
        nextButton.click();

            // 4. Wait & Fill in password
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Passwd")));

        WebElement passwordTextbox = driver.findElement(By.name("Passwd"));
        passwordTextbox.sendKeys("testuser1234");

            // 5. Click Login button
        WebElement loginButton = driver.findElement(By.xpath(".//*[@id='signIn']"));
        loginButton.click();

            // 6. Wait & verify that user did sign in
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Inbox")));
        Assert.assertTrue("Inbox should exist", driver.findElements(By.partialLinkText("Inbox")).size() >0);

        // 2. Click Compose
        WebElement composeButton = driver.findElement(By.cssSelector("div[role='button'][gh='cm']"));
        composeButton.click();

        // 3. Fill in recipient
        WebElement receipentTextArea = driver.findElement(By.cssSelector("textarea[name='to']"));
        receipentTextArea.clear();
        receipentTextArea.sendKeys("knedel1981@gmail.com");

        // 4. Fill in subject
        WebElement subjectTextArea = driver.findElement(By.cssSelector("input[name='subjectbox']"));
        final String subject = "Gmail Send Email Test";
        subjectTextArea.clear();
        subjectTextArea.sendKeys(subject);

        // 5. Fill in email body
        WebElement bodyTextArea = driver.findElement(By.cssSelector("div[role='textbox']"));
        final String body = "Hello Testers! Good Morning!";
        bodyTextArea.clear();
        bodyTextArea.sendKeys(body);

        // 6. Click Send
        WebElement sendButton = driver.findElement(By.cssSelector("div[aria-label*=\"Send\"]"));
        sendButton.click();

        // 7. Click Inbox again
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Inbox")));
        WebElement inboxLinkage = driver.findElement(By.partialLinkText("Inbox"));
        inboxLinkage.click();

        // 8. Click email
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='y6'] span[id] b")));
        WebElement newEmail = driver.findElement(By.cssSelector("div[class='y6'] span[id] b"));
        newEmail.click();

        // 9. Verify that email subject and email body is correct
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2[class='hP']")));
        WebElement subjectArea = driver.findElement(By.cssSelector("h2[class='hP']"));
        Assert.assertEquals("Email Subject Text should be the same", subject, subjectArea.getText());
        WebElement bodyArea = driver.findElement(By.cssSelector("div[class='nH aHU'] div[dir='ltr']"));
        Assert.assertEquals("Email Body Text should be the same", body, bodyArea.getText());

        // 10. Sign out
        WebElement userIcon = driver.findElement(By.cssSelector("span[class='gb_Za gbii']"));
        userIcon.click();
        WebElement logoutButton = driver.findElement(By.id("gb_71"));
        logoutButton.click();
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
