package com.project.movieservice.service;

import com.project.movieservice.dto.MovieRequest;
import com.project.movieservice.dto.MovieResponse;
import com.project.movieservice.entity.Movie;
import com.project.movieservice.repositories.MovieRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public MovieResponse saveMovie(MovieRequest request) throws Exception {
        Movie movieDbObj = movieRepository.save(new Movie(null, request.getName(), request.getDescription(),
                request.getDurationMint(), request.getGenre()));
        return new MovieResponse(movieDbObj.getId(), movieDbObj.getName(), movieDbObj.getDescription(),
                movieDbObj.getDurationMint(), movieDbObj.getGenre());
    }

    public MovieResponse getMovie(Integer id) throws Exception {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isEmpty()) {
            throw new EntityNotFoundException("Movie not found");
        }
        Movie movieDbObj = movie.get();
        return new MovieResponse(movieDbObj.getId(), movieDbObj.getName(), movieDbObj.getDescription(),
                movieDbObj.getDurationMint(), movieDbObj.getGenre());
    }

    public MovieResponse updateMovie(Integer id, MovieRequest request) throws Exception {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isEmpty()) {
            throw new EntityNotFoundException("Movie not found");
        }
        Movie movieDb = movie.get();
        movieDb.setName(request.getName());
        movieDb.setDescription(request.getDescription());
        movieDb.setDurationMint(request.getDurationMint());
        movieDb.setGenre(request.getGenre());
        Movie movieSaved = movieRepository.save(movieDb);
        return new MovieResponse(movieSaved.getId(), movieSaved.getName(), movieSaved.getDescription(),
                 movieSaved.getDurationMint(), movieSaved.getGenre());
    }

    public void deleteMovie(Integer id) throws Exception {
        movieRepository.deleteById(id);
    }
}
