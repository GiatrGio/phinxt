package com.example.phinxt.utils;

import com.example.phinxt.exceptions.ApplicationException;
import com.example.phinxt.models.HooverCleanInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.example.phinxt.utils.HooverInputValidator.validateInput;
import static com.example.phinxt.TestUtils.generateSampleInput;

class HooverInputValidatorTest {

    @Test
    void testHooverInputValidator_invalidInstructions() {
        HooverCleanInput input = generateSampleInput();
        input.setInstructions("NNESEESAWNWW");

        ApplicationException exception = Assertions.assertThrows(
                ApplicationException.class,
                () -> validateInput(input)
        );

        Assertions.assertEquals(exception.getClass(), ApplicationException.class);
        Assertions.assertEquals(
                "400 BAD_REQUEST \"Letter A in instructions is invalid.\"",
                exception.getMessage());
    }

    @Test
    void testHooverInputValidator_invalidRoomSize() {
        HooverCleanInput input = generateSampleInput();
        input.setRoomSize(Arrays.asList(-1, 5));

        ApplicationException exception = Assertions.assertThrows(
                ApplicationException.class,
                () -> validateInput(input)
        );

        Assertions.assertEquals(exception.getClass(), ApplicationException.class);
        Assertions.assertEquals(
                "400 BAD_REQUEST \"Room size must be a list of two positive integers.\"",
                exception.getMessage());
    }

    @Test
    void testHooverInputValidator_invalidHooverStartingPosition() {
        HooverCleanInput input = generateSampleInput();
        input.setCoords(List.of(1));

        ApplicationException exception = Assertions.assertThrows(
                ApplicationException.class,
                () -> validateInput(input)
        );

        Assertions.assertEquals(exception.getClass(), ApplicationException.class);
        Assertions.assertEquals(
                "400 BAD_REQUEST \"Initial hoover position must be a list of two integers.\"",
                exception.getMessage());
    }

    @Test
    void testHooverInputValidator_invalidInitialHooverCoordinates() {
        HooverCleanInput input = generateSampleInput();
        input.setCoords(Arrays.asList(6, 2));

        ApplicationException exception = Assertions.assertThrows(
                ApplicationException.class,
                () -> validateInput(input)
        );

        Assertions.assertEquals(exception.getClass(), ApplicationException.class);
        Assertions.assertEquals(
                "400 BAD_REQUEST \"Initial hoover position is outside the room boundaries.\"",
                exception.getMessage());
    }

    @Test
    void testHooverInputValidator_invalidPatchesOfDirt() {
        HooverCleanInput input = generateSampleInput();
        input.setPatches(Arrays.asList(
                Arrays.asList(1, 0),
                Arrays.asList(2, 2),
                Arrays.asList(6, 3)
        ));

        ApplicationException exception = Assertions.assertThrows(
                ApplicationException.class,
                () -> validateInput(input)
        );

        Assertions.assertEquals(exception.getClass(), ApplicationException.class);
        Assertions.assertEquals(
                "400 BAD_REQUEST \"Patch location [6, 3] is outside the room boundaries.\"",
                exception.getMessage());
    }

    @Test
    void testHooverInputValidator_emptyPatchesOfDirt() {
        HooverCleanInput input = generateSampleInput();
        input.setPatches(Collections.emptyList());

        ApplicationException exception = Assertions.assertThrows(
                ApplicationException.class,
                () -> validateInput(input)
        );

        Assertions.assertEquals(exception.getClass(), ApplicationException.class);
        Assertions.assertEquals(
                "400 BAD_REQUEST \"Patches of dirt cannot be null or empty.\"",
                exception.getMessage());
    }

    @Test
    void testHooverInputValidator_invalidPatchFormat() {
        HooverCleanInput input = generateSampleInput();
        input.setPatches(Arrays.asList(
                Arrays.asList(1, 0),
                List.of(2)
        ));

        ApplicationException exception = Assertions.assertThrows(
                ApplicationException.class,
                () -> validateInput(input)
        );

        Assertions.assertEquals(exception.getClass(), ApplicationException.class);
        Assertions.assertEquals(
                "400 BAD_REQUEST \"Each patch must be a list of two integers representing coordinates.\"",
                exception.getMessage());
    }
}
