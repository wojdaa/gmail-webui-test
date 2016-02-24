import com.appsenseca.categories.Critical;
import com.appsenseca.categories.Major;
import com.appsenseca.pageobjects.EmailHomePage;
import com.appsenseca.pageobjects.EmailViewPage;
import com.appsenseca.pageobjects.SignInPage;
import com.appsenseca.util.WebUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by wojdaa on 2016-02-19.
 */
public class GmailSignInTest {

    WebDriver driver = new FirefoxDriver();

    @Category({Critical.class})
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
        emailHomePage.clickInboxWithNewEmail(driver);

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

    @After
    public void tearDown() { driver.quit();}

}
