package com.example.coffeebeans.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Информация о бригадах.")
@Builder
public class RoastingTeamResponse {

    @Schema(description = "номер бригады")
    private UUID roastingTeamId;

    @Schema(description = "вес после обжарки(граммы)")
    private Long outputWeight;

    @Schema(description = "вес до обжарки(граммы)")
    private Long roastingWeight;

    @Schema(description = "страна происхождения")
    private String countryOfOrigin;
}
