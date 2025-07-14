package ar.com.damian.drinkbros_backend.mapper;

import ar.com.damian.drinkbros_backend.model.entity.ProductStock;
import ar.com.damian.drinkbros_backend.model.projections.ProductStockProjection;
import ar.com.damian.drinkbros_backend.model.response.ProductStockResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductStockMapper {

    @Mapping(source = "drinkId", target = "drinkId")
    @Mapping(source = "warehouseId", target = "warehouseId")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "updatedAt", target = "updatedAt")
    ProductStockResponse mapEntityToResponse(ProductStock productStock);

    ProductStockResponse mapProjectionToResponse(ProductStockProjection projection);

    List<ProductStockResponse> mapListProjectionToListResponse(List<ProductStockProjection> productStockProjections);
}
