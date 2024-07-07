package com.example.restservice;

public class Show {

    private final long id;
    private final String name;
    private final String quote;

    public Show(long id, String name, String quote) {
        this.id = id;
        this.name = name;
        this.quote = quote;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getQuote() {
        return quote;
    }
}
