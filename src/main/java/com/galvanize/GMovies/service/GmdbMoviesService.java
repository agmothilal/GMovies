package com.galvanize.GMovies.service;

import com.galvanize.GMovies.dto.GMovieDto;
import com.galvanize.GMovies.entity.GMovieEntity;
import com.galvanize.GMovies.repository.GmdbMoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<GMovieDto> getAllMovies() {
        return this.moviesRepository.findAll().stream().
                map(movie -> new GMovieDto(movie.getTitle(),
                        movie.getDirector(),
                        movie.getActors(),
                        movie.getRelease(),
                        movie.getDescription(),
                        movie.getRating())).collect(Collectors.toList());
    }

    public void addMovies(List<GMovieDto> gMoviesDto) {
        List<GMovieEntity> entities = new ArrayList<>();

        for(GMovieDto gMovieDto: gMoviesDto) {
            GMovieEntity entity = new GMovieEntity();
            entity.setTitle(gMovieDto.getTitle());
            entity.setActors(gMovieDto.getActors());
            entity.setDescription(gMovieDto.getDescription());
            entity.setRating(gMovieDto.getRating());
            entity.setRelease(gMovieDto.getRelease());
            entity.setDirector(gMovieDto.getDirector());
            entities.add(entity);
        }

        this.moviesRepository.saveAll(entities);
    }

    public GMovieDto updateMovie(GMovieDto gMovieDto) {
        GMovieEntity gEntity = moviesRepository.findAll().stream().
                filter(entity -> entity.getTitle().equalsIgnoreCase(gMovieDto.getTitle())).findFirst().get();
        Integer averageRating = gEntity.getRating() == null ?
                gMovieDto.getRating() :
                (gEntity.getRating() + gMovieDto.getRating()) / 2;

        gEntity.setRating(averageRating);
        this.moviesRepository.save(gEntity);

        return ConvertToDto(this.moviesRepository.findById(gEntity.getId()).get());
    }

    private static GMovieDto ConvertToDto(GMovieEntity entity) {
        return new GMovieDto(entity.getTitle(),
                entity.getDirector(),
                entity.getActors(),
                entity.getRelease(),
                entity.getDescription(),
                entity.getRating());
    }
    private GMovieEntity ConvertToEntity(GMovieDto dto) {
        return new GMovieEntity(dto.getTitle(),
                dto.getDirector(),
                dto.getActors(),
                dto.getRelease(),
                dto.getDescription(),
                dto.getRating());
    }
}
