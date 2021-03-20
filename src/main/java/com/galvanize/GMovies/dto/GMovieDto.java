package com.galvanize.GMovies.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GMovieDto {
    private String title;
    private String director;
    private String actors;
    private Integer release;
    private String description;
    private Integer rating;
}
