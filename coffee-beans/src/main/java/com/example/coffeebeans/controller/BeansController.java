package com.example.coffeebeans.controller;

import com.example.coffeebeans.model.response.BeansResponse;
import com.example.coffeebeans.model.response.RoastingLossesTeamResponse;
import com.example.coffeebeans.model.response.RoastingTeamResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/grain-service/api/v1")
public interface BeansController {

    @GetMapping("/grains")
    @Operation(summary = "Остатки зерна по сортам и странам (с фильтрацией)",
            description = """
                    Данный метод предназначен для получения остатков зерна по сортам и странам (с фильтрацией)
                    """)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запрос выполнен успешно",
                    content = {@Content(schema = @Schema(implementation = BeansResponse.class))}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Некорректные параметры запроса",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}
            )
    })
    ResponseEntity<List<BeansResponse>> getGrains(@RequestParam Optional<String> country,
                                                  @RequestParam Optional<String> variety);

    @GetMapping("/roasting-teams/losses")
    @Operation(summary = "Потери по каждой бригаде",
            description = """
                    Данный метод предназначен для получения списка Потерь по каждой бригаде
                    """)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запрос выполнен успешно",
                    content = {@Content(schema = @Schema(implementation = RoastingTeamResponse.class))}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Некорректные параметры запроса",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}
            )
    })
    ResponseEntity<List<RoastingLossesTeamResponse>> getTeamLosses(@RequestParam String team);
}
