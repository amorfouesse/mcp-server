package com.zenika.mcp_server.service;

import com.zenika.mcp_server.repository.SncfRepository;
import com.zenika.mcp_server.repository.model.JourneySummary;
import com.zenika.mcp_server.repository.model.PlaceSummary;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class SncfService{
    private final SncfRepository sncfRepository;

    public SncfService(SncfRepository sncfRepository) {
        this.sncfRepository = sncfRepository;
    }

    @Tool(name = "get_journey", description = "Get a journey for a route")
    public JourneySummary getJourney(String startCity, String endCity) {
       var journeys = sncfRepository.getJourneys(startCity,endCity);
       if(journeys.isEmpty()){
           throw new RuntimeException("Journey not found");
       }
       return journeys.getFirst();
    }

    @Tool(name = "get_place", description = "Get a coordinate for a city")
    public PlaceSummary getPlace(String city) {
        var places = sncfRepository.getPlaces(city);
        return places.map(place -> place.stream()
                    .sorted(Comparator.comparingInt(PlaceSummary::quality).reversed())
                    .filter(placeSummary ->
                            placeSummary.embedded_type().equals("stop_area")
                    ).toList().getFirst()).
                orElseThrow(() -> new RuntimeException("Place not found"));
    }

}