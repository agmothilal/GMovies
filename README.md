| URI               | HTTP Method | HTTP Status |      Description     |       Request        |        Response        | 
|-------------------|-------------|-------------|----------------------|----------------------|------------------------|
| /v1/gmdb/movies   | GET  | 200 | Return all Gmdb Movies    |                      | `[{ "name": "Terminator"}]`|
| /v1/gmdb/movie    | POST | 201 | Add new movie to Gmdb movies list | `{ "name": "Teminator" }` | `{ "name": "Teminator" }` |
| /v1/gmdb/movies   | POST | 201 | Add multiple movies to movies list | `[{ "name": "Teminator" }]` | "added all movies" |
| /v1/gmdb/movie    | GET  | 200 | Return movie by name | ?name=Terminator | `{ "name": "Teminator" }` |
| /v1/gmdb/movie    | GET  | 204 | Return movie not found | ?name=Terminator | "Movie not found" |
| /v1/gmdb/movie    | PUT  | 200 | Update movie rating | `{ "name": "Teminator", "rating": 5 }` | `{ "name": "Teminator", "rating": 5 }` |
