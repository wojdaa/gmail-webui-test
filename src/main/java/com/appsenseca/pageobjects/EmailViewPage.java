package com.appsenseca.pageobjects;

import com.appsenseca.util.WebUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by wojdaa on 2016-02-23.
 */
public class EmailViewPage {
    public String getEmailSubjectText(WebDriver driver) {
        WebUtil.waitForElementVisible(driver, By.cssSelector("h2[class='hP']"));
        return WebUtil.getElementText(driver, By.cssSelector("h2[class='hP']"));
    }

    public String getEmailBodyText(WebDriver driver) {
        return WebUtil.getElementText(driver, By.cssSelector("div[class='nH aHU'] div[dir='ltr']"));
    }

    public boolean getEmailAttachmentName(WebDriver driver) {
        WebUtil.waitForElementVisible(driver, By.cssSelector("span[class='aV3 a6U']"));
        return WebUtil.isElementExist(driver, By.cssSelector("span[class='aV3 a6U']"));
    }
}
