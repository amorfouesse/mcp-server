package com.zenika.mcp_server.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PlaceSncf(List<PlaceSummary> places){}
