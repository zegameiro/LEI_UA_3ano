package com.example.restservice.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "quotes")
public class Quote {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Movie movie;

    @Column(name = "quote", nullable = false)
    private String quote;

    public Quote(){}

    public Quote(Movie movie, String quote) {
        this.movie = movie;
        this.quote = quote;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie m) {
        this.movie = m;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String q) {
        this.quote = q;
    } 

    @Override
    public String toString() {
        return "Quote " + id + " { Movie = " + movie + movie.getTitle() + ", Quote = " + quote + " }";
    }
}
