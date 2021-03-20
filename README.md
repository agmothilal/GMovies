| URI               | HTTP Method | HTTP Status |      Description     |       Request        |        Response        | 
|-------------------|-------------|-------------|----------------------|----------------------|------------------------|
| /v1/gmdb/movies   | GET  | 200 | Return all Gmdb Movies    |                      | `[{ "name": "Terminator"}]`|
| /v1/gmdb/movie    | POST | 201 | Add new movie to Gmdb movies list | `{ "name": "Teminator" }` | `{ "name": "Teminator" }` |                                                      