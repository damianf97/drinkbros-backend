package ar.com.damian.drinkbros_backend.controller;

import ar.com.damian.drinkbros_backend.model.dtos.PageResponse;
import ar.com.damian.drinkbros_backend.model.entity.User;
import ar.com.damian.drinkbros_backend.model.request.ProductStockRequest;
import ar.com.damian.drinkbros_backend.model.response.ProductStockResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.Max;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/product_stock")
@Validated
public interface ProductStockControllerApi {
    @Operation(summary = "Registrar stock de una bebida en un deposito", tags = {"Product Stock"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "412", description = "Precondition failed")})
    @PostMapping
    ResponseEntity<ProductStockResponse> registerProductStock(@AuthenticationPrincipal User user, @RequestBody ProductStockRequest productStockRequest);

    @Operation(summary = "Obtener stocks de bebidas", tags = {"Product Stock"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "412", description = "Precondition failed")})
    @GetMapping
    ResponseEntity<PageResponse<ProductStockResponse>> getProductStocks(@AuthenticationPrincipal User user,
                                                                 @RequestParam(value = "drik_name", required = false) String drinkName,
                                                                 @RequestParam(value = "drik_id", required = false) Long drinkId,
                                                                 @RequestParam(value = "warehouse_name", required = false) String warehouseName,
                                                                 @RequestParam(value = "warehouse_id", required = false) Long warehouseId,
                                                                 @RequestParam(value = "size", required = false, defaultValue = "20") @Max(100) int size,
                                                                 @RequestParam(value = "page", required = false, defaultValue = "0") int page
    );
}
