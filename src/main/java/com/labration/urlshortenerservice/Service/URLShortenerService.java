package com.labration.urlshortenerservice.Service;

import java.util.HashMap;
import java.util.Random;

public class URLShortenerService {
    private HashMap<String, String> urlMap = new HashMap<>();
    private Random random = new Random();
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_URL_LENGTH = 8;

    public String shortenURL(String longUrl){
        String shortUrl = generateRandomString();
        while (urlMap.containsKey(shortUrl)){
            shortUrl = generateRandomString();
        }
        urlMap.put(shortUrl, longUrl);
        return shortUrl;
    }

    public String getLongURL(String shortURL){
        return urlMap.get(shortURL);
    }

    private String generateRandomString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SHORT_URL_LENGTH; i++){
            sb.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }
        return sb.toString();
    }
}