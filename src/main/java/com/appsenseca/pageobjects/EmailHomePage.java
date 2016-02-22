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

}
