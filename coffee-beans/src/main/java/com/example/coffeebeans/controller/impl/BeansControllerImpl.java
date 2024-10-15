package com.example.coffeebeans.controller.impl;

import com.example.coffeebeans.controller.BeansController;
import com.example.coffeebeans.model.response.BeansResponse;
import com.example.coffeebeans.model.response.RoastingLossesCountryResponse;
import com.example.coffeebeans.model.response.RoastingLossesTeamResponse;
import com.example.coffeebeans.service.BeansService;
import com.example.coffeebeans.service.RoastingTeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/grain-service/api/v1")
@RequiredArgsConstructor
public class BeansControllerImpl implements BeansController {

    private final BeansService beansService;
    private final RoastingTeamService roastingTeamService;

    @GetMapping(value = "/grains", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BeansResponse>> getGrains(@RequestParam Optional<String> country,
                                                         @RequestParam Optional<String> variety) {
        List<BeansResponse> filteredGrains = beansService.getFilteredBeans(country, variety);
        return ResponseEntity.ok(filteredGrains);
    }

    @GetMapping("/roasting-teams/losses")
    public ResponseEntity<List<RoastingLossesTeamResponse>> getTeamLosses(@RequestParam String team) {
        List<RoastingLossesTeamResponse> teamLosses = roastingTeamService.getTeamLosses(team);
        return ResponseEntity.ok(teamLosses);
    }

    @GetMapping("/country/losses")
    public ResponseEntity<List<RoastingLossesCountryResponse>> getCountryLosses(@RequestParam String country) {
        List<RoastingLossesCountryResponse> teamLosses = roastingTeamService.getCountryLosses(country);
        return ResponseEntity.ok(teamLosses);
    }
}
