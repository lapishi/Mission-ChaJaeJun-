package com.ll.domain;

import com.ll.standard.util.Ut;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Rq {
    private final String line;
    private final String action;
    public String queryString;
    private final Map<String, String> paramsMap;

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

        Arrays.stream(queryStringBits)
                .map(queryParamStr -> queryParamStr.split("=", 2))
                .filter(queryParamStrBits -> queryParamStrBits.length == 2)
                .forEach(queryParamStrBits -> {
                    String paramName = queryParamStrBits[0];
                    String paramValue = queryParamStrBits[1];
                    paramsMap.put(paramName, paramValue);
                });
    }

    public String getAction() {
        return action;
    }

    public int getParamAsInt(String paramName, int defaultValue) {
        return Ut.str.parseInt(paramsMap.get(paramName), defaultValue);
    }


}
