import com.appsenseca.categories.Critical;
import com.appsenseca.categories.Major;
import com.appsenseca.pageobjects.EmailHomePage;
import com.appsenseca.pageobjects.EmailViewPage;
import com.appsenseca.pageobjects.SignInPage;
import com.appsenseca.util.WebUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

/**
 * Created by wojdaa on 2016-02-19.
 */
public class GmailSignInTest {

//    WebDriver driver = new FirefoxDriver();

    WebDriver driver;

    @Before
    public void setDriver(){
        String browserName = System.getenv("browser");
        if (browserName!= null && browserName.equalsIgnoreCase("Chrome")) {
            driver = new ChromeDriver();
        }else{
            driver = new FirefoxDriver();
        }
    }

    @Category({Major.class})
    @Test
    public void gmailLogInShouldBeSuccessfull(){
        // 1. Go to Gmail website
        SignInPage signInPage = WebUtil.goToSignInPage(driver);

        // 2. Fill in username
        signInPage.fillInUsername(driver, "knedel1981@gmail.com");

        // 3. Click Next button
        SignInPage nextPage = signInPage.clickNextButton(driver);

        // 4. Fill in password
        signInPage.fillInPassword(driver, "testuser1234" );

        // 5. Click Login button
        EmailHomePage emailHomePage = signInPage.clickSignIn(driver);

        // 6. Verify that user did sign in
        Assert.assertTrue("Inbox should exist", emailHomePage.isInboxExist(driver));

        // 7. Sign out
        signInPage = emailHomePage.signOut(driver);

        // 8. Verify that user did sign out
        Assert.assertTrue("Login button should exist", signInPage.isSignButtonExist(driver));

    }

    @Category({Major.class})
    @Test
    public void gmailSendAndReceiveEmailTest() {
        // 1. Click sign in
            // 1. Go to Gmail website
        SignInPage signInPage = WebUtil.goToSignInPage(driver);

            // 2. Fill in username
        signInPage.fillInUsername(driver, "knedel1981@gmail.com");

            // 3. Click Next button
        SignInPage nextPage = signInPage.clickNextButton(driver);

            // 4. Wait & Fill in password
        signInPage.fillInPassword(driver, "testuser1234" );

            // 5. Click Login button
        EmailHomePage emailHomePage = signInPage.clickSignIn(driver);

            // 6. Wait & verify that user did sign in
        Assert.assertTrue("Inbox should exist", emailHomePage.isInboxExist(driver));

        // 2. Click Compose
        EmailHomePage.clickComposeButton(driver);

        // 3. Fill in recipient
        EmailHomePage.fillInRecipient(driver, "knedel1981@gmail.com");

        // 4. Fill in subject
        final String subject = "Gmail Send Email Test";
        emailHomePage.fillInSubject(driver, subject);

        // 5. Fill in email body
        final String body = "Hello Testers! Good Morning!";
        emailHomePage.fillInEmailBody(driver, body);

        // 6. Click Send
        emailHomePage.clickSendButton(driver);

        // 7. Click Inbox again
//        emailHomePage.clickInboxWithNewEmail(driver);
        emailHomePage.clickFolderByName(driver, "Inbox");

        // 8. Click email
        EmailViewPage emailViewPage = emailHomePage.clickNewEmail(driver);

        // 9. Verify that email subject and email body is correct
        String actualSubject = emailViewPage.getEmailSubjectText(driver);
        Assert.assertEquals("Email Subject Text should be the same", subject, actualSubject);

        String actualBody = emailViewPage.getEmailBodyText(driver);
        Assert.assertEquals("Email Body Text should be the same", body, actualBody);

        // 10. Sign out
        emailHomePage.signOut(driver);
    }

