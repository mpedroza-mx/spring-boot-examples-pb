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
curl -X POST -H "Accept:application/json" -H "Content-Type:application/json" http://localhost:6060/api/movies/semantic-search -d @./spring-boot-solr-movies-search-service/requests/semanticSearch/semantic-search-request-body.json | jq '[.movies[] | {title, year, plot, plot,fullPlot, genres}]'
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
    "title": "Rocketship X-M",
    "year": "1950",
    "plot": "An astronaut crew of 4 men and one woman on their way to the Moon, are unexpectedly propelled by gravitational forces and end up on Mars instead.",
    "fullPlot": "Astronauts (Lloyd Bridges, Osa Massen, John Emery, Noah Beery, Jr., and Hugh O'Brien) blast off to explore the moon. Because of craft malfunction and some fuel calculations, they end up landing on Mars. On Mars, evidence of a once powerful civilization is found. The scientists determine that an atomic war destroyed most of the Martians (who surprisingly look like humans). Those that survived reverted to a caveman-like existence.",
    "genres": [
      "Sci-Fi"
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
    "title": "Invasion of the Body Snatchers",
    "year": "1956",
    "plot": "A small-town doctor learns that the population of his community is being replaced by emotionless alien duplicates.",
    "fullPlot": "Dr Miles Bennell returns his small town practice to find several of his patients suffering the paranoid delusion that their friends or relatives are impostors. He is initially skeptical, especially when the alleged dopplegèngers are able to answer detailed questions about their victim's lives, but he is eventually persuaded that something odd has happened and determines to find out what is causing this phenomenon. This film can be seen as a paranoid 1950s warning against those Damn Commies or, conversely, as a metaphor for the tyranny of McCarthyism (or the totalitarian system of Your Choice) and has a pro- and epilogue that was forced upon Siegel by the studio to lighten the tone.",
    "genres": [
      "Horror",
      "Sci-Fi"
    ]
  },
  {
    "title": "A Phantasy",
    "year": "1952",
    "plot": "An animated film drawn entirely in pastels. Various fantastical plant-like things \"grow\" from the ground, eventually launching five spheres. The spheres drift in space while changing shapes...",
    "fullPlot": "An animated film drawn entirely in pastels. Various fantastical plant-like things \"grow\" from the ground, eventually launching five spheres. The spheres drift in space while changing shapes and come back down to another setting, which eventually becomes more fantastical and symbolic than the opening one. The soundtrack has a jazz slant, with an ensemble of four saxophones and synthetic sound (i.e. sound created by drawing directly on the soundtrack).",
    "genres": [
      "Animation",
      "Short"
    ]
  },
  {
    "title": "The 10th Victim",
    "year": "1965",
    "plot": "Some people like violence so much, that they decide to create a club in which human hunts are organized - members being alternately hunters, and prey, until they end up dead.",
    "fullPlot": "A campy futuristic tale where people hunt one another for sport. In this film, Victim and Hunter run around Italy trying to score a kill in front of the movie crews they arranged so they could make commercials from the footage.",
    "genres": [
      "Action",
      "Sci-Fi"
    ]
  },
  {
    "title": "Invaders from Mars",
    "year": "1953",
    "plot": "A young boy learns that space aliens are taking over the minds of earthlings.",
    "fullPlot": "One night, young David McLean sees a spaceship crash into a nearby sandpit. His father goes to investigate, but comes back changed. Where once he was cheerful and affectionate, he's now sullen and snarlingly rude. Others fall into the sandpit and begin acting like him: cold, ill-tempered and conspiratorial. David knows that aliens are taking over the bodies of humans, but he'll soon discover there have been far more of these terrible thefts than he could have imagined. The young doom-monger finds some serious help in a lady doctor and a brilliant astronomer. Soon they meet the aliens: green creatures with insect-like eyes. These beings prove to be slaves to their leader: a large, silent head with ceaselessly shifting eyes and two tentacles on either side, each of which branches off into three smaller tentacles. It's up to the redoubtable earth trio to stop its evil plans.",
    "genres": [
      "Horror",
      "Sci-Fi"
    ]
  },
  {
    "title": "The War of the Worlds",
    "year": "1953",
    "plot": "The film adaptation of the H.G.Wells story told on radio of the invasion of Earth by Martians.",
    "fullPlot": "H.G. Well's classic novel is brought to life is this tale of alien invasion. The residents of a small town in California are excited when a flaming meteor lands in the hills. Their joy is tempered somewhat when they discover that it has passengers who are not very friendly. The movie itself is understood better when you consider that it was made at the height of the Cold War--just replace Martian with Russian....",
    "genres": [
      "Action",
      "Sci-Fi",
      "Thriller"
    ]
  }
]


```

</details>