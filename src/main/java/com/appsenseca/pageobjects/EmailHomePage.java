package com.appsenseca.pageobjects;

import com.appsenseca.util.WebUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

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

    public void clickInboxWithNewEmail(WebDriver driver) {
        WebUtil.waitForElementVisible(driver, By.partialLinkText("Inbox ("));
        WebUtil.click(driver, By.partialLinkText("Inbox"));
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
}
