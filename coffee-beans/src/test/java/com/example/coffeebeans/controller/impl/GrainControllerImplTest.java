package com.example.coffeebeans.controller.impl;

import com.example.coffeebeans.model.response.BeansResponse;
import com.example.coffeebeans.model.response.RoastingLossesCountryResponse;
import com.example.coffeebeans.model.response.RoastingLossesTeamResponse;
import com.example.coffeebeans.service.impl.BeansServiceImpl;
import com.example.coffeebeans.service.impl.RoastingTeamServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = BeansControllerImpl.class)
class GrainControllerImplTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BeansServiceImpl grainService;

    @MockBean
    private RoastingTeamServiceImpl roastingTeamService;

    @Test
    void getGrains_shouldReturnFilteredGrains() throws Exception {
        // Arrange
        List<BeansResponse> grainResponses = List.of(
                new BeansResponse("Brazil", "Arabica", 100L),
                new BeansResponse("Brazil", "Arabica", 200L)
        );
        when(grainService.getFilteredBeans(Optional.of("Brazil"), Optional.of("Arabica"))).thenReturn(grainResponses);

        // Act & Assert
        mockMvc.perform(get("/grain-service/api/v1/grains")
                        .param("country", "Brazil")
                        .param("variety", "Arabica")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].countryOfOrigin", is("Brazil")))
                .andExpect(jsonPath("$[0].variety", is("Arabica")))
                .andExpect(jsonPath("$[0].remainingQuantityGrainBag", is(100)))
                .andExpect(jsonPath("$[1].remainingQuantityGrainBag", is(200)));
    }

    @Test
    void getTeamLosses_shouldReturnTeamLosses() throws Exception {
        // Arrange
        List<RoastingLossesTeamResponse> teamLosses = List.of(
                new RoastingLossesTeamResponse("Team A", 5.0f),
                new RoastingLossesTeamResponse("Team B", 7.0f)
        );
        when(roastingTeamService.getTeamLosses("Team A")).thenReturn(teamLosses);

        // Act & Assert
        mockMvc.perform(get("/grain-service/api/v1/roasting-teams/losses")
                        .param("team", "Team A")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].roastingTeamId", is("Team A")))
                .andExpect(jsonPath("$[0].percentageOfLoss", is(5.0)))
                .andExpect(jsonPath("$[1].roastingTeamId", is("Team B")))
                .andExpect(jsonPath("$[1].percentageOfLoss", is(7.0)));
    }

    @Test
    void getCountryLosses_shouldReturnCountryLosses() throws Exception {
        // Arrange
        List<RoastingLossesCountryResponse> countryLosses = List.of(
                new RoastingLossesCountryResponse("Brazil", 3.0f)
        );
        when(roastingTeamService.getCountryLosses("Brazil")).thenReturn(countryLosses);

        // Act & Assert
        mockMvc.perform(get("/grain-service/api/v1/country/losses")
                        .param("country", "Brazil")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].countryOfOrigin", is("Brazil")))
                .andExpect(jsonPath("$[0].percentageOfLoss", is(3.0)));
    }
}

