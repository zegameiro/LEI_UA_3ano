package com.example.restservice.repository;

import com.example.restservice.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {
    Movie findById(long id);
    void deleteById(long id);
}
