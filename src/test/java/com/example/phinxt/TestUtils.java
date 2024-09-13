package com.example.phinxt;

import com.example.phinxt.models.HooverCleanInput;

import java.util.Arrays;

public class TestUtils {

    public static HooverCleanInput generateSampleInput() {
        HooverCleanInput input = new HooverCleanInput();
        input.setRoomSize(Arrays.asList(5,5));
        input.setCoords(Arrays.asList(1, 2));
        input.setPatches(Arrays.asList(
                Arrays.asList(1, 0),
                Arrays.asList(2, 2),
                Arrays.asList(2, 3)
        ));
        input.setInstructions("NNESEESWNWW");
        return input;
    }
}
