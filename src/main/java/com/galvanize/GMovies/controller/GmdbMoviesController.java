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

    private List<GMovieDto> gMoviesDto = new ArrayList<>();

    @GetMapping("/gmdb/movies")
    public ResponseEntity<?> getGmdbMovies() {
        return new ResponseEntity<>(gMoviesDto, HttpStatus.OK);
    }

    @PostMapping("/gmdb/movie")
    public ResponseEntity<?> addGmdbMovie(@RequestBody GMovieDto gMovieDto){
        gMoviesDto.add(gMovieDto);
        return new ResponseEntity<>(gMovieDto, HttpStatus.CREATED);
    }
}
