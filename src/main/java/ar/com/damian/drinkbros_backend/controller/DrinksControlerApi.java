package ar.com.damian.drinkbros_backend.controller;

import ar.com.damian.drinkbros_backend.model.request.DrinkRequest;
import ar.com.damian.drinkbros_backend.model.response.DrinkResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<DrinkResponse> getDrinks(@RequestParam(value = "name", required = false) String name) throws Exception;

    @Operation(summary = "Registrar una nueva bebida", tags = {"Drinks"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "412", description = "Precondition failed")})
    @PostMapping
    ResponseEntity<DrinkResponse> createDrink(@RequestBody DrinkRequest drinkRequest);

}
