package com.zenika.mcp_server.repository;

import com.zenika.mcp_server.repository.model.JourneySncf;
import com.zenika.mcp_server.repository.model.JourneySummary;
import com.zenika.mcp_server.repository.model.PlaceSncf;
import com.zenika.mcp_server.repository.model.PlaceSummary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static com.zenika.mcp_server.SncfConstant.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // Active Mockito pour ce test
class SncfRepositoryTest {

    @InjectMocks
    private SncfRepository sncfRepository;

    @Mock
    private RestClient restClient;

    // Ces mocks sont nécessaires pour simuler la chaîne d'appels de RestClient
    @Mock private RestClient.RequestHeadersUriSpec requestHeadersUriSpec;
    @Mock private RestClient.RequestHeadersSpec requestHeadersSpec;
    @Mock private RestClient.ResponseSpec responseSpec;


    @BeforeEach
    void setUp() {
        when(restClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(any(Function.class)))
                .thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
    }


    @Test
    void getJourneysOK() {
        //GIVEN
        List<JourneySummary> expectedJourneys = List.of(
                new JourneySummary("20251003T183500","20251003T201500",6000 )
        );

        when(responseSpec.body(JourneySncf.class)).thenReturn(new JourneySncf(expectedJourneys));

        //WHEN
        var response = sncfRepository.getJourneys(RENNES_COORDINATE, PARIS_COORDINATE);

        //THEN
        assertThat(response).isEqualTo(expectedJourneys);
    }

    @Test
    void getJourneysApiReturnNullKO() {
        // GIVEN
        when(responseSpec.body(JourneySncf.class)).thenReturn(null);

        // WHEN
        List<JourneySummary> actualJourneys = sncfRepository.getJourneys(RENNES_COORDINATE, PARIS_COORDINATE);

        //THEN
        assertNotNull(actualJourneys);
        assertEquals(0, actualJourneys.size());
    }


    @Test
    void getPlacesOK() {
        //GIVEN
        List<PlaceSummary> expectedPlaces = List.of(
                new PlaceSummary(RENNES_COORDINATE,"stop_area",100 )
        );

        when(responseSpec.body(PlaceSncf.class)).thenReturn(new PlaceSncf(expectedPlaces));

        //WHEN
        var actualResponse = sncfRepository.getPlaces(RENNES_CITY);

        //THEN
        assertThat(actualResponse).isEqualTo(Optional.of(expectedPlaces));
    }

    @Test
    void getPlacesApiReturnNullKO() {
        //GIVEN
        when(responseSpec.body(PlaceSncf.class)).thenReturn(null);

        //WHEN
        var actualResponse = sncfRepository.getPlaces(RENNES_CITY);

        //THEN
        assertNotNull(actualResponse);
        assertThat(actualResponse).isEqualTo(Optional.of(List.of()));
    }
}