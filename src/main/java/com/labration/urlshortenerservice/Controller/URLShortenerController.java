package com.labration.urlshortenerservice.Controller;

import com.labration.urlshortenerservice.Service.URLShortenerService;
import com.labration.urlshortenerservice.Url.UrlRequest;
import com.labration.urlshortenerservice.Url.UrlResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

@RestController
public class URLShortenerController {

    private static final Logger LOG = Logger.getLogger(URLShortenerController.class.getName());

    private final URLShortenerService urlShortenerService;

    public URLShortenerController(URLShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @PostMapping("/short")
    public UrlResponse shortenUrl(@RequestBody UrlRequest request, HttpServletRequest req) {
        String originalUrl = request.getUrl();
        if (!originalUrl.startsWith("http://") && !originalUrl.startsWith("https://")) {
            originalUrl = "http://" + originalUrl;
        }
        LOG.info("Original URL after validation: " + originalUrl);
        String shortUrl = urlShortenerService.shortenURL(originalUrl);
        LOG.info("Generated short URL: " + shortUrl);
        String serverUrl = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/";
        return new UrlResponse(serverUrl + shortUrl);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> getLongUrl(@PathVariable("shortUrl") String shortUrl) {
        try {
            LOG.info(String.format("Getting long URL for short URL: %s", shortUrl));
            String longUrl = urlShortenerService.getLongURL(shortUrl);
            LOG.info(String.format("Long URL: %s", longUrl));
            if (!longUrl.startsWith("http://") && !longUrl.startsWith("https://")) {
                longUrl = "http://" + longUrl;
            }
            return ResponseEntity.status(301).location(URI.create(longUrl)).build();
        } catch (NoSuchElementException e) {
            LOG.warning("No long URL found for short URL: " + shortUrl);
            return ResponseEntity.status(404).build();
        }
    }
}