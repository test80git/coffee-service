package com.example.coffeebeans.service;



import com.example.coffeebeans.model.response.RoastingLossesCountryResponse;
import com.example.coffeebeans.model.response.RoastingLossesTeamResponse;

import java.util.List;

public interface RoastingTeamService {
    List<RoastingLossesTeamResponse> getTeamLosses(String team);

    List<RoastingLossesCountryResponse> getCountryLosses(String country);
}
