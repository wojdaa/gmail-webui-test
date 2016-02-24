package com.appsenseca.pageobjects;

import com.appsenseca.util.WebUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by wojdaa on 2016-02-22.
 */
public class SignInPage {

    public void fillInUsername(WebDriver driver, String s) {
        WebUtil.clearAndSendKeys(driver, By.id("Email"), s);
    }

    public SignInPage clickNextButton(WebDriver driver) {
        WebUtil.click(driver, By.id("next"));

        return null;
    }

    public void fillInPassword(WebDriver driver, String s) {
        WebUtil.waitForElementVisible(driver, By.id("Passwd"));
        WebUtil.clearAndSendKeys(driver, By.id("Passwd"), s);
    }

    public EmailHomePage clickSignIn(WebDriver driver) {
        WebUtil.click(driver, By.xpath(".//*[@id='signIn']"));
        WebUtil.waitForElementVisible(driver, By.partialLinkText("Inbox"));

        return PageFactory.initElements(driver, EmailHomePage.class);
    }

    public boolean isSignButtonExist(WebDriver driver) {
        return WebUtil.isElementExist(driver, By.id("signIn"));
    }

}