    @Category({Critical.class})
    @Test
    public void gmailSendAndReceiveEmailWithAttachment() throws AWTException {
        // 1. Click sign in
            // 1. Go to Gmail website
        SignInPage signInPage = WebUtil.goToSignInPage(driver);

            // 2. Fill in username
        signInPage.fillInUsername(driver, "knedel1981@gmail.com");

            // 3. Click Next button
        SignInPage nextPage = signInPage.clickNextButton(driver);

            // 4. Wait & Fill in password
        signInPage.fillInPassword(driver, "testuser1234" );

            // 5. Click Login button
        EmailHomePage emailHomePage = signInPage.clickSignIn(driver);

            // 6. Wait & verify that user did sign in
        Assert.assertTrue("Inbox should exist", emailHomePage.isInboxExist(driver));

        // 2. Click Compose
        EmailHomePage.clickComposeButton(driver);

        // 3. Fill in recipient
        EmailHomePage.fillInRecipient(driver, "knedel1981@gmail.com");

        // 4. Fill in subject
        final String subject = "Gmail Send Email Test";
        emailHomePage.fillInSubject(driver, subject);

        // 5. Fill in email body
        final String bodyAtt = "Hello Arek! I am sending You something in attachment!";
        emailHomePage.fillInEmailBody(driver, bodyAtt);

        // 6. Click Attach file button
        EmailHomePage.clickAttachFile(driver);

        // 7. Copy path to file to be attached to clipboard
        StringSelection ss = new StringSelection("C:\\Users\\Arek\\Documents\\Arek\\Automaty\\attachment.pdf");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

        // 8. Use CTRL + V and ENTER keys to paste attachment path to explorer dialog box and submit
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(2500);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        // 9. Click Send
        emailHomePage.clickSendButton(driver);

        // 10. Click Inbox again
        emailHomePage.clickFolderByName(driver, "Inbox");

        // 11. Click email
        EmailViewPage emailViewPage = emailHomePage.clickNewEmailWithSubject(driver, subject);

        // 12. Verify that email subject and email body is correct
        String actualSubject = emailViewPage.getEmailSubjectText(driver);
        Assert.assertEquals("Email Subject Text should be the same", subject, actualSubject);

        String actualBodyAtt = emailViewPage.getEmailBodyText(driver);
        Assert.assertEquals("Email Body Text should be the same", bodyAtt, actualBodyAtt);

        // 13. Verify that email contains attachment
        boolean actualAttachment = emailViewPage.getEmailAttachmentName(driver);
        Assert.assertTrue("Email attachment should be visible", actualAttachment);

        // 13. Sign out
        emailHomePage.signOut(driver);
    }

    @Category({Critical.class})
    @Test
    public void gmailSaveDraft(){
        // 1. Go to Gmail website
        SignInPage signInPage = WebUtil.goToSignInPage(driver);

        // 2. Fill in username
        signInPage.fillInUsername(driver, "knedel1981@gmail.com");

        // 3. Click Next button
        SignInPage nextPage = signInPage.clickNextButton(driver);

        // 4. Fill in password
        signInPage.fillInPassword(driver, "testuser1234" );

        // 5. Click Login button
        EmailHomePage emailHomePage = signInPage.clickSignIn(driver);

        // 6. Verify that user did sign in
        Assert.assertTrue("Inbox should exist", emailHomePage.isInboxExist(driver));

        // 7. Click Compose
        EmailHomePage.clickComposeButton(driver);

        // 8. Fill in recipient
        EmailHomePage.fillInRecipient(driver, "knedel1981@gmail.com");

        // 9. Fill in subject
        final String subject = "Gmail Send Email Test";
        emailHomePage.fillInSubject(driver, subject);

        // 10. Fill in email body
        final String body = "Hello Testers! Good Morning!";
        emailHomePage.fillInEmailBody(driver, body);

        // 11. Save draft
        EmailHomePage.clickSaveAndExitButton(driver);

        // 12. Open Drafts
        EmailHomePage.clickDraftsButton(driver);

        // 13. Verify that draft have been saved
        Assert.assertTrue("Draft should have been saved", EmailHomePage.isDraftExist(driver));


    }


//    @After
//    public void tearDown() { driver.quit();}

}
