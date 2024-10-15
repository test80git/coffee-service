package com.example.coffeebeans.service.impl;

import com.example.coffeebeans.model.response.RoastingLossesCountryResponse;
import com.example.coffeebeans.model.response.RoastingLossesTeamResponse;
import com.example.coffeebeans.model.response.RoastingTeamResponse;
import com.example.coffeebeans.repository.RoastingBatchRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class RoastingTeamServiceImplTest {

    @Mock
    private RoastingBatchRepository RBRepository;

    @InjectMocks
    private RoastingTeamServiceImpl roastingTeamService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void getTeamLosses_shouldReturnTeamLosses() {
        UUID teamId = UUID.randomUUID();
        List<RoastingTeamResponse> mockResponses = Arrays.asList(
                new RoastingTeamResponse(teamId, 80L, 100L, "Бразилия"),
                new RoastingTeamResponse(teamId, 160L, 200L, "Колумбия")
        );
        when(RBRepository.findOutputWeightByTeam(any(UUID.class))).thenReturn(mockResponses);

        List<RoastingLossesTeamResponse> result = roastingTeamService.getTeamLosses(teamId.toString());

        assertEquals(1, result.size());
        assertEquals(teamId.toString(), result.get(0).getRoastingTeamId());
        assertTrue(result.get(0).getPercentageOfLoss() > 0);
        assertEquals(20, (float) result.get(0).getPercentageOfLoss());

        verify(RBRepository, times(1)).findOutputWeightByTeam(teamId);
    }

    @Test
    void getCountryLosses_shouldReturnCountryLosses() {
        String country = "Brazil";
        UUID teamId = UUID.randomUUID();
        List<RoastingTeamResponse> mockResponses = Arrays.asList(
                new RoastingTeamResponse(teamId, 80L, 100L, country),
                new RoastingTeamResponse(teamId, 160L, 200L, country)
        );
        when(RBRepository.findOutputWeightByCountry(any(String.class))).thenReturn(mockResponses);

        // When
        List<RoastingLossesCountryResponse> result = roastingTeamService.getCountryLosses(country);

        // Then
        assertEquals(1, result.size());
        assertEquals(country, result.get(0).getCountryOfOrigin());
        assertTrue(result.get(0).getPercentageOfLoss() > 0);
        assertEquals(20, (float) result.get(0).getPercentageOfLoss());

        verify(RBRepository, times(1)).findOutputWeightByCountry(country);
    }
}
