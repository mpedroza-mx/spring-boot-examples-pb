# Sample QUERIES SPRING-BOOT-SOLR-SEARCH-SERVICE

This document shows how to interact with the Movies Search API using `curl`.

## Base URL

```text
http://localhost:6060/api/movies/search
```

---

## Simple query

Retrieves the movies found with the text provided

### Request

```bash
curl -X POST -H "Accept:application/json" -H "Content-Type:application/json" http://localhost:6060/api/movies/search -d @./spring-boot-solr-search-service/requests/simple-search-request-body.json | jq .
```

### Example Response

<details>
<summary>Response body</summary>

```json
{
  "movies": [
    {
      "awards": {
        "nominations": 1,
        "wins": 3,
        "text": "3 wins & 1 nomination."
      },
      "imdb": {
        "rating": 7.4,
        "imdbId": 2245195,
        "votes": 1905
      },
      "runtime": 86,
      "cast": [
        "Nils d'Aulaire",
        "Jay Klaitz",
        "Julie Ann Emery",
        "April L. Hernandez"
      ],
      "countries": [
        "USA"
      ],
      "directors": [
        "John Mitchell",
        "Jeremy Kipp Walker"
      ],
      "genres": [
        "Comedy",
        "Music",
        "Sci-Fi"
      ],
      "languages": [
        "English",
        "Spanish"
      ],
      "writers": null,
      "id": "573a13d9f29313caabda9530",
      "fullPlot": "Two aliens from the planet Hondo have come take over our planet. But when they discover an amazing human invention called \"music\", they immediately abandon their mission, head to a tiny Brooklyn bar, and start the universe's first Hondonian bluegrass duo: Future Folk!",
      "plot": "The possibly exaggerated origin story of the real life alien bluegrass band, Future Folk, that has been playing for NYC audiences for the better part of a decade.",
      "poster": "https://m.media-amazon.com/images/M/MV5BNzA3MDI3MzAxMl5BMl5BanBnXkFtZTcwNDY2Mjc0OQ@@._V1_SY1000_SX677_AL_.jpg",
      "rated": null,
      "released": "Fri May 31 00:00:00 UTC 2013",
      "title": "The History of Future Folk",
      "type": "movie",
      "year": "2012",
      "lastUpdated": null,
      "tomatoes": {
        "critic": {
          "rating": 7.2,
          "meter": 94,
          "numReviews": 31
        },
        "fresh": 29,
        "rotten": 2,
        "lastUpdated": "Wed Sep 02 19:39:01 UTC 2015",
        "viewer": {
          "rating": 4.1,
          "meter": 86,
          "numReviews": 1137
        }
      }
    }
  ],
  "numOfMoviesFound": 1
}
```

</details>



---

## Query with EQUALS filter

Retrieves the movies found with the text provided and the additional EQUALS filter

### Request

```bash
curl -X POST -H "Accept:application/json" -H "Content-Type:application/json" http://localhost:6060/api/movies/search -d @./spring-boot-solr-search-service/requests/filter-equals-search-request-body.json | jq .
```

### Example Response

<details>
<summary>Response body</summary>

```json
{
  "movies": [
    {
      "awards": {
        "nominations": 1,
        "wins": 3,
        "text": "3 wins & 1 nomination."
      },
      "imdb": {
        "rating": 7.4,
        "imdbId": 2245195,
        "votes": 1905
      },
      "runtime": 86,
      "cast": [
        "Nils d'Aulaire",
        "Jay Klaitz",
        "Julie Ann Emery",
        "April L. Hernandez"
      ],
      "countries": [
        "USA"
      ],
      "directors": [
        "John Mitchell",
        "Jeremy Kipp Walker"
      ],
      "genres": [
        "Comedy",
        "Music",
        "Sci-Fi"
      ],
      "languages": [
        "English",
        "Spanish"
      ],
      "writers": null,
      "id": "573a13d9f29313caabda9530",
      "fullPlot": "Two aliens from the planet Hondo have come take over our planet. But when they discover an amazing human invention called \"music\", they immediately abandon their mission, head to a tiny Brooklyn bar, and start the universe's first Hondonian bluegrass duo: Future Folk!",
      "plot": "The possibly exaggerated origin story of the real life alien bluegrass band, Future Folk, that has been playing for NYC audiences for the better part of a decade.",
      "poster": "https://m.media-amazon.com/images/M/MV5BNzA3MDI3MzAxMl5BMl5BanBnXkFtZTcwNDY2Mjc0OQ@@._V1_SY1000_SX677_AL_.jpg",
      "rated": null,
      "released": "Fri May 31 00:00:00 UTC 2013",
      "title": "The History of Future Folk",
      "type": "movie",
      "year": "2012",
      "lastUpdated": null,
      "tomatoes": {
        "critic": {
          "rating": 7.2,
          "meter": 94,
          "numReviews": 31
        },
        "fresh": 29,
        "rotten": 2,
        "lastUpdated": "Wed Sep 02 19:39:01 UTC 2015",
        "viewer": {
          "rating": 4.1,
          "meter": 86,
          "numReviews": 1137
        }
      }
    }
  ],
  "numOfMoviesFound": 1
}
```

