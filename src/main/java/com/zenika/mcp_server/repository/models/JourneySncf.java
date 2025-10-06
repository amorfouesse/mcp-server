package com.zenika.mcp_server.repository.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record JourneySncf(List<JourneySummary> journeys) {
}
