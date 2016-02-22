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
public class SignInPage {

    public void fillInUsername(WebDriver driver, String s) {
        WebElement usernameTextbox = driver.findElement(By.id("Email"));
        usernameTextbox.clear();
        usernameTextbox.sendKeys("knedel1981@gmail.com");
    }

    public SignInPage clickNextButton(WebDriver driver) {
        WebElement nextButton = driver.findElement(By.id("next"));
        nextButton.click();

        return PageFactory.initElements(driver, SignInPage.class);
    }

    public void fillInPassword(WebDriver driver, String s) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Passwd")));

        WebElement passwordTextbox = driver.findElement(By.name("Passwd"));
        passwordTextbox.clear();
        passwordTextbox.sendKeys("testuser1234");
    }

    public EmailHomePage clickSignIn(WebDriver driver) {
        WebElement loginButton = driver.findElement(By.xpath(".//*[@id='signIn']"));
        loginButton.click();

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Inbox")));

        return PageFactory.initElements(driver, EmailHomePage.class);
    }

    public boolean isSignButtonExist(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signIn")));

        return driver.findElements(By.id("signIn")).size() >0;
    }

}
