package ar.com.damian.drinkbros_backend.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductStockResponse {
    private Long drinkId;
    private String drinkName;
    private Long warehouseId;
    private String warehouseName;
    private BigDecimal quantity;
    private LocalDateTime updatedAt;

}
