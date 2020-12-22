package com.qa.utils;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;

public class JsonParser {

    public static JSONObject parse(String file) {
        InputStream is = JsonParser.class.getClassLoader().getResourceAsStream(file);
        assert is != null;
        return new JSONObject(new JSONTokener(is));
    }
}
