package com.jdcg.movies.services;

import com.jdcg.movies.models.Movie;
import com.jdcg.movies.repositories.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private IMovieRepository iMovieRepository;
    public List<Movie> allMovies(){
        return iMovieRepository.findAll();
    }

    public Optional<Movie> singleMovie(String imdbId) {
        return iMovieRepository.findMovieByImdbId(imdbId);
    }

    public  Optional<Movie> movieByTitle(String title) {
        return iMovieRepository.findFirstByTitle(title);
    }
}
