package com.galvanize.GMovies.service;

import com.galvanize.GMovies.dto.GMovieDto;
import com.galvanize.GMovies.entity.GMovieEntity;
import com.galvanize.GMovies.repository.GmdbMoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GmdbMoviesService {

    @Autowired
    private final GmdbMoviesRepository moviesRepository;

    public GmdbMoviesService(GmdbMoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }


    public void addMovie(GMovieDto gMovieDto) {
        GMovieEntity entity = new GMovieEntity();
        entity.setTitle(gMovieDto.getTitle());
        entity.setActors(gMovieDto.getActors());
        entity.setDescription(gMovieDto.getDescription());
        entity.setRating(gMovieDto.getRating());
        entity.setRelease(gMovieDto.getRelease());
        entity.setDirector(gMovieDto.getDirector());

        this.moviesRepository.save(entity);
    }
}
