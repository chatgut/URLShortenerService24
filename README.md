# URL Shortener Service
This is a simple URL shortener service that takes a long URL and returns a short URL. The short URL can then be used to redirect to the original long URL.

# Running the service

- In a compose.yml file, add below:
```
  app:
    image: erikasignal/url-shortener-service:latest
    ports:
      - "8080:8080"
    environment:
      SPRING_DATA_MONGODB_HOST: mongodb
```
- If you do not have a mongoDB instance running, you can add the below to the compose.yml file:
```
  mongodb:
    image: mongo:latest
    ports:
      - "27017:27017"
```
- Run `docker-compose up` in the directory where the compose.yml file is located.

# Using the service with the API
### How to create a short URL
`POST /short`

JSON body:
```
{
    "url": "https://www.google.com"
}
```
Example output:
```
{
    "short_url": "http://localhost:8080/F2jLE"
}
```
`GET /{ShortUrl}`

GET: "http://localhost:8080/F2jLE"