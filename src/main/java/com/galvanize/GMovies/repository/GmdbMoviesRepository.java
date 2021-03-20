package com.galvanize.GMovies.repository;

import com.galvanize.GMovies.entity.GMovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GmdbMoviesRepository extends JpaRepository<GMovieEntity, Long> {
}
