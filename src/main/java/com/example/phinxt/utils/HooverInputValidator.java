package com.example.phinxt.utils;

import com.example.phinxt.exceptions.ApplicationException;
import com.example.phinxt.models.HooverCleanInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

public class HooverInputValidator {

    private static final Logger logger = LoggerFactory.getLogger(HooverInputValidator.class);
    private HooverInputValidator(){}

    public static void validateInput(HooverCleanInput input) {
        List<Integer> roomSize = input.getRoomSize();
        List<Integer> coords = input.getCoords();
        List<List<Integer>> patches = input.getPatches();
        String instructions = input.getInstructions();

        validateRoomSize(roomSize);
        validateHooverStartingPosition(coords);
        validateInitialHooverCoordinates(coords, roomSize);
        validatePatchesOfDirt(patches, roomSize);
        validateInstructions(instructions);
    }

    private static void validateInstructions(String instructions) {
        if (Objects.isNull(instructions) || instructions.isEmpty()) {
            logger.warn("Instructions cannot be null or empty.");
            throw new ApplicationException(HttpStatus.BAD_REQUEST, "Instructions cannot be null or empty.");
        }

        for (char instruction : instructions.toCharArray()) {
            if (!isValidInstruction(instruction)) {
                logger.warn(String.format("Letter %s in instructions is invalid.", instruction));
                throw new ApplicationException(
                        HttpStatus.BAD_REQUEST, String.format("Letter %s in instructions is invalid.", instruction));
            }
        }
    }

    private static void validatePatchesOfDirt(List<List<Integer>> patches, List<Integer> roomSize) {
        if (CollectionUtils.isEmpty(patches)) {
            logger.warn("Patches of dirt cannot be null or empty.");
            throw new ApplicationException(
                    HttpStatus.BAD_REQUEST,
                    "Patches of dirt cannot be null or empty.");
        }

        for (List<Integer> patch : patches) {
            if (CollectionUtils.isEmpty(patch) || patch.size() != 2) {
                logger.warn("Each patch must be a list of two integers representing coordinates.");
                throw new ApplicationException(
                        HttpStatus.BAD_REQUEST, "Each patch must be a list of two integers representing coordinates.");
            }

            if (!isValidCoordinate(patch, roomSize)) {
                logger.warn("Patch location %s is outside the room boundaries.");
                throw new ApplicationException(
                        HttpStatus.BAD_REQUEST,
                        String.format("Patch location %s is outside the room boundaries.", patch));
            }
        }
    }

    private static void validateRoomSize(List<Integer> roomSize) {
        if (CollectionUtils.isEmpty(roomSize) || roomSize.size() != 2 || roomSize.get(0) <= 0 || roomSize.get(1) <= 0) {
            logger.warn("Room size must be a list of two positive integers.");
            throw new ApplicationException(HttpStatus.BAD_REQUEST, "Room size must be a list of two positive integers.");
        }
    }

    private static void validateHooverStartingPosition(List<Integer> coords) {
        if (CollectionUtils.isEmpty(coords) || coords.size() != 2) {
            logger.warn("Initial hoover position must be a list of two integers.");
            throw new ApplicationException(
                    HttpStatus.BAD_REQUEST, "Initial hoover position must be a list of two integers.");
        }
    }

    private static void validateInitialHooverCoordinates(List<Integer> coord, List<Integer> roomSize) {
        logger.warn("Initial hoover position is outside the room boundaries.");
        if (!isValidCoordinate(coord, roomSize)) {
            throw new ApplicationException(
                    HttpStatus.BAD_REQUEST, "Initial hoover position is outside the room boundaries.");
        }
    }

    private static boolean isValidCoordinate(List<Integer> coord, List<Integer> roomSize) {
        int x = coord.get(0);
        int y = coord.get(1);
        return x >= 0 && x < roomSize.get(0) && y >= 0 && y < roomSize.get(1);
    }

    private static boolean isValidInstruction(char instruction) {
        return instruction == 'N' || instruction == 'E' || instruction == 'S' || instruction == 'W';
    }
}
