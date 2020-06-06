package com.sewerynkamil.movies.service;

import com.sewerynkamil.movies.domain.Movie;
import com.sewerynkamil.movies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    private MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).get();
    }

    public Movie getMovieByTitile(String title) {
        return movieRepository.findByTitle(title);
    }

    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        movieRepository.findAll().forEach(movies::add);
        return movies;
    }

    public Movie addMovie(Movie movie) {
        try {
            return movieRepository.save(movie);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateMovie(Movie movie) {
        try {
            movieRepository.save(movie);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteMovieById(Long id) {
        try {
            movieRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
