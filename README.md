# URL Shortener Service
This is a simple URL shortener service that takes a long URL and returns a short URL. The short URL can then be used to redirect to the original long URL.

# Running with Docker

# Using the service
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