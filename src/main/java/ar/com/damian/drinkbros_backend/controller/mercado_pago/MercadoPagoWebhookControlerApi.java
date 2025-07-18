package ar.com.damian.drinkbros_backend.controller.mercado_pago;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/mercado_pago/webhook")
@Validated
public interface MercadoPagoWebhookControlerApi {

    @Operation(summary = "Recibir webhooks de Mercado Pago", tags = {"Mercado Pago"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "412", description = "Precondition failed")})
    @PostMapping
    ResponseEntity<String> handleWebhook(@RequestBody Map<String, Object> payload,
                                         @RequestHeader Map<String, String> headers);

}
