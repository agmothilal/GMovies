package com.galvanize.GMovies.unitTest;

import com.galvanize.GMovies.dto.GMovieDto;
import com.galvanize.GMovies.entity.GMovieEntity;
import com.galvanize.GMovies.repository.GmdbMoviesRepository;
import com.galvanize.GMovies.service.GmdbMoviesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MoviesServiceTest {

    @Mock
    GmdbMoviesRepository repository;

    @InjectMocks
    GmdbMoviesService service;

    @Test
    public void addMovieTest() {
        GMovieDto gMovieDto = new GMovieDto("Terminator", "", "", 0, "", null);
        service.addMovie(gMovieDto);

        GMovieEntity entity = new GMovieEntity();
        entity.setTitle(gMovieDto.getTitle());
        entity.setActors(gMovieDto.getActors());
        entity.setDescription(gMovieDto.getDescription());
        entity.setRating(gMovieDto.getRating());
        entity.setRelease(gMovieDto.getRelease());
        entity.setDirector(gMovieDto.getDirector());

        verify(repository).save(entity);
    }

}
