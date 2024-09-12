package com.example.phinxt.resources;

import com.example.phinxt.models.HooverCleanInput;
import com.example.phinxt.models.HooverCleanResponse;
import com.example.phinxt.services.HooverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.phinxt.utils.HooverInputValidator.validateInput;

@RestController
@RequestMapping("/hoover")
public class HooverResource {

    private final HooverService hooverService;

    public HooverResource(HooverService hooverService) {
        this.hooverService = hooverService;
    }

    @Operation(summary = "Gets number of cleaned patches of dirt and final position of hoover after running instructions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "500", description = "Unknown error encountered")
    })
    @PostMapping("/cleanRoom")
    public HooverCleanResponse cleanRoom(@RequestBody HooverCleanInput input) {
        validateInput(input);
        return hooverService.cleanRoom(input);
    }

}
