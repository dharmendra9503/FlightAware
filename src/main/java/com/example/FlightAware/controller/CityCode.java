package com.example.FlightAware.controller;

import java.util.HashMap;

public class CityCode {
    public String codes(String city){
        String code;
        HashMap<String, String> cityCode = new HashMap<String, String>();
        cityCode.put("munich","MUC");
        cityCode.put("berlin","BER");
        cityCode.put("mumbai","BOM");
        cityCode.put("india","IN");
        cityCode.put("bengaluru","BLR");
        cityCode.put("hyderabad","HYD");
        cityCode.put("chennai","MAA");
        cityCode.put("kolkata","CCU");
        cityCode.put("ahmedabad","AMD");
        cityCode.put("kochi","COK");
        cityCode.put("pune","PNQ");
        cityCode.put("fort wayne","FWA-sky");
        cityCode.put("united states","US-sky");
        cityCode.put("concord regional","USA-sky");
        cityCode.put("koh samui","USM-sky");
        cityCode.put("samui island","USM-sky");
        cityCode.put("us virgin islands","VI-sky");
        cityCode.put("st augustine","UST-sky");
        cityCode.put("busuanga","USU-sky");
        cityCode.put("ushuaia","USH-sky");
        cityCode.put("saint thomas cyril e king","STT-sky");
        cityCode.put("ust-kamenogorsk","UKK-sky");
        cityCode.put("ulsan","USN-sky");
        cityCode.put("uk","UK-sky");
        cityCode.put("ukraine","UA-sky");
        cityCode.put("kyiv","KIEV-sky");
        cityCode.put("kiev borispol","KBP-sky");
        cityCode.put("kiev zhulhany","IEV-sky");
        cityCode.put("lviv","LWO-sky");
        cityCode.put("odesa central","ODS-sky");
        cityCode.put("kharkiv","HRK-sky");
        cityCode.put("kobe","UKB-sky");
        cityCode.put("ukunda","UKA-sky");

        code = cityCode.get(city.toLowerCase());
        return code;
    }
}
