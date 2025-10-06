package com.zenika.mcp_server.repository;

import com.zenika.mcp_server.models.Journey;
import com.zenika.mcp_server.repository.models.JourneySncf;
import com.zenika.mcp_server.repository.models.JourneySummary;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.util.List;

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
}
