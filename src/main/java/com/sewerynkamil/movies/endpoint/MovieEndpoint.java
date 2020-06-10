package com.sewerynkamil.movies.endpoint;

import com.sewerynkamil.movies.domain.Movie;
import com.sewerynkamil.movies.exception.MovieNotFoundException;
import com.sewerynkamil.movies.movie.*;
import com.sewerynkamil.movies.service.MovieService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;

@Endpoint
public class MovieEndpoint {
    public static final String NAMESPACE_URI = "http://sewerynkamil.pl/movies";

    private MovieService movieService;

    @Autowired
    public MovieEndpoint(MovieService movieService) {
        this.movieService = movieService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMovieByIdRequest")
    @ResponsePayload
    public GetMovieByIdResponse getMovieById(@RequestPayload GetMovieByIdRequest request) throws MovieNotFoundException {
        GetMovieByIdResponse response = new GetMovieByIdResponse();
        Movie movie = movieService.getMovieById(request.getMovieId());
        MovieType movieType = new MovieType();

        if (movie == null) {
            throw new MovieNotFoundException("Invalid movie id " + request.getMovieId());
        }

        BeanUtils.copyProperties(movie, movieType);
        response.setMovieType(movieType);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllMoviesRequest")
    @ResponsePayload
    public GetAllMoviesResponse getAllMovies(@RequestPayload GetAllMoviesRequest request) {
        GetAllMoviesResponse response = new GetAllMoviesResponse();
        List<MovieType> movieTypeList = new ArrayList<>();
        List<Movie> movieList = movieService.getAllMovies();
        for (Movie movie : movieList) {
            MovieType movieType = new MovieType();
            BeanUtils.copyProperties(movie, movieType);
            movieTypeList.add(movieType);
        }
        response.getMovieType().addAll(movieTypeList);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addMovieRequest")
    @ResponsePayload
    public AddMovieResponse addMovie(@RequestPayload AddMovieRequest request) {
        AddMovieResponse response = new AddMovieResponse();
        MovieType newMovieType = new MovieType();
        ServiceStatus serviceStatus = new ServiceStatus();

        Movie newMovie = new Movie(request.getTitle(), request.getCategory());
        Movie savedMovie = movieService.addMovie(newMovie);

        if (savedMovie == null) {
            serviceStatus.setStatusCode("CONFLICT");
            serviceStatus.setMessage("Exception while adding new movie");
        } else {
            BeanUtils.copyProperties(savedMovie, newMovieType);
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content added successfully");
        }

        response.setMovieType(newMovieType);
        response.setServiceStatus(serviceStatus);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateMovieRequest")
    @ResponsePayload
    public UpdateMovieResponse updateMovie(@RequestPayload UpdateMovieRequest request) {
        UpdateMovieResponse response = new UpdateMovieResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        Movie movieFromDB = movieService.getMovieByTitle(request.getTitle());

        if (movieFromDB == null) {
            serviceStatus.setStatusCode("NOT FOUND");
            serviceStatus.setMessage("Movie = " + request.getTitle() + " not found");
        } else {
            movieFromDB.setTitle(request.getTitle());
            movieFromDB.setCategory(request.getCategory());

            boolean flag = movieService.updateMovie(movieFromDB);

            if (!flag) {
                serviceStatus.setStatusCode("CONFLICT");
                serviceStatus.setMessage("Exception while updating movie = " + request.getTitle());
            } else {
                serviceStatus.setStatusCode("SUCCESS");
                serviceStatus.setMessage("Content updated successfully");
            }
        }

        response.setServiceStatus(serviceStatus);
        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteMovieRequest")
    @ResponsePayload
    public DeleteMovieResponse deleteMovie(@RequestPayload DeleteMovieRequest request) {
        DeleteMovieResponse response = new DeleteMovieResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        boolean flag = movieService.deleteMovieById(request.getMovieId());

        if (!flag) {
            serviceStatus.setStatusCode("FAIL");
            serviceStatus.setMessage("Exception while deleting movie id = " + request.getMovieId());
        } else {
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content deleted successfully");
        }

        response.setServiceStatus(serviceStatus);
        return response;
    }
}
