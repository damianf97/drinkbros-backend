package ar.com.damian.drinkbros_backend.service;

import ar.com.damian.drinkbros_backend.mapper.ProductStockMapper;
import ar.com.damian.drinkbros_backend.model.dtos.PageResponse;
import ar.com.damian.drinkbros_backend.model.entity.Drink;
import ar.com.damian.drinkbros_backend.model.entity.ProductStock;
import ar.com.damian.drinkbros_backend.model.entity.ProductStockId;
import ar.com.damian.drinkbros_backend.model.entity.Warehouse;
import ar.com.damian.drinkbros_backend.model.projections.ProductStockProjection;
import ar.com.damian.drinkbros_backend.model.request.ProductStockRequest;
import ar.com.damian.drinkbros_backend.model.response.ProductStockResponse;
import ar.com.damian.drinkbros_backend.repository.ProductStockRepository;
import ar.com.damian.drinkbros_backend.util.CommonFunctions;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductStockService {
    private final ProductStockRepository productStockRepository;
    private final DrinksService drinksService;
    private final WarehouseService warehouseService;
    private final ProductStockMapper productStockMapper;

    @Transactional
    public ProductStockResponse registerProductStock(Long drinkBrotherId, ProductStockRequest request) {
        Drink drink = drinksService.findByDrinkIdAndDrinkBrotherId(request.getDrinkId(), drinkBrotherId);

        Warehouse warehouse = warehouseService.findByWarehouseIdAndDrinkBrotherId(request.getWarehouseId(), drinkBrotherId);

        ProductStockId id = new ProductStockId(drink.getDrinkId(), request.getWarehouseId());
        Optional<ProductStock> optional = productStockRepository.findById(id);

        ProductStock stock = optional.orElseGet(ProductStock::new);
        stock.setDrinkId(drink.getDrinkId());
        stock.setWarehouseId(warehouse.getWarehouseId());
        stock.setQuantity(request.getQuantity());

        stock = productStockRepository.save(stock);

        return productStockMapper.mapEntityToResponse(stock);
    }

    public PageResponse<ProductStockResponse> getProductStocks(Long drinkBrotherId, String drinkName, Long drinkId, String warehouseName, Long warehouseId, Pageable pageable) {

        Page<ProductStockProjection> result = productStockRepository.findProductStock(
                drinkBrotherId, CommonFunctions.prepareStringToSearch(drinkName), drinkId, CommonFunctions.prepareStringToSearch(warehouseName), warehouseId, pageable);

        List<ProductStockResponse> drinkResponses = productStockMapper.mapListProjectionToListResponse(result.getContent());
        return new PageResponse<>(result, drinkResponses);
    }
}