</details>

---

## Query with RANGE filter

Retrieves the movies found with the text provided and the additional RANGE filter

### Request

```bash
curl -X POST -H "Accept:application/json" -H "Content-Type:application/json" http://localhost:6060/api/movies/search -d @./spring-boot-solr-search-service/requests/filter-range-search-request-body.json | jq .
```

### Example Response

<details>
<summary>Response body</summary>

```json
{
  "movies": [
    {
      "awards": {
        "nominations": 1,
        "wins": 3,
        "text": "3 wins & 1 nomination."
      },
      "imdb": {
        "rating": 7.4,
        "imdbId": 2245195,
        "votes": 1905
      },
      "runtime": 86,
      "cast": [
        "Nils d'Aulaire",
        "Jay Klaitz",
        "Julie Ann Emery",
        "April L. Hernandez"
      ],
      "countries": [
        "USA"
      ],
      "directors": [
        "John Mitchell",
        "Jeremy Kipp Walker"
      ],
      "genres": [
        "Comedy",
        "Music",
        "Sci-Fi"
      ],
      "languages": [
        "English",
        "Spanish"
      ],
      "writers": null,
      "id": "573a13d9f29313caabda9530",
      "fullPlot": "Two aliens from the planet Hondo have come take over our planet. But when they discover an amazing human invention called \"music\", they immediately abandon their mission, head to a tiny Brooklyn bar, and start the universe's first Hondonian bluegrass duo: Future Folk!",
      "plot": "The possibly exaggerated origin story of the real life alien bluegrass band, Future Folk, that has been playing for NYC audiences for the better part of a decade.",
      "poster": "https://m.media-amazon.com/images/M/MV5BNzA3MDI3MzAxMl5BMl5BanBnXkFtZTcwNDY2Mjc0OQ@@._V1_SY1000_SX677_AL_.jpg",
      "rated": null,
      "released": "Fri May 31 00:00:00 UTC 2013",
      "title": "The History of Future Folk",
      "type": "movie",
      "year": "2012",
      "lastUpdated": null,
      "tomatoes": {
        "critic": {
          "rating": 7.2,
          "meter": 94,
          "numReviews": 31
        },
        "fresh": 29,
        "rotten": 2,
        "lastUpdated": "Wed Sep 02 19:39:01 UTC 2015",
        "viewer": {
          "rating": 4.1,
          "meter": 86,
          "numReviews": 1137
        }
      }
    }
  ],
  "numOfMoviesFound": 1
}
```

</details>


# Query with MULTIPLE filters

Retrieves the movies found with the text provided and the additional filters

### Request

```bash
curl -X POST -H "Accept:application/json" -H "Content-Type:application/json" http://localhost:6060/api/movies/search -d @./spring-boot-solr-search-service/requests/multiple-filter-search-request-body.json | jq .
```

### Example Response

<details>
<summary>Response body</summary>

```json
{
  "movies": [
    {
      "awards": {
        "nominations": 1,
        "wins": 3,
        "text": "3 wins & 1 nomination."
      },
      "imdb": {
        "rating": 7.4,
        "imdbId": 2245195,
        "votes": 1905
      },
      "runtime": 86,
      "cast": [
        "Nils d'Aulaire",
        "Jay Klaitz",
        "Julie Ann Emery",
        "April L. Hernandez"
      ],
      "countries": [
        "USA"
      ],
      "directors": [
        "John Mitchell",
        "Jeremy Kipp Walker"
      ],
      "genres": [
        "Comedy",
        "Music",
        "Sci-Fi"
      ],
      "languages": [
        "English",
        "Spanish"
      ],
      "writers": null,
      "id": "573a13d9f29313caabda9530",
      "fullPlot": "Two aliens from the planet Hondo have come take over our planet. But when they discover an amazing human invention called \"music\", they immediately abandon their mission, head to a tiny Brooklyn bar, and start the universe's first Hondonian bluegrass duo: Future Folk!",
      "plot": "The possibly exaggerated origin story of the real life alien bluegrass band, Future Folk, that has been playing for NYC audiences for the better part of a decade.",
      "poster": "https://m.media-amazon.com/images/M/MV5BNzA3MDI3MzAxMl5BMl5BanBnXkFtZTcwNDY2Mjc0OQ@@._V1_SY1000_SX677_AL_.jpg",
      "rated": null,
      "released": "Fri May 31 00:00:00 UTC 2013",
      "title": "The History of Future Folk",
      "type": "movie",
      "year": "2012",
      "lastUpdated": null,
      "tomatoes": {
        "critic": {
          "rating": 7.2,
          "meter": 94,
          "numReviews": 31
        },
        "fresh": 29,
        "rotten": 2,
        "lastUpdated": "Wed Sep 02 19:39:01 UTC 2015",
        "viewer": {
          "rating": 4.1,
          "meter": 86,
          "numReviews": 1137
        }
      }
    }
  ],
  "numOfMoviesFound": 1
}
```

</details>