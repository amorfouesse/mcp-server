package com.zenika.mcp_server;

import com.zenika.mcp_server.models.Journey;
import com.zenika.mcp_server.repository.SncfRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SncfService{
    private SncfRepository sncfRepository;

    public SncfService(SncfRepository sncfRepository) {
        this.sncfRepository = sncfRepository;
    }

    @Tool(name = "get_journeys", description = "Get a list of journeys for a route")
    public List<Journey> getJourneys(String startCity, String endCity) {
      // return sncfRepository.getJourneys(startCity,endCity);
        return List.of(new Journey(startCity,endCity,"35 minutes"));
    }

}