package ar.com.damian.drinkbros_backend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "drinks")
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "drink_id")
    private Long drinkId;

    @Column(name = "name")
    private String name;

    @Column(name = "bar_code")
    private String barCode;
}
