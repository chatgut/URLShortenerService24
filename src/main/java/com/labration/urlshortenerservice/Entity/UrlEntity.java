package com.labration.urlshortenerservice.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "url")
public class UrlEntity {

    @Id
    private String shorturl;
    private String longurl;

    public UrlEntity() {}

    public UrlEntity(String shorturl, String longurl) {
        this.shorturl = shorturl;
        this.longurl = longurl;
    }

    public String getShorturl() {
        return shorturl;
    }

    public void setShorturl(String shorturl) {
        this.shorturl = shorturl;
    }

    public String getLongurl() {
        return longurl;
    }

    public void setLongurl(String longurl) {
        this.longurl = longurl;
    }
}
