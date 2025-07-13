package ar.com.damian.drinkbros_backend.mapper;

import ar.com.damian.drinkbros_backend.model.entity.Warehouse;
import ar.com.damian.drinkbros_backend.model.request.WarehouseRequest;
import ar.com.damian.drinkbros_backend.model.response.WarehouseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface WarehouseMapper {

    WarehouseResponse mapWarehouseToResponse(Warehouse warehouse);

    List<WarehouseResponse> mapListToResponseList(List<Warehouse> warehouses);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "city", target = "city")
    Warehouse mapToEntity(WarehouseRequest warehouseRequest);
}
