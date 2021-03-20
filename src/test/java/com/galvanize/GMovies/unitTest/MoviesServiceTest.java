package com.galvanize.GMovies.unitTest;

import com.galvanize.GMovies.dto.GMovieDto;
import com.galvanize.GMovies.entity.GMovieEntity;
import com.galvanize.GMovies.repository.GmdbMoviesRepository;
import com.galvanize.GMovies.service.GmdbMoviesService;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @Test
    public void fetchAllMoviesTest() {
        GMovieEntity entity1 = new GMovieEntity("The Avengers", "", "", 2019, "", null);
        GMovieEntity entity2 = new GMovieEntity("Superman Returns", "", "", 2019, "", null);

        when(repository.findAll()).thenReturn(Arrays.asList(entity1, entity2));

        List<GMovieDto> moviesDto = service.getAllMovies();

        GMovieDto dto1 = new GMovieDto();
        dto1.setTitle(entity1.getTitle());
        dto1.setActors(entity1.getActors());
        dto1.setDescription(entity1.getDescription());
        dto1.setRating(entity1.getRating());
        dto1.setRelease(entity1.getRelease());
        dto1.setDirector(entity1.getDirector());

        GMovieDto dto2 = new GMovieDto();
        dto2.setTitle(entity2.getTitle());
        dto2.setActors(entity2.getActors());
        dto2.setDescription(entity2.getDescription());
        dto2.setRating(entity2.getRating());
        dto2.setRelease(entity2.getRelease());
        dto2.setDirector(entity2.getDirector());

        assertThat(moviesDto).isEqualTo(Arrays.asList(dto1, dto2));
    }

    @Test
    public void updateMovieTest(){
        GMovieEntity entity1 = new GMovieEntity("The Avengers", "", "", 2019, "", null);
        entity1.setId(123l);
        GMovieEntity entity2 = new GMovieEntity("Superman Returns", "", "", 2019, "", null);

        when(repository.findAll()).thenReturn(Arrays.asList(entity1, entity2));

        when(repository.findById(any(Long.class))).thenReturn(Optional.of(entity1));
       GMovieDto movieDto= service.updateMovie(new GMovieDto("The Avengers", "", "", 2019, "", 5));
       verify(repository).save(entity1);
       assertThat(movieDto).isEqualTo(new GMovieDto("The Avengers", "", "", 2019, "", 5));
    }
}
