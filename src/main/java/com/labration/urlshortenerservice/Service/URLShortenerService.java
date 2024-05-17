package com.labration.urlshortenerservice.Service;

import com.labration.urlshortenerservice.Entity.UrlEntity;
import com.labration.urlshortenerservice.Url.UrlRepository;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
public class URLShortenerService {

    private static final Logger LOG = LoggerFactory.getLogger(URLShortenerService.class);

    private final UrlRepository urlRepository;

    private Random random = new Random();
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_URL_LENGTH = 5;

    public URLShortenerService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String shortenURL(String longUrl) {
        if (!longUrl.startsWith("http://") && !longUrl.startsWith("https://")) {
            longUrl = "http://" + longUrl;
        }
        LOG.info("Long URL to be shortened: {}", longUrl);
        String shortUrl = generateRandomString();
        while (urlRepository.existsById(shortUrl)) {
            shortUrl = generateRandomString();
        }
        UrlEntity urlEntity = new UrlEntity(shortUrl, longUrl);
        urlRepository.save(urlEntity);
        LOG.info("URL entity saved: {}", urlEntity);
        return shortUrl;
    }

    public String getLongURL(String shortURL) {
        UrlEntity urlEntity = urlRepository.findById(shortURL)
                .orElseThrow(() -> new NoSuchElementException("No URL entity found for short URL: " + shortURL));
        String longUrl = urlEntity.getLongurl();
        LOG.info("Found long URL: {}", longUrl);
        return longUrl;
    }

    private String generateRandomString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            sb.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }
        return sb.toString();
    }
}