package com.appsenseca.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by wojdaa on 2016-02-22.
 */
public class EmailHomePage {

    public SignInPage signOut(WebDriver driver) {
        WebElement userIcon = driver.findElement(By.cssSelector("span[class='gb_Za gbii']"));
        userIcon.click();
        WebElement logoutButton = driver.findElement(By.id("gb_71"));
        logoutButton.click();

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("signIn")));

        return PageFactory.initElements(driver, SignInPage.class);
    }

    public boolean isInboxExist(WebDriver driver) {
        return driver.findElements(By.partialLinkText("Inbox")).size() >0;
    }

    public static void clickComposeButton(WebDriver driver) {
        WebElement composeButton = driver.findElement(By.cssSelector("div[role='button'][gh='cm']"));
        composeButton.click();
    }

    public static void fillInRecipient(WebDriver driver, String s) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("textarea[name='to']")));
        WebElement receipentTextArea = driver.findElement(By.cssSelector("textarea[name='to']"));
        receipentTextArea.clear();
        receipentTextArea.sendKeys("knedel1981@gmail.com");
    }

    public void fillInSubject(WebDriver driver, String s) {
        WebElement subjectTextArea = driver.findElement(By.cssSelector("input[name='subjectbox']"));
        final String subject = "Gmail Send Email Test";
        subjectTextArea.clear();
        subjectTextArea.sendKeys(subject);
    }

    public void fillInEmailBody(WebDriver driver, String s) {
        WebElement bodyTextArea = driver.findElement(By.cssSelector("div[role='textbox']"));
        final String body = "Hello Testers! Good Morning!";
        bodyTextArea.clear();
        bodyTextArea.sendKeys(body);
    }

    public void clickSendButton(WebDriver driver) {
        WebElement sendButton = driver.findElement(By.cssSelector("div[aria-label*=\"Send\"]"));
        sendButton.click();
    }

    public void clickInboxWithNewEmail(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Inbox")));
        WebElement inboxLinkage = driver.findElement(By.partialLinkText("Inbox"));
        inboxLinkage.click();
    }

    public EmailViewPage clickNewEmail(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='y6'] span[id] b")));
        WebElement newEmail = driver.findElement(By.cssSelector("div[class='y6'] span[id] b"));
        newEmail.click();

        return PageFactory.initElements(driver, EmailViewPage.class);
    }
}
