package com.example.phinxt;

import com.example.phinxt.models.HooverCleanInput;
import com.example.phinxt.models.HooverCleanResponse;
import com.example.phinxt.services.HooverService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.example.phinxt.utils.TestUtils.generateSampleInput;
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
