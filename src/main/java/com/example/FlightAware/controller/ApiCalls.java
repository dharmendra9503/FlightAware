package com.example.FlightAware.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;

//This class is responsible for sending HTTP requests using Rapid API calls.
public class ApiCalls {

    //API Call to get list of flights
    public String fprices(Integer adults, String origin, String destination, String departureDate) throws IOException, InterruptedException, ParseException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://skyscanner44.p.rapidapi.com/search-extended?adults="+adults+"&origin="+origin+"&destination="+destination+"&departureDate="+departureDate))
                .header("X-RapidAPI-Key", "5eda3ba939msh59b5687cc41afb9p10aab1jsn2eec0dc11a6e")
                .header("X-RapidAPI-Host", "skyscanner44.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
