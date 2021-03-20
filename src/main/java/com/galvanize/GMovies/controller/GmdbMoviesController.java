package com.galvanize.GMovies.controller;

import com.galvanize.GMovies.dto.GMovieDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class GmdbMoviesController {

    @GetMapping("/gmdb/movies")
    public ResponseEntity<?> getGmdbMovies() {
        List<GMovieDto> gMoviesDto = new ArrayList<>();
        return new ResponseEntity<>(gMoviesDto, HttpStatus.OK);
    }

}
