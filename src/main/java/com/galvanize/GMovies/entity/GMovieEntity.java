package com.galvanize.GMovies.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class GMovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String director;
    private String actors;
    private Integer release;
    private String description;
    private Integer rating;

    public GMovieEntity(String title, String director, String actors,
                        Integer release, String description,
                        Integer rating) {
        this.title = title;
        this.director = director;
        this.actors = actors;
        this.release = release;
        this.description = description;
        this.rating = rating;
    }
}
