# Sample QUERIES SPRING-BOOT-SOLR-MOVIES-SEARCH-SERVICE

This document shows how to interact with the Movies Search API using `curl`.

### Base URL

```text
http://localhost:6060/api/movies/search
```

---
## Simple Search

### Simple query

Retrieves the movies found with the text provided

#### Request

```bash
curl -X POST -H "Accept:application/json" -H "Content-Type:application/json" http://localhost:6060/api/movies/search -d @./spring-boot-solr-movies-search-service/requests/keyWordSearch/simple-search-request-body.json | jq .
```

#### Example Response

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

### Query with EQUALS filter

Retrieves the movies found with the text provided and the additional EQUALS filter

#### Request

```bash
curl -X POST -H "Accept:application/json" -H "Content-Type:application/json" http://localhost:6060/api/movies/search -d @./spring-boot-solr-movies-search-service/requests/keyWordSearch/filter-equals-search-request-body.json | jq .
```

#### Example Response

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

### Query with RANGE filter

Retrieves the movies found with the text provided and the additional RANGE filter

#### Request

```bash
curl -X POST -H "Accept:application/json" -H "Content-Type:application/json" http://localhost:6060/api/movies/search -d @./spring-boot-solr-movies-search-service/requests/keyWordSearch/filter-range-search-request-body.json | jq .
```

#### Example Response

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


### Query with MULTIPLE filters

Retrieves the movies found with the text provided and the additional filters

#### Request

```bash
curl -X POST -H "Accept:application/json" -H "Content-Type:application/json" http://localhost:6060/api/movies/search -d @./spring-boot-solr-movies-search-service/requests/keyWordSearch/multiple-filter-search-request-body.json | jq .
```

#### Example Response

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

## Semantic Search

Retrieves the movies found based on the meaning of the provided query

#### Request

```bash
curl -X POST -H "Accept:application/json" -H "Content-Type:application/json" http://localhost:6060/api/movies/semantic-search -d @./spring-boot-solr-movies-search-service/requests/semanticSearch/semantic-search-request-body.json | jq '.movies[] | {title, year, plot, plot,fullPlot, genres}'
```

#### Example Response

<details>
<summary>Response body</summary>

