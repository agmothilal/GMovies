package com.galvanize.GMovies.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class GmdbMoviesController {

    @GetMapping("/gmdb/movies")
    @ResponseStatus(HttpStatus.OK)
    public String getGmdbMovies() {
        return "{}";
    }

}
