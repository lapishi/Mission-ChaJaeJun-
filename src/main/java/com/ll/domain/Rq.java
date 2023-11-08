package com.ll.domain;

import com.ll.standard.util.Ut;

import java.util.HashMap;
import java.util.Map;

public class Rq {
    private String line;
    private String action;
    private String queryString;
    private Map<String, String> paramsMap;

    public Rq(String line) {
        paramsMap = new HashMap<>();

        this.line = line;
        String[] lineBits = line.split("\\?", 2);
        action = lineBits[0].trim();

        if (lineBits.length == 1) { //ArrayIndexOutOfBoundsException 에러 방지용
            return;
        }

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

    public String getAction() {
        return action;
    }

    public int getParamAsInt(String paramName, int defaultValue) {
        return Ut.str.parseInt(paramsMap.get(paramName), defaultValue);
    }
}
