package com.labration.urlshortenerservice.Service;

import com.labration.urlshortenerservice.Entity.UrlEntity;
import com.labration.urlshortenerservice.Url.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class URLShortenerService {
    @Autowired
    private UrlRepository urlRepository;

    private Random random = new Random();
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_URL_LENGTH = 5;

    public String shortenURL(String longUrl){
        String shortUrl = generateRandomString();
        while (urlRepository.existsById(shortUrl)){
            shortUrl = generateRandomString();
        }
        UrlEntity urlEntity = new UrlEntity(shortUrl, longUrl);
        urlRepository.save(urlEntity);
        return shortUrl;
    }

    public String getLongURL(String shortURL){
        UrlEntity urlEntity = urlRepository.findById(shortURL).orElse(null);
        return urlEntity != null ? urlEntity.getLongurl() : null;
    }

    private String generateRandomString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SHORT_URL_LENGTH; i++){
            sb.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }
        return sb.toString();
    }
}