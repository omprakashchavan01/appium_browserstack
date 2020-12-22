package com.qa.base;

import com.qa.utils.JsonParser;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.json.JSONObject;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class DriverManager {
    private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public static AppiumDriver getDriver(){
        return driver.get();
    }

    public static void setDriver(AppiumDriver driver1){
        driver.set(driver1);
    }

    public static void initializeDriver(String deviceID) throws Exception {
        AppiumDriver driver;
        String accessKey = "WSh657ymwgyZTQkTtVoy";
        String userName = "omprakashchavan1";

        JSONObject deviceObj = JsonParser.parse("Devices.json").getJSONObject(deviceID);

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("device", deviceObj.getString("device"));
        caps.setCapability("os_version", deviceObj.getString("os_version"));
        caps.setCapability("project", "My First Project");
        caps.setCapability("build", "My First Build");
        caps.setCapability("name", "Bstack-[Java] Sample Test");
        caps.setCapability("app", deviceObj.getString("app_url"));

        URL url = new URL("https://" + userName + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub");

        switch (deviceID) {
            case "1":
                driver = new AndroidDriver(url, caps);
                break;
            case "2":
                driver = new IOSDriver(url, caps);
                break;
            default:
                throw new Exception("Invalid device ID! - " + deviceID);
        }
        setDriver(driver);
    }
}
