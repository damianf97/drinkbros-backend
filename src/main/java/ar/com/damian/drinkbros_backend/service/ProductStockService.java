package ar.com.damian.drinkbros_backend.service;

import ar.com.damian.drinkbros_backend.exception.ResourceNotFoundException;
import ar.com.damian.drinkbros_backend.mapper.ProductStockMapper;
import ar.com.damian.drinkbros_backend.model.dtos.PageResponse;
import ar.com.damian.drinkbros_backend.model.entity.Drink;
import ar.com.damian.drinkbros_backend.model.entity.ProductStock;
import ar.com.damian.drinkbros_backend.model.entity.ProductStockId;
import ar.com.damian.drinkbros_backend.model.entity.Warehouse;
import ar.com.damian.drinkbros_backend.model.projections.ProductStockProjection;
import ar.com.damian.drinkbros_backend.model.request.ProductStockRequest;
import ar.com.damian.drinkbros_backend.model.response.ProductStockResponse;
import ar.com.damian.drinkbros_backend.repository.DrinkRepository;
import ar.com.damian.drinkbros_backend.repository.ProductStockRepository;
import ar.com.damian.drinkbros_backend.repository.WarehouseRepository;
import ar.com.damian.drinkbros_backend.util.CommonFunctions;
import ar.com.damian.drinkbros_backend.util.MessageBundle;
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
    private final DrinkRepository drinkRepository;
    private final WarehouseRepository warehouseRepository;
    private final ProductStockMapper productStockMapper;

    @Transactional
    public ProductStockResponse registerProductStock(Long drinkBrotherId, ProductStockRequest request) {
        Drink drink = drinkRepository.findByDrinkIdAndDrinkBrotherId(request.getDrinkId(), drinkBrotherId)
                .orElseThrow(() -> new ResourceNotFoundException(MessageBundle.DRINK_NOT_FOUND));

        Warehouse warehouse = warehouseRepository.findByWarehouseIdAndDrinkBrotherId(request.getWarehouseId(), drinkBrotherId)
                .orElseThrow(() -> new ResourceNotFoundException(MessageBundle.WAREHOUSE_NOT_FOUND));

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
        List<ProductStockProjection> productStockProjections = result.getContent();
        List<ProductStockResponse> drinkResponses = productStockMapper.mapListProjectionToListResponse(productStockProjections);
        return new PageResponse<>(result, drinkResponses);
    }
}
