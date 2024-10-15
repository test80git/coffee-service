package com.example.coffeebeans.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Информации о бригаде с потерями при обжарке")
@Builder
public class RoastingLossesTeamResponse {

    @Schema(description = "номер бригады")
    private String roastingTeamId;

    @Schema(description = "процент потери при обжарке")
    private Float percentageOfLoss;
}
