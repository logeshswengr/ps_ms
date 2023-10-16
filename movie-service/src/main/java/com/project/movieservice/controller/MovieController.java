package com.project.movieservice.controller;

import com.project.movieservice.dto.MovieRequest;
import com.project.movieservice.dto.MovieResponse;
import com.project.movieservice.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping(value = "/" )
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MovieResponse> save(@RequestBody MovieRequest request) throws Exception {
        MovieResponse response = movieService.saveMovie(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MovieResponse> get(@PathVariable Integer id) throws Exception {
        MovieResponse response = movieService.getMovie(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<MovieResponse> update(@PathVariable Integer id, @RequestBody MovieRequest request) throws Exception {
        MovieResponse response = movieService.updateMovie(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Integer id) throws Exception {
        movieService.deleteMovie(id);
        return ResponseEntity.ok(null);
    }
}