```json
[
  {
    "title": "It Came from Outer Space",
    "year": "1953",
    "plot": "A spaceship from another world crashes in the Arizona desert, and only an amateur stargazer and a schoolteacher suspect alien influence when the local townsfolk begin to act strange.",
    "fullPlot": "John Putnam is a writer and an amateur stargazer with a new home out in the beautiful Arizona desert, which he enjoys with Ellen Fields, his girlfriend and a local schoolteacher. John is not trusted by the people of the small town near where he lives, certainly not by Sheriff Matt Warren, who feels protective of Ellen, and perhaps something more. One night, John and Ellen see a meteor crash in the desert. John drags his friend, Pete, out of bed to take him over to the crash site in his helicopter. Once there, John climbs down into the crater. Unfortunately, he does so alone, as Pete and Ellen wait for him. John is the only one who sees the spaceship before a landslide covers it. And John is the only one who catches a glimpse of the hideous thing inside. At first John's story seems mad, until some of the townsfolk begin acting strange - as if they aren't really who they seem to be.",
    "genres": [
      "Horror",
      "Sci-Fi"
    ]
  },
  {
    "title": "Forbidden Planet",
    "year": "1956",
    "plot": "A starship crew goes to investigate the silence of a planet's colony only to find two survivors and a deadly secret that one of them has.",
    "fullPlot": "When Adams and his crew are sent to investigate the silence from a planet inhabited by scientists, he finds all but two have died. Dr. Morbius and his daughter Altaira have somehow survived a hideous monster which roams the planet. Unknown to Adams, Morbius has made a discovery, and has no intention of sharing it (or his daughter!) with anyone.",
    "genres": [
      "Action",
      "Adventure",
      "Family"
    ]
  },
  {
    "title": "The Day the Earth Stood Still",
    "year": "1951",
    "plot": "An alien lands and tells the people of Earth that they must live peacefully or be destroyed as a danger to other planets.",
    "fullPlot": "An alien (Klaatu) with his mighty robot (Gort) land their spacecraft on Cold War-era Earth just after the end of World War II. They bring an important message to the planet that Klaatu wishes to tell to representatives of all nations. However, communication turns out to be difficult, so, after learning something about the natives, Klaatu decides on an alternative approach.",
    "genres": [
      "Sci-Fi"
    ]
  },
  {
    "title": "Planet of the Apes",
    "year": "1968",
    "plot": "An astronaut crew crash lands on a planet in the distant future where intelligent talking apes are the dominant species, and humans are the oppressed and enslaved.",
    "fullPlot": "Taylor and two other astronauts come out of deep hibernation to find that their ship has crashed. Escaping with little more than clothes they find that they have landed on a planet where men are pre-lingual and uncivilized while apes have learned speech and technology. Taylor is captured and taken to the city of the apes after damaging his throat so that he is silent and cannot communicate with the apes.",
    "genres": [
      "Adventure",
      "Sci-Fi"
    ]
  },
  {
    "title": "The Dependent",
    "year": "1969",
    "plot": "Fernandez is a lonely man leading a lonely life. All he does is work for an old man in a hardware store. But all that changes, when he meets the girl of his dreams...and her family.",
    "fullPlot": "Fernandez is a lonely man leading a lonely life. All he does is work for an old man in a hardware store. But all that changes, when he meets the girl of his dreams...and her family.",
    "genres": [
      "Drama"
    ]
  },
  {
    "title": "The Invisible Ray",
    "year": "1936",
    "plot": "A scientist becomes murderous after discovering, and being exposed to the radiation of, a powerful new element called Radium X.",
    "fullPlot": "Visionary scientist Janos Rukh convinces a group of scientists and supporters to mount an expedition to the African continent to locate and study an ancient meteorite of great significance. He exposes himself to the highly toxic radiation of the meteorite, and while an antidote devised by Dr. Benet saves him from death by radiation poisoning, his naked touch causes instant death to others. Back in London, the benefits of the meteorite's controlled radiation offer Dr. Benet an opportunity to restore eyesight to the blind. The antidote's toxicity excites Prof. Rukh into paranoid rages as he seeks revenge against the members of his expedition, who he accuses of stealing his discovery for their own glory.",
    "genres": [
      "Horror",
      "Sci-Fi",
      "Thriller"
    ]
  },
  {
    "title": "Secrets of Life",
    "year": "1956",
    "plot": "A feature-length drama showing the changing World of Nature, the sky, the sea, the sun, planets,insects and volcanic action. A story of Nature's strange and intricate designs for survival and her many methods of perpetrating life.",
    "fullPlot": "A feature-length drama showing the changing World of Nature, the sky, the sea, the sun, planets,insects and volcanic action. A story of Nature's strange and intricate designs for survival and her many methods of perpetrating life.",
    "genres": [
      "Documentary",
      "Family"
    ]
  },
  {
    "title": "You Only Live Twice",
    "year": "1967",
    "plot": "Agent 007 and the Japanese secret service ninja force must find and stop the true culprit of a series of spacejackings before nuclear war is provoked.",
    "fullPlot": "When an American space capsule is swallowed up by what they believe to be a Russian spaceship, World War 3 nearly breaks out. The British Government, however, suspect that other powers are at work as the space craft went down near Japan. S.P.E.C.T.R.E. is the force behind the theft, as James Bond discovers, but its motives are far from clear, and he must first find out where the captured space capsule is held before America and Russia initiate another world war.",
    "genres": [
      "Action",
      "Adventure",
      "Thriller"
    ]
  },
  {
    "title": "Lonely Are the Brave",
    "year": "1962",
    "plot": "A fiercely independent cowboy arranges to have himself locked up in jail in order to then escape with an old friend who has been sentenced to the penitentiary.",
    "fullPlot": "In order to free his best friend Bondi, Jack Burns lets himself be imprisoned only to find out that Bondi does not want to escape. Thus Burns breaks out on his own and is afterwards being chased by sheriff Johnson with helicopters and jeeps.",
    "genres": [
      "Drama",
      "Western"
    ]
  },
  {
    "title": "This Island Earth",
    "year": "1955",
    "plot": "Aliens come to Earth seeking scientists to help them in their war.",
    "fullPlot": "Dr. Meacham is chosen along with others by the inhabitants of the planet Metaluna to do research that will help save their dying planet. However, an evil scheme is uncovered by the suspecting Dr. Meacham when he discovers the Metalunan's plan to take over Earth. Dr. Meacham then escapes an exploding Metalunan built Earth lab along with Dr. Adams only to be kidnapped while flying away in a small plane. A flying saucer wisks both the scientists off to Metaluna where they are held accountable for blowing up the Metalunan Earth lab during their escape. They later escape there with the help of Exeter the friendly Metalunan. Metaluna then self destructs and the Doctors make it safely back to Earth, which is saved from Metalunan invasion.",
    "genres": [
      "Horror",
      "Sci-Fi"
    ]
  }
]

```

</details>