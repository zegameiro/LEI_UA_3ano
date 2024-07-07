package com.example.restservice.services;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restservice.models.Movie;
import com.example.restservice.models.Quote;
import com.example.restservice.repository.MovieRepository;
import com.example.restservice.repository.QuoteRepository;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private QuoteRepository quoteRepository;

    public void deleteQuote(Long quoteId) {
        quoteRepository.deleteById(quoteId);
    }

    public Quote updateQuote(Quote quote)  {
        return quoteRepository.save(quote);
    }

    public List<Quote> getAllQuotes() {
        return quoteRepository.findAll();
    }

    public Quote getRandomQuoteByMovieId(Long movieId) {
        List<Long> quotes= new ArrayList<>();
        Movie movie = movieRepository.findById(movieId);
        for(Quote q : quoteRepository.findAll()) {
            if(q.getMovie().getTitle().equals(movie.getTitle())) {
                quotes.add(q.getId());
            }
        }
        Random random = new Random();
        return quoteRepository.findById(quotes.get(random.nextInt(quotes.size()))).orElse(null);
    }

    public Quote getQuoteById(Long quoteId) {
        return quoteRepository.findById(quoteId).get();
    }

    public Quote getRandomQuoteRandomMovie() {
        ArrayList<Long> ids = new ArrayList<>();
        for(Quote q : quoteRepository.findAll())  {
            ids.add(q.getId());
        }
        Random random = new Random();
        return quoteRepository.findById(ids.get(random.nextInt(ids.size()))).get();
    }

    public Quote createQuote(Quote quote) {
        return quoteRepository.save(quote);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie getMovieById(Long movieId) {
        return movieRepository.findById(movieId);
    }

    public Movie updateMovie(Movie movie)  {
        return movieRepository.save(movie);
    }

    public void deleteMovie(Long movieId) {
        movieRepository.deleteById(movieId);
    }
}
