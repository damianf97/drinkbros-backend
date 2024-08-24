package ar.com.damian.drinkbros_backend.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WarehouseResponse {
    private Long warehouseId;
    private String name;
    private String address;
    private String city;
    private LocalDateTime createdAt;
}
