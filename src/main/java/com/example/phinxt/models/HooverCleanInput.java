package com.example.phinxt.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HooverCleanInput {
    @Schema(description = "Room dimensions as X and Y coordinates.", example = "[5, 5]")
    @NotNull
    private List<Integer> roomSize;

    @Schema(description = "Initial hoover position as X and Y coordinates.", example = "[1, 2]")
    @NotNull
    private List<Integer> coords;


    @Schema(description = "Locations of patches of dirt as X and Y coordinates.",
            example = "[[1, 0], [2, 2], [2, 3]]")
    @NotNull
    private List<List<Integer>> patches;


    @Schema(description = "Instructions for hoover movement as a string of characters (e.g., 'N' for North, 'E' for East, 'S' for South, and 'W' for West).",
            example = "NNESEESWNWW")
    @NotNull
    private String instructions;
}
