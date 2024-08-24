package ar.com.damian.drinkbros_backend.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DrinkResponse {
    private Long drinkId;
    private String name;
    private String barCode;
    private Double alc;
}
