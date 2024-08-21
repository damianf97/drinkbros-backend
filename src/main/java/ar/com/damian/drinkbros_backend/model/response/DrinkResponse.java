package ar.com.damian.drinkbros_backend.model.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrinkResponse {
    private Long drinkId;
    private String name;
    private String barCode;
    private Double alc;
}
