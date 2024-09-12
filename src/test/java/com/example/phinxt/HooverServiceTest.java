package com.example.phinxt;

import com.example.phinxt.models.HooverCleanInput;
import com.example.phinxt.models.HooverCleanResponse;
import com.example.phinxt.services.HooverService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HooverServiceTest {

    private HooverService hooverService;

    @BeforeEach
    void setUp() {
        hooverService = new HooverService();
    }

    @Test
    void testCleanRoom_BaseCase() {
        HooverCleanInput input = new HooverCleanInput();
        input.setRoomSize(Arrays.asList(5,5));
        input.setCoords(Arrays.asList(1, 2));
        input.setPatches(Arrays.asList(
                Arrays.asList(1, 0),
                Arrays.asList(2, 2),
                Arrays.asList(2, 3)
        ));
        input.setInstructions("NNESEESWNWW");

        HooverCleanResponse response = hooverService.cleanRoom(input);

        assertEquals(Arrays.asList(1, 3), response.getCoords());
        assertEquals(1, response.getPatches());
    }
}
