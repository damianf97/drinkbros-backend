package ar.com.damian.drinkbros_backend.controller;

import ar.com.damian.drinkbros_backend.model.dtos.PageResponse;
import ar.com.damian.drinkbros_backend.model.entity.User;
import ar.com.damian.drinkbros_backend.model.request.DrinkRequest;
import ar.com.damian.drinkbros_backend.model.response.DrinkResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.Max;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/drinks")
@Validated
public interface DrinksControlerApi {

    @Operation(summary = "Obtener Bebidas", tags = {"Drinks"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "412", description = "Precondition failed")})
    @GetMapping
    ResponseEntity<PageResponse<DrinkResponse>> getDrinks(@AuthenticationPrincipal User user,
                                                          @RequestParam(value = "name", required = false) String name,
                                                          @RequestParam(value = "size", required = false, defaultValue = "20")@Max(100) int size,
                                                          @RequestParam(value = "page", required = false, defaultValue = "0") int page
    ) throws Exception;

    @Operation(summary = "Registrar una nueva bebida", tags = {"Drinks"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "412", description = "Precondition failed")})
    @PostMapping
    ResponseEntity<DrinkResponse> createDrink(@AuthenticationPrincipal User user, @RequestBody DrinkRequest drinkRequest);

    @Operation(summary = "Registrar una nueva bebida", tags = {"Drinks"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "412", description = "Precondition failed")})
    @DeleteMapping("/{drink_id}")
    ResponseEntity<DrinkResponse> deleteDrink(@AuthenticationPrincipal User user, @PathVariable("drink_id") Long drinkId);

    @Operation(summary = "Registrar una nueva bebida", tags = {"Drinks"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "412", description = "Precondition failed")})
    @PostMapping("/{drink_id}")
    ResponseEntity<DrinkResponse> updateDrink(@AuthenticationPrincipal User user, @PathVariable("drink_id") Long drinkId, @RequestBody DrinkRequest drinkRequest);

}
