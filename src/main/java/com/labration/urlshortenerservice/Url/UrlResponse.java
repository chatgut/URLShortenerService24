package com.labration.urlshortenerservice.Url;

public class UrlResponse {
    private String short_url;

    public UrlResponse(String short_url) {
        this.short_url = short_url;
    }

    public String getShort_url() {
        return short_url;
    }

    public void setShort_url(String short_url) {
        this.short_url = short_url;
    }
}
