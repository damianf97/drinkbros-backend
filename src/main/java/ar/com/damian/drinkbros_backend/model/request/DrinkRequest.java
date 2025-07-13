package ar.com.damian.drinkbros_backend.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DrinkRequest {

    @NotBlank
    private String name;
    private String barCode;
    private Double alc;
}