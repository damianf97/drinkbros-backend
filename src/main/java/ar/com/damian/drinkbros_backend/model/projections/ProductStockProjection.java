package ar.com.damian.drinkbros_backend.model.projections;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface ProductStockProjection {
    Long getDrinkId();
    String getDrinkName();
    Long getWarehouseId();
    String getWarehouseName();
    BigDecimal getQuantity();
    LocalDateTime getUpdatedAt();
}
