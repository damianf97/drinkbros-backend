package ar.com.damian.drinkbros_backend.repository;

import ar.com.damian.drinkbros_backend.model.entity.ProductStock;
import ar.com.damian.drinkbros_backend.model.entity.ProductStockId;
import ar.com.damian.drinkbros_backend.model.projections.ProductStockProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductStockRepository extends JpaRepository<ProductStock, ProductStockId> {
    @Query(value = """
            SELECT ps.drink_id     AS drinkId,
                   d.name          AS drinkName,
                   ps.warehouse_id AS warehouseId,
                   w.name          AS warehouseName,
                   ps.quantity     AS quantity,
                   ps.updated_at   AS updatedAt
            FROM product_stock ps
                     INNER JOIN drinks d ON d.drink_id = ps.drink_id AND d.drink_brother_id = :drinkBrotherId
                     INNER JOIN warehouses w ON w.warehouse_id = ps.warehouse_id AND w.drink_brother_id = :drinkBrotherId
            WHERE (:warehouseName IS NULL OR w.name LIKE :warehouseName)
              AND (:warehouseId IS NULL OR ps.warehouse_id LIKE :warehouseId)
              AND (:drinkName IS NULL OR d.name LIKE :drinkName)
              AND (:drinkId IS NULL OR ps.drink_id LIKE :drinkId)
            """,
            nativeQuery = true)
    Page<ProductStockProjection> findProductStock(
            @Param("drinkBrotherId") Long drinkBrotherId,
            @Param("drinkName") String drinkName,
            @Param("drinkId") Long drinkId,
            @Param("warehouseName") String warehouseName,
            @Param("warehouseId") Long warehouseId,
            Pageable pageable);
}
