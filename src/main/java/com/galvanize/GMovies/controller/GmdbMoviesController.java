package com.galvanize.GMovies.controller;

import com.galvanize.GMovies.dto.GMovieDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class GmdbMoviesController {

    @GetMapping("/gmdb/movies")
    public ResponseEntity<?> getGmdbMovies() {
        List<GMovieDto> gMoviesDto = new ArrayList<>();
        return new ResponseEntity<>(gMoviesDto, HttpStatus.OK);
    }

    @PostMapping("/gmdb/movie")
    public ResponseEntity<?> addGmdbMovie(@RequestBody GMovieDto gMovieDto){
        return new ResponseEntity<>(gMovieDto,HttpStatus.CREATED);
    }
}
