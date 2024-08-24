package ar.com.damian.drinkbros_backend.repository;

import ar.com.damian.drinkbros_backend.model.entity.Warehouses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WarehouseRepository extends JpaRepository<Warehouses, Long> {

    @Query(value =
            """
                SELECT *
                FROM warehouses w
                WHERE w.drink_brother_id = :drinkBrotherId
                AND (:name IS NULL OR w.name LIKE :name)
                AND (:city IS NULL OR w.city LIKE :city);
            """,
            nativeQuery = true)
    Page<Warehouses> findWarehouses(@Param("drinkBrotherId") Long drinkBrotherId,
                                    @Param("name") String name,
                                    @Param("city") String city,
                                    Pageable pageable);

    Optional<Warehouses> findWarehousesByWarehousesIdAndDrinkBrotherId(Long warehouseId, Long drinkBrotherId);
}
