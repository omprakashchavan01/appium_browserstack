package com.qa.base;

import com.qa.utils.JsonParser;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.json.JSONObject;
import org.openqa.selenium.remote.DesiredCapabilities;

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

    public static void initializeDriver(String deviceID) throws Exception {

        String userName = "omprakashchavan1";
        String accessKey = "pmQHaJUnZBL1fUFUxnTQ";
//        String userName = System.getenv("BROWSERSTACK_USERNAME");
//        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
    //    String browserstackLocal = System.getenv("BROWSERSTACK_LOCAL");
//        String buildName = System.getenv("BROWSERSTACK_BUILD_NAME");
        String buildName = "My first build";
    //    String browserstackLocalIdentifier = System.getenv("BROWSERSTACK_LOCAL_IDENTIFIER");
//        String app = System.getenv("BROWSERSTACK_APP_ID");

        JSONObject deviceObj = new JSONObject(JsonParser.parse("Devices.json").getJSONObject(deviceID).toString());

        AppiumDriver driver;
        DesiredCapabilities caps = new DesiredCapabilities();

//        caps.setCapability("platformName", "android");
        caps.setCapability("platformVersion", deviceObj.getString("os_version"));
        caps.setCapability("deviceName", deviceObj.getString("device"));
        caps.setCapability("app", deviceObj.getString("app_url"));

        HashMap<String, Object> browserstackOptions = new HashMap<>();

        // Set BrowserStack capabilities
//        browserstackOptions.put("userName", userName);
//        browserstackOptions.put("accessKey", accessKey);
        browserstackOptions.put("appiumVersion", "2.0.0");
        browserstackOptions.put("projectName", "First Java Project");
        browserstackOptions.put("buildName", "browserstack-build-1");
        browserstackOptions.put("sessionName", "first_test");

        caps.setCapability("bstack:options", browserstackOptions);

        URL url = new URL("https://"+userName+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub");
//        URL url = new URL("https://hub-cloud.browserstack.com/wd/hub");
//        URL url = new URL("https://hub.browserstack.com/wd/hub");
//        URL url = new URL("http://hub.browserstack.com/wd/hub");

        switch (deviceID){
            case "1":
                driver = new AndroidDriver(url, caps);
                break;
            case "2":
                driver = new IOSDriver(url, caps);
                break;
            default:
                throw new IllegalStateException("invalid device id" + deviceID);
        }
        setDriver(driver);
    }
}
