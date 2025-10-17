package com.zenika.mcp_server.service;

import com.zenika.mcp_server.repository.SncfRepository;
import com.zenika.mcp_server.repository.model.JourneySummary;
import com.zenika.mcp_server.repository.model.PlaceSummary;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.zenika.mcp_server.SncfConstant.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // Active Mockito pour ce test
class SncfServiceTest {
    @InjectMocks
    private SncfService sncfService;

    @Mock
    private SncfRepository sncfRepository;

    @Test
    void getJourneyOk() {
        //GIVEN
        List<JourneySummary> expectedJourneys = List.of(
                new JourneySummary("20251003T183500","20251003T201500",6000 )
        );
        when(sncfRepository.getJourneys(RENNES_COORDINATE,PARIS_COORDINATE)).thenReturn(expectedJourneys);

        //WHEN
        var actualResponse = sncfService.getJourney(RENNES_COORDINATE, PARIS_COORDINATE);

        //THEN
        assertThat(expectedJourneys.getFirst()).isEqualTo(actualResponse);
    }

    @Test
    void getJourneyEmptyListKo() {
        //GIVEN
        when(sncfRepository.getJourneys(RENNES_COORDINATE,PARIS_COORDINATE)).thenReturn(List.of());

        //WHEN and THEN
        assertThrows(RuntimeException.class, ()-> {
            sncfService.getJourney(RENNES_COORDINATE, PARIS_COORDINATE);
        });
    }

    @Test
    void getPlaceOK() {
        //GIVEN
        List<PlaceSummary> expectedPlaces = List.of(
                new PlaceSummary(BAD_RENNES_COORDINATE,"stop_area",80 ),
        new PlaceSummary(BAD_RENNES_COORDINATE,"plein_area",100 ),
                new PlaceSummary(RENNES_COORDINATE,"stop_area",100 )
        );

        when(sncfRepository.getPlaces(RENNES_CITY)).thenReturn(Optional.of(expectedPlaces));

        //WHEN
        var actualResponse = sncfService.getPlace(RENNES_CITY);

        //THEN
        assertThat(expectedPlaces.getLast()).isEqualTo(actualResponse);
    }

    @Test
    void getPlaceEmptyListKo() {
        //GIVEN
        when(sncfRepository.getPlaces(RENNES_CITY)).thenReturn(Optional.of(List.of()));

        //WHEN and THEN
        assertThrows(RuntimeException.class, ()-> {
            sncfService.getPlace(RENNES_CITY);
        });
    }
}