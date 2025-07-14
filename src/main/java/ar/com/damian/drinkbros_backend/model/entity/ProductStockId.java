package ar.com.damian.drinkbros_backend.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductStockId implements Serializable {

    @Column(name = "drink_id")
    private Long drinkId;

    @Column(name = "warehouse_id")
    private Long warehouseId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductStockId that)) return false;
        return Objects.equals(drinkId, that.drinkId) &&
                Objects.equals(warehouseId, that.warehouseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(drinkId, warehouseId);
    }
}