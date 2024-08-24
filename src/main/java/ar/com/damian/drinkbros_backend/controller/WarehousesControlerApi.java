package ar.com.damian.drinkbros_backend.controller;

import ar.com.damian.drinkbros_backend.model.dtos.PageResponse;
import ar.com.damian.drinkbros_backend.model.entity.User;
import ar.com.damian.drinkbros_backend.model.request.DrinkRequest;
import ar.com.damian.drinkbros_backend.model.request.WarehouseRequest;
import ar.com.damian.drinkbros_backend.model.response.DrinkResponse;
import ar.com.damian.drinkbros_backend.model.response.WarehouseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.Max;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/warehouses")
@Validated
public interface WarehousesControlerApi {

    @Operation(summary = "Obtener Depositos", tags = {"Warehouses"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "412", description = "Precondition failed")})
    @GetMapping
    ResponseEntity<PageResponse<WarehouseResponse>> getWarehouses(@AuthenticationPrincipal User user,
                                                                  @RequestParam(value = "name", required = false) String name,
                                                                  @RequestParam(value = "city", required = false) String city,
                                                                  @RequestParam(value = "size", required = false, defaultValue = "20")@Max(100) int size,
                                                                  @RequestParam(value = "page", required = false, defaultValue = "0") int page
    );

    @Operation(summary = "Registrar un nuevo Deposito", tags = {"Warehouses"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "412", description = "Precondition failed")})
    @PostMapping
    ResponseEntity<WarehouseResponse> createWarehouse(@AuthenticationPrincipal User user, @RequestBody WarehouseRequest warehouseRequest);

    @Operation(summary = "Eliminar un Deposito", tags = {"Warehouses"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "412", description = "Precondition failed")})
    @DeleteMapping("/{warehouse_id}")
    ResponseEntity<WarehouseResponse> deleteWarehouse(@AuthenticationPrincipal User user, @PathVariable("warehouse_id") Long warehouseId);

    @Operation(summary = "Actualizar un Deposito", tags = {"Warehouses"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "412", description = "Precondition failed")})
    @PostMapping("/{warehouse_id}")
    ResponseEntity<WarehouseResponse> updateWarehouse(@AuthenticationPrincipal User user, @PathVariable("warehouse_id") Long warehouseId, @RequestBody WarehouseRequest warehouseRequest);

}
