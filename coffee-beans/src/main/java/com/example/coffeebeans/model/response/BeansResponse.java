package com.example.coffeebeans.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "ОТВЕТ для количества остатков по каждому сорту и стране")
@Builder
public class BeansResponse {

    @Schema(description = "страна происхождения")
    private String countryOfOrigin;

    @Schema(description = "сорт зерна")
    private String variety;

    @Schema(description = "количество оставшихся мешков")
    private Long remainingQuantityGrainBag;
}
