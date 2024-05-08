package com.labration.urlshortenerservice.Controller;

import com.labration.urlshortenerservice.Service.URLShortenerService;
import com.labration.urlshortenerservice.Url.UrlRequest;
import com.labration.urlshortenerservice.Url.UrlResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.logging.Logger;

@RestController
public class URLShortenerController {

    private static final Logger LOG = Logger.getLogger(String.valueOf(URLShortenerController.class));

    @Autowired
    private URLShortenerService urlShortenerService;

    @PostMapping("/short")
    public UrlResponse shortenUrl(@RequestBody UrlRequest request, HttpServletRequest req) {
        String shortUrl = urlShortenerService.shortenURL(request.getUrl());
        String serverUrl = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/";
        return new UrlResponse(serverUrl + shortUrl);
    }

    @GetMapping("/{shortUrl}")
    public RedirectView getLongUrl(@PathVariable("shortUrl") String shortUrl) {
        LOG.info(String.format("Getting long URL for short URL: %s", shortUrl));
        String longUrl = urlShortenerService.getLongURL(shortUrl);
        LOG.info(String.format("Long URL: %s", longUrl));
        return new RedirectView(longUrl);
    }
}