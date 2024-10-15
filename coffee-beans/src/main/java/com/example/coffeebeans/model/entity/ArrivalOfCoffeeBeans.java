package com.example.coffeebeans.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сущность для записи в таблицу Поступление нового зерна.")
@Entity
@Table(name = "arrival_of_coffee_beans")
public class ArrivalOfCoffeeBeans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "Id поступления нового зерна")
    private Long id;

    @Schema(description = "сорт зерна")
    @Column(name = "variety", nullable = false)
    private String variety;

    @Schema(description = "страна происхождения")
    @Column(name = "country_of_origin", nullable = false)
    private String countryOfOrigin;

    @Schema(description = "процент арабики")
    @Column(name = "arabica_percentage", nullable = false)
    private Float arabicaPercentage;

    @Schema(description = "процент робусты")
    @Column(name = "robusta_percentage", nullable = false)
    private Float robustaPercentage;

    @Schema(description = "количество мешков")
    @Column(name = "quantity_bag", nullable = false)
    private Long quantityGrainBag;

    @Schema(description = "количество оставшихся мешков")
    @Column(name = "remaining_quantity_bag", nullable = false)
    private Long remainingQuantityGrainBag;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Date modifyDate;
}
