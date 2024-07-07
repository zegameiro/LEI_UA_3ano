package com.example.restservice;

import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShowController {
    
    Show[] shows = {
        new Show(0, "Some Like it Hot", "Well nobody is perfect"),
        new Show(1, "Monty Python and the Holy Grail", "It’s just a flesh wound."),
        new Show(2, "The Lord of the Rings: The Two Towers", "But in the end it’s only a passing thing, this shadow, even darkness must pass."),
        new Show(3, "Sweeney Todd: The Demon Barber of Fleet Street (2007)", "They all deserve to die. Even you, Mrs. Lovett. Even I. Because the lives of the wicked should be made brief. For the rest of us death would be relief."),
        new Show(4, "Devil wears Prada (2006)", "I'm just one stomach flu away from my goal weight."),
        new Show(5, "Raiders of the Lost Ark (1981)", "It's not the years, honey. It's the mileage."),
        new Show(6, "Mean Girls (2004)", "That's why her hair is so big. It's full of secrets."),
        new Show(7, "Harry Potter and the Order of the Phoenix (2007)", "We’ve all got both light and darkness inside us. What matters is the part we choose to act on. That’s who we really are."),
        new Show(8, "The Raid Redemption (2011)", "Go to work and have a fun."),
        new Show(9, "The Magic of Belle Isle (2012)", "Never Stop Looking for What's Not There."),
        new Show(10, "Mission Impossible: fallout (2018)", "What’s done is done, when we say it’s done."),
        new Show(11, "About Time (2013)", "I just try to live every day as if it was the final day of my extraordinary, ordinary life."),
        new Show(12, "Avengers: Endgame (2019)", "No amount of money ever bought a second of time.")
    };

    @GetMapping("/quote")
    public String quote() {
        return shows[new Random().nextInt(shows.length - 1) + 1].getQuote();
    }

    @GetMapping("/shows")
    public Show[] shows() {
        return shows;
    }

    @GetMapping("/quotes")
    public String quoteMovie(@RequestParam(value = "show") int id) {
        return shows[id-1].getQuote();
    }
}
