package com.galvanize.GMovies.controller;

import com.galvanize.GMovies.dto.GMovieDto;
import com.galvanize.GMovies.service.GmdbMoviesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class GmdbMoviesController {

    private List<GMovieDto> gMoviesDto = new ArrayList<>();

    GmdbMoviesService gmdbMoviesService;

    public GmdbMoviesController(GmdbMoviesService gmdbMoviesService) {
        this.gmdbMoviesService = gmdbMoviesService;
    }

    @GetMapping("/gmdb/movies")
    public ResponseEntity<?> getGmdbMovies() {
        return new ResponseEntity<>(gMoviesDto, HttpStatus.OK);
    }

    @PostMapping("/gmdb/movie")
    public ResponseEntity<?> addGmdbMovie(@RequestBody GMovieDto gMovieDto){
        gmdbMoviesService.addMovie(gMovieDto);
        //gMoviesDto.add(gMovieDto);
        return new ResponseEntity<>(gMovieDto, HttpStatus.CREATED);
    }

    @PostMapping("/gmdb/movies")
    public ResponseEntity<?> addGmdbMovies(@RequestBody List<GMovieDto> gMovieDto){
        gMoviesDto.addAll(gMovieDto);
        return new ResponseEntity<>("added all movies", HttpStatus.CREATED);
    }

    @GetMapping("/gmdb/movie")
    public ResponseEntity<?> getGmdbMovies(@RequestParam String name) {
        Optional<GMovieDto> gMovieDto = gMoviesDto.stream().filter(movie -> movie.getTitle().
                equalsIgnoreCase(name)).findFirst();
        if(gMovieDto.isPresent()){
            return new ResponseEntity<>(gMovieDto.get(), HttpStatus.OK);
        }
       else{
           return new ResponseEntity<>("Movie not found", HttpStatus.NO_CONTENT);
       }
    }
}
