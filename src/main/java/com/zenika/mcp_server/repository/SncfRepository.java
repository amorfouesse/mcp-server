package com.zenika.mcp_server.repository;

import com.zenika.mcp_server.models.Journey;
import com.zenika.mcp_server.repository.models.JourneySncf;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

import java.util.List;

@Repository
public class SncfRepository {
    private final RestClient restClient;

    public SncfRepository(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<Journey> getJourneys(String startCoordinate, String endCoordinate) {
        var journeySncf = restClient.get()
                .uri("/journeys?from=-1.672438%3B48.103392&to=-1.21154%3B48.12273&datetime=20251002T113906&")
                .retrieve()
                .body(JourneySncf.class);
    return List.of(new Journey("","","45"));
    }
}
