package com.example.coffeebeans.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Информации о стране с потерями при обжарке")
@Builder
public class RoastingLossesCountryResponse {

    @Schema(description = "страна происхождения")
    private String countryOfOrigin;

    @Schema(description = "процент потери при обжарке")
    private Float percentageOfLoss;
}
