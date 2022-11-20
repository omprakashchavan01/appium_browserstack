package com.qa.base;

import com.qa.utils.JsonParser;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.json.JSONObject;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class DriverManager {
    private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public static AppiumDriver getDriver(){
        return driver.get();
    }

    public static void setDriver(AppiumDriver driver1){
        driver.set(driver1);
    }

    public static void initializeDriver(String deviceID) throws MalformedURLException {
        AppiumDriver driver;
//        String userName = "enter_your_browserstack_username";
//        String accessKey = "enter_your_browserstack_accesskey";
        String userName = System.getenv("BROWSERSTACK_USERNAME");
        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
//        String browserstackLocal = System.getenv("BROWSERSTACK_LOCAL");
        String buildName = System.getenv("BROWSERSTACK_BUILD_NAME");
//        String browserstackLocalIdentifier = System.getenv("BROWSERSTACK_LOCAL_IDENTIFIER");
        String app = System.getenv("BROWSERSTACK_APP_ID");

        JSONObject jsonObject = new JSONObject(
                JsonParser.parse("Devices.json").getJSONObject(deviceID).toString()
        );

        DesiredCapabilities capabilities = new DesiredCapabilities();

        HashMap<String, Object> browserstackOptions = new HashMap<>();
//        browserstackOptions.put("userName", userName);
//        browserstackOptions.put("accessKey", accessKey);
        browserstackOptions.put("projectName", "Project 1");
        browserstackOptions.put("buildName", buildName);
        browserstackOptions.put("sessionName", "Session 1");
        browserstackOptions.put("appiumVersion", "2.0.0");
        capabilities.setCapability("bstack:options", browserstackOptions);

//        capabilities.setCapability("platformName", "android");
        capabilities.setCapability("platformVersion", jsonObject.getString("os_version"));
        capabilities.setCapability("deviceName", jsonObject.getString("device"));
        capabilities.setCapability("app", app);

//        URL url = new URL("https://hub-cloud.browserstack.com/wd/hub");
//        URL url = new URL("https://hub.browserstack.com/wd/hub");
        URL url = new URL("https://"+userName+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub");

        switch (deviceID){
            case "1":
                driver = new AndroidDriver(url, capabilities);
                break;
            case "2":
                driver = new IOSDriver(url, capabilities);
                break;
            default:
                throw new IllegalStateException("invalid device id" + deviceID);
        }
        setDriver(driver);
    }
}
