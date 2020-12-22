package com.qa.base;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    public WebDriverWait wait;

    public BasePage(){
        PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getDriver()), this);
        wait = new WebDriverWait(DriverManager.getDriver(), 10);
    }
}
