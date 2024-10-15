package com.example.coffeebeans.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сущность для записи в таблицу Обжарка.")
@Entity
@Table(name = "roasting_batchs")
public class RoastingBatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Id поступившего нового зерна")
    @Column(name = "grain_id")
    private Long grainId;

    @Schema(description = "сорт зерна")
    @Column(name = "variety", nullable = false)
    private String variety;

    @Schema(description = "страна происхождения")
    @Column(name = "country_of_origin", nullable = false)
    private String countryOfOrigin;

    @Schema(description = "вес зерна перед обжаркой(граммы)")
    @Column(name = "roasting_weight")
    private Long roastingWeight;

    @Schema(description = "вес после обжарки(граммы)")
    @Column(name = "output_weight")
    private Long outputWeight;

    @Schema(description = "количество взятых мешков для обжарки")
    @Column(name = "quantity_bag", nullable = false)
    private Long quantityGrainBag;

    @Schema(description = "идентификатор бригады")
    @Column(name="roasting_team_id")
    private UUID roastingTeamId;
}
