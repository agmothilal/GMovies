package com.galvanize.GMovies.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.GMovies.dto.GMovieDto;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MoviesControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    /**
     *  Given the GBDB is empty
     *     When I visit GMDB movies
     *     Then I should see no movies
     */
    @Test
    public void checkGMDBMoviesAreEmptyTest() throws Exception {
        RequestBuilder rb = get("/v1/gmdb/movies")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(rb)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isEmpty())
            .andDo(print());
    }

    /**
     * Given a new movie has released
     * When I submit this new movie to GMDB movies
     * Then I should see that movie in GMDB movies
     */

    @Test
    public void addGMDBMoviesTest() throws Exception {
        GMovieDto gMovieDto = new GMovieDto("Terminator","", "", 0, "", null);
        RequestBuilder rb = post("/v1/gmdb/movie").
                contentType(MediaType.APPLICATION_JSON).
                content(objectMapper.writeValueAsString(gMovieDto));

        mockMvc.perform(rb).
                andExpect(status().isCreated()).
                andExpect(jsonPath("title").value("Terminator"));
    }

    /**
     * Given the GMDB has many movies
     * When I visit GMDB movies
     * Then I should see all the movies in GMDB movies
     */
    @Test
    public void checkGmdbMoviesTest() throws Exception {
        GMovieDto gMovieDto = new GMovieDto("Terminator","", "", 0, "", null);

        RequestBuilder postRequest = post("/v1/gmdb/movie").
                contentType(MediaType.APPLICATION_JSON).
                content(objectMapper.writeValueAsString(gMovieDto));
        RequestBuilder getRequest = get("/v1/gmdb/movies")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(postRequest).
                andExpect(status().isCreated());
        mockMvc.perform(getRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("length()").value(1))
                .andExpect(jsonPath("[0].title").value("Terminator"))
                .andDo(print());
    }

    /**
     * Given the GMDB has a movie
     * When I visit GMDB movies
     * Then I should see that movie in GMDB movies
     */
    @Test
    public void checkGMDBMovieTest() throws Exception {
        List<GMovieDto> movieList = Arrays.asList(
                new GMovieDto("Terminator","", "", 0, "", null),
                new GMovieDto("Jurassic Park","", "", 0, "", null),
                new GMovieDto("Batman","", "", 0, "", null)
        );

        RequestBuilder postRequest = post("/v1/gmdb/movies").
                contentType(MediaType.APPLICATION_JSON).
                content(objectMapper.writeValueAsString(movieList));
        mockMvc.perform(postRequest).
                andExpect(status().isCreated()).
                andExpect(content().string("added all movies"));

        RequestBuilder getRequest = get("/v1/gmdb/movie?name=Terminator")
                .contentType(MediaType.APPLICATION_JSON);
        RequestBuilder getRequest1 = get("/v1/gmdb/movie?name=SuperMan")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(getRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("title").value("Terminator"))
                .andDo(print());
        mockMvc.perform(getRequest1)
                .andExpect(status().isNoContent())
                .andExpect(content().string("Movie not found"))
                .andDo(print());
    }

    /**
     * Rule: Movie details include title, director, actors, release year, description and star rating.
     *
     * Given the GMDB has many movies
     * When I visit GMDB movies with an existing title
     * Then I should see that movie's details
     */
    @Test
    public void getMovieByTitleTest() throws Exception {
        List<GMovieDto> movieList = Arrays.asList(
                new GMovieDto("The Avengers","Joss Whedon", "Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth", 2012, "Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.", null),
                new GMovieDto("Superman Returns","Bryan Singer", "Brandon Routh, Kate Bosworth, Kevin Spacey, James Marsden", 2006, "Superman returns to Earth after spending five years in space examining his homeworld Krypton. But he finds things have changed while he was gone, and he must once again prove himself important to the world.", null)
        );

        RequestBuilder postRequest = post("/v1/gmdb/movies").
                contentType(MediaType.APPLICATION_JSON).
                content(objectMapper.writeValueAsString(movieList));
        mockMvc.perform(postRequest).
                andExpect(status().isCreated()).
                andExpect(content().string("added all movies"));

        RequestBuilder getRequest = get("/v1/gmdb/movie?name=Superman Returns")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(getRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("title").value("Superman Returns"))
                .andExpect(jsonPath("director").value("Bryan Singer"))
                .andExpect(jsonPath("actors").value("Brandon Routh, Kate Bosworth, Kevin Spacey, James Marsden"))
                .andExpect(jsonPath("release").value(2006))
                .andExpect(jsonPath("description").value("Superman returns to Earth after spending five years in space examining his homeworld Krypton. But he finds things have changed while he was gone, and he must once again prove himself important to the world."))
                .andExpect(jsonPath("rating").value(IsNull.nullValue()));
    }

    /**
     * Given the GMDB has many movies
     * When I visit GMDB movies with a non-existing title
     * Then I receive a friendly message that it doesn't exist
     */
    @Test
    public void getMovieByTitleIsNotFoundTest() throws Exception {
        List<GMovieDto> movieList = Arrays.asList(
                new GMovieDto("The Avengers","Joss Whedon", "Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth", 2012, "Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.", null),
                new GMovieDto("Superman Returns","Bryan Singer", "Brandon Routh, Kate Bosworth, Kevin Spacey, James Marsden", 2006, "Superman returns to Earth after spending five years in space examining his homeworld Krypton. But he finds things have changed while he was gone, and he must once again prove himself important to the world.", null)
        );

        RequestBuilder postRequest = post("/v1/gmdb/movies").
                contentType(MediaType.APPLICATION_JSON).
                content(objectMapper.writeValueAsString(movieList));
        mockMvc.perform(postRequest).
                andExpect(status().isCreated()).
                andExpect(content().string("added all movies"));

        RequestBuilder getRequest = get("/v1/gmdb/movie?name=Steel")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(getRequest)
                .andExpect(status().isNoContent())
                .andExpect(content().string("Movie not found"));
    }
}
