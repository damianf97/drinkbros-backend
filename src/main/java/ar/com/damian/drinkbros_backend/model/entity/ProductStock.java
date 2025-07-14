package ar.com.damian.drinkbros_backend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "product_stock")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductStock {

    @EmbeddedId
    private ProductStockId id;

    @Column(nullable = false)
    private BigDecimal quantity;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    @PreUpdate
    public void updateTimestamp() {
        this.updatedAt = LocalDateTime.now();
    }

    public void setDrinkId(Long drinkId) {
        if (this.id == null) {
            this.id = new ProductStockId();
        }
        id.setDrinkId(drinkId);
    }

    public void setWarehouseId(Long warehouseId) {
        if (this.id == null) {
            this.id = new ProductStockId();
        }
        id.setWarehouseId(warehouseId);
    }

    public Long getDrinkId() {
        if (this.id == null) {
            return null;
        }
        return id.getDrinkId();
    }

    public Long getWarehouseId() {
        if (this.id == null) {
            return null;
        }
        return id.getWarehouseId();
    }
}
