package ar.com.damian.drinkbros_backend.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductStockRequest {

    @NotNull
    private Long drinkId;

    @NotNull
    private Long warehouseId;
    private BigDecimal quantity;

}
