package com.ll;

import java.util.HashMap;
import java.util.Map;

public class Rq {
    String line;
    String action;
    String queryString;
    Map<String, String> paramsMap;

    Rq(String line) {
        paramsMap = new HashMap<>();

        this.line = line;
        String[] lineBits = line.split("\\?", 2);
        action = lineBits[0].trim();
        queryString = lineBits[1].trim();
        String[] queryStringBits = queryString.split("&");

        for (int i = 0; i < queryStringBits.length; i++) {
            String queryParamStr = queryStringBits[i];
            String[] queryParamStrBits = queryParamStr.split("=", 2);

            String paramName = queryParamStrBits[0];
            String paramValue = queryParamStrBits[1];

            paramsMap.put(paramName, paramValue);
        }
    }

    String getAction() {
        return action;

    }

    public int getParamAsInt(String paramName, int defaultValue) {
        String paramValue = paramsMap.get(paramName);

        if (paramValue != null) {//소수점 에러 방지용
            try {
                return Integer.parseInt(paramValue);
            } catch (NumberFormatException e) {
            }
        }
        return defaultValue;
    }
}
