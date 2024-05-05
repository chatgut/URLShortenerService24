package com.labration.urlshortenerservice.Controller;

import com.labration.urlshortenerservice.Service.URLShortenerService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.logging.Logger;

@RestController
@RequestMapping("/url")
public class URLShortenerController {

    private static final Logger LOG = Logger.getLogger(String.valueOf(URLShortenerController.class));
    private URLShortenerService urlShortenerService = new URLShortenerService();

    @PostMapping
    public String shortenUrl(@RequestBody String longUrl) {
        return urlShortenerService.shortenURL(longUrl);
    }

    @GetMapping("/{shortUrl}")
    public RedirectView getLongUrl(@PathVariable("shortUrl") String shortUrl) {
        LOG.info(String.format("Getting long URL for short URL: %s", shortUrl));
        String longUrl = urlShortenerService.getLongURL(shortUrl);
        LOG.info(String.format("Long URL: %s", longUrl));
        return new RedirectView(longUrl);
    }
}
