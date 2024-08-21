package ar.com.damian.drinkbros_backend.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrinkRequest {

    @NotBlank
    private String name;
    private String barCode;
    private Double alc;
}
