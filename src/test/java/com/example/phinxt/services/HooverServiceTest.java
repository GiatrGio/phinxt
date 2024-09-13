package com.example.phinxt.services;

import com.example.phinxt.models.HooverCleanInput;
import com.example.phinxt.models.HooverCleanResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.example.phinxt.TestUtils.generateSampleInput;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HooverServiceTest {

    private HooverService hooverService;

    @BeforeEach
    void setUp() {
        hooverService = new HooverService();
    }

    @Test
    void testCleanRoom_BaseCase() {
        HooverCleanInput input = generateSampleInput();

        HooverCleanResponse response = hooverService.cleanRoom(input);

        assertEquals(Arrays.asList(1, 3), response.getCoords());
        assertEquals(1, response.getPatches());
    }
}
