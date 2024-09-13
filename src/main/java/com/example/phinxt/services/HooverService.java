package com.example.phinxt.services;

import com.example.phinxt.exceptions.ApplicationException;
import com.example.phinxt.models.HooverCleanInput;
import com.example.phinxt.models.HooverCleanResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class HooverService {

    private static final Logger logger = LoggerFactory.getLogger(HooverService.class);

    public HooverCleanResponse cleanRoom(HooverCleanInput hooverCleanInput) {
        int cleanedPatches = 0;
        int roomX = hooverCleanInput.getRoomSize().get(0);
        int roomY = hooverCleanInput.getRoomSize().get(1);
        int hooverPositionX = hooverCleanInput.getCoords().get(0);
        int hooverPositionY = hooverCleanInput.getCoords().get(1);

        Set<String> patches = getPatchPositions(hooverCleanInput.getPatches());

        for (char instruction : hooverCleanInput.getInstructions().toCharArray()) {
            int[] newHooverPosition = moveHoover(instruction, hooverPositionX, hooverPositionY, roomX, roomY);
            hooverPositionX = newHooverPosition[0];
            hooverPositionY = newHooverPosition[1];

            cleanedPatches = cleanNewPositionIfNeeded(newHooverPosition, patches, cleanedPatches);
        }

        logger.info("Return hoover final position and cleaned patches");
        return new HooverCleanResponse(List.of(hooverPositionX, hooverPositionY), cleanedPatches);
    }

    private static Set<String> getPatchPositions(List<List<Integer>> patchesInput) {
        return patchesInput.stream()
                .map(patch -> patch.get(0) + "," + patch.get(1))
                .collect(Collectors.toSet());
    }

    private int[] moveHoover(char instruction, int hooverPositionX, int hooverPositionY, int roomX, int roomY) {
        switch (instruction) {
            case 'N':
                if (hooverPositionY < roomY) hooverPositionY++;
                break;
            case 'E':
                if (hooverPositionX < roomX) hooverPositionX++;
                break;
            case 'S':
                if (hooverPositionY > 0) hooverPositionY--;
                break;
            case 'W':
                if (hooverPositionX > 0) hooverPositionX--;
                break;
            default:
                throw new ApplicationException(
                        HttpStatus.BAD_REQUEST, String.format("Letter %s in instructions is invalid.", instruction));
        }

        return new int[]{hooverPositionX, hooverPositionY};
    }

    private int cleanNewPositionIfNeeded(int[] newPosition, Set<String> patches, int cleanedPatches) {
        String positionCoordinates = newPosition[0] + "," + newPosition[1];

        if (patches.contains(positionCoordinates)) {
            patches.remove(positionCoordinates);
            cleanedPatches++;
        }

        return cleanedPatches;
    }
}
