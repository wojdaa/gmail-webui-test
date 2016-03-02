package com.appsenseca.pageobjects;

import com.appsenseca.util.WebUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


/**
 * Created by wojdaa on 2016-02-22.
 */
public class EmailHomePage {

    public SignInPage signOut(WebDriver driver) {
        WebUtil.click(driver, By.cssSelector("span[class='gb_Za gbii']"));
        WebUtil.click(driver,By.id("gb_71"));
        WebUtil.waitForElementVisible(driver,By.name("signIn"));

        return PageFactory.initElements(driver, SignInPage.class);
    }

    public boolean isInboxExist(WebDriver driver) {
        return WebUtil.isElementExist(driver, By.partialLinkText("Inbox"));
    }

    public static void clickComposeButton(WebDriver driver) {
        WebUtil.click(driver, By.cssSelector("div[role='button'][gh='cm']"));
    }

    public static void fillInRecipient(WebDriver driver, String s) {
        WebUtil.waitForElementVisible(driver, By.cssSelector("textarea[name='to']"));
        WebUtil.clearAndSendKeys(driver, By.cssSelector("textarea[name='to']"), "knedel1981@gmail.com");
    }

    public void fillInSubject(WebDriver driver, String subject) {
        WebUtil.clearAndSendKeys(driver, By.cssSelector("input[name='subjectbox']"), subject);
    }

    public void fillInEmailBody(WebDriver driver, String body) {
        WebUtil.clearAndSendKeys(driver, By.cssSelector("div[role='textbox']"), body);
    }

    public void clickSendButton(WebDriver driver) {
        WebUtil.click(driver, By.cssSelector("div[aria-label*='Send']"));
    }

    public EmailViewPage clickNewEmail(WebDriver driver) {
        WebUtil.waitForElementVisible(driver, By.cssSelector("div[class='y6'] span[id] b"));
        WebUtil.click(driver, By.cssSelector("div[class='y6'] span[id] b"));

        return PageFactory.initElements(driver, EmailViewPage.class);
    }

    public static void clickAttachFile(WebDriver driver) {
        WebUtil.waitForElementVisible(driver, By.cssSelector("div[class='a1 aaA aMZ']"));
        WebUtil.click(driver, By.cssSelector("div[class='a1 aaA aMZ']"));
    }

    public void clickFolderByName(WebDriver driver, String folder) {
        WebUtil.waitForElementVisible(driver, By.partialLinkText(folder));
        WebUtil.click(driver, By.partialLinkText(folder));
    }

    public EmailViewPage clickNewEmailWithSubject(WebDriver driver, String subject) {
        WebUtil.waitForElementVisible(driver, By.cssSelector("div[class='y6'] span[id] b"));

        boolean isEmailFound = false;
        // get all new emails
        List<WebElement> emails = driver.findElements(By.cssSelector("div[class='y6'] span[id] b"));

        for (WebElement email : emails) {
            // if email found, click it and stop loop
            if (email.getText().equals(subject)) {
                WebUtil.click(driver, email);
                isEmailFound = true;
                break;
            }
        }
        // if email cannot be found, refresh the page and search again
        if (!isEmailFound) {
            driver.navigate().refresh();
            WebUtil.tryDismissAlert(driver);
            clickNewEmailWithSubject(driver, subject);
        }

        return PageFactory.initElements(driver, EmailViewPage.class);
    }
}
