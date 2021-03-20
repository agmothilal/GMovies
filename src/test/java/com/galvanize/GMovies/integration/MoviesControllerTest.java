package com.galvanize.GMovies.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.GMovies.dto.GMovieDto;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MoviesControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    /*
    Given the GBDB is empty
    When I visit GMDB movies
    Then I should see no movies
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
        GMovieDto gMovieDto = new GMovieDto("Terminator");
        RequestBuilder rb = post("/v1/gmdb/movie").
                contentType(MediaType.APPLICATION_JSON).
                content(objectMapper.writeValueAsString(gMovieDto));
        mockMvc.perform(rb).
                andExpect(status().isCreated()).
                andExpect(jsonPath("name").value("Terminator"));
    }
}
