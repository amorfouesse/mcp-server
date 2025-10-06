package com.zenika.mcp_server.repository.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record JourneySummary(String departure_date_time, String arrival_date_time, int duration) {
}
