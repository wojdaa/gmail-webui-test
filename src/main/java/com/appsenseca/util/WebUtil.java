package com.appsenseca.util;

import com.appsenseca.pageobjects.SignInPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by wojdaa on 2016-02-22.
 */
public class WebUtil {

    public static SignInPage goToSignInPage(WebDriver driver) {
        driver.get("http://gmail.com");
        return PageFactory.initElements(driver, SignInPage.class);
    }
}
