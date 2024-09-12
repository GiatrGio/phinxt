package com.example.phinxt.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class HooverCleanResponse {
    @Schema(description = "Final hoover position as X and Y coordinates", example = "[1, 3]")
    private List<Integer> coords;
    @Schema(description = "Number of cleaned patches", example = "1")
    private Integer patches;
}
