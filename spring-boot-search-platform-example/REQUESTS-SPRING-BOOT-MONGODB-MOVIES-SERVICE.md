# Sample QUERIES

This document shows how to interact with the MOVIES API using `curl`.

## Base URL

```text
http://localhost:7070/api/movies
```

---

## Create new Movie

Creates a new movie

### Request

```bash
curl -X POST -H "Accept:application/json" -H "Content-Type:application/json" http://localhost:7070/api/movies -d @./spring-boot-mongodb-movies-service/requests/create-movie-request-payload.json | jq .
```

### Example Response

<details>
<summary>Response body</summary>

```json
{
  "awards": {
    "nominations": 0,
    "wins": 1,
    "text": "1 win."
  },
  "imdb": {
    "rating": 7.4,
    "imdbId": null,
    "votes": 9847
  },
  "runtime": 11,
  "cast": [
    "A.C. Abadie",
    "Gilbert M. 'Broncho Billy' Anderson",
    "George Barnes",
    "Justus D. Barnes"
  ],
  "countries": [
    "USA"
  ],
  "directors": [
    "Edwin S. Porter"
  ],
  "genres": [
    "Short",
    "Western"
  ],
  "languages": [
    "English"
  ],
  "writers": null,
  "_id": {
    "date": "2026-08-04T17:41:09.000Z",
    "timestamp": 1785865269
  },
  "fullPlot": null,
  "plot": "HelloMyMovie",
  "poster": "https://m.media-amazon.com/images/M/MV5BMTU3NjE5NzYtYTYyNS00MDVmLWIwYjgtMmYwYWIxZDYyNzU2XkEyXkFqcGdeQXVyNzQzNzQxNzI@._V1_SY1000_SX677_AL_.jpg",
  "rated": "TV-G",
  "released": "1903-12-01T00:00:00.000+00:00",
  "title": "The Great Train Robbery",
  "type": "movie",
  "year": "1903",
  "tomatoes": {
    "critic": {
      "rating": 7.6,
      "meter": 100,
      "numReviews": 6
    },
    "fresh": 6,
    "rotten": 0,
    "lastUpdated": "2015-08-13 00:27:59.177000000",
    "production": null,
    "viewer": {
      "rating": 3.7,
      "meter": 75,
      "numReviews": 2559
    }
  }
}
```


</details>










