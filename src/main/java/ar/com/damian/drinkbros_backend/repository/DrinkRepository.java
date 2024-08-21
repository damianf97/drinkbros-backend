package ar.com.damian.drinkbros_backend.repository;

import ar.com.damian.drinkbros_backend.model.entity.Drink;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DrinkRepository extends JpaRepository<Drink, Long> {

    @Query(value =
            """
            SELECT *
            FROM drinks d
            WHERE d.drink_brother_id = :drinkBrotherId
            AND (:name IS NULL OR d.name LIKE :name);
            """,
            nativeQuery = true)
    Page<Drink> findDrinks(@Param("drinkBrotherId") Long drinkBrotherId,
                           @Param("name") String name,
                           Pageable pageable);
}
