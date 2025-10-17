package com.zenika.mcp_server.repository;

import com.zenika.mcp_server.repository.model.JourneySncf;
import com.zenika.mcp_server.repository.model.JourneySummary;
import com.zenika.mcp_server.repository.model.PlaceSncf;
import com.zenika.mcp_server.repository.model.PlaceSummary;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class SncfRepository {
    private final RestClient restClient;

    public SncfRepository(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<JourneySummary> getJourneys(String startCoordinate, String endCoordinate) {
        var response = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("journeys")
                        .queryParam("from",startCoordinate)
                        .queryParam("to",endCoordinate)
                        .queryParam("datetime", LocalDateTime.now())
                        .build())
                .retrieve()
                .body(JourneySncf.class);

        return response != null ? response.journeys() : List.of();
    }

    public Optional<List<PlaceSummary>> getPlaces(String city) {
        var response = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("places")
                        .queryParam("q",city)
                        .build())
                .retrieve()
                .body(PlaceSncf.class);



        return response != null ? Optional.of(response.places()) : Optional.of(List.of());
    }
}
