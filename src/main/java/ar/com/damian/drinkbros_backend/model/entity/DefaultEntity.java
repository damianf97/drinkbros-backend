package ar.com.damian.drinkbros_backend.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class DefaultEntity {

    @Id
    private Long id;
}
