package com.labration.urlshortenerservice.Url;

import com.labration.urlshortenerservice.Entity.UrlEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepository extends MongoRepository<UrlEntity, String> {
}
