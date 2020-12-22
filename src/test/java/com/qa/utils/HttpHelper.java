package com.qa.utils;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.File;

public class HttpHelper {

    public static String post() throws UnirestException {
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.post("https://api-cloud.browserstack.com/app-automate/upload")
                .header("Authorization", "Basic b21wcmFrYXNoY2hhdmFuMTpXU2g2NTd5bXdneVpUUWtUdFZveQ==")
                .field("file", new File("/D:/Software/apps/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk"))
                .asString();
        return response.getBody();
    }
}
