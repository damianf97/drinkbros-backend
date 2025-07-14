package ar.com.damian.drinkbros_backend.controller.imp;

import ar.com.damian.drinkbros_backend.controller.ProductStockControllerApi;
import ar.com.damian.drinkbros_backend.model.dtos.PageResponse;
import ar.com.damian.drinkbros_backend.model.entity.User;
import ar.com.damian.drinkbros_backend.model.request.ProductStockRequest;
import ar.com.damian.drinkbros_backend.model.response.ProductStockResponse;
import ar.com.damian.drinkbros_backend.service.ProductStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductStockController implements ProductStockControllerApi {

    private final ProductStockService productStockService;

    @Override
    public ResponseEntity<ProductStockResponse> registerProductStock(User user, ProductStockRequest productStockRequest) {
        ProductStockResponse result = productStockService.registerProductStock(user.getDrinkBrotherId(), productStockRequest);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<PageResponse<ProductStockResponse>> getProductStocks(User user, String drinkName, Long drinkId, String warehouseName, Long warehouseId, int size, int page) {
        Pageable pageable = PageRequest.of(page, size);

        PageResponse<ProductStockResponse> result = productStockService.getProductStocks(
                user.getDrinkBrotherId(), drinkName, drinkId, warehouseName, warehouseId, pageable);
        return ResponseEntity.ok(result);
    }
}
