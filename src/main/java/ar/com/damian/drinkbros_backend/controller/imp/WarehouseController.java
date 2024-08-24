package ar.com.damian.drinkbros_backend.controller.imp;

import ar.com.damian.drinkbros_backend.controller.WarehousesControlerApi;
import ar.com.damian.drinkbros_backend.model.dtos.PageResponse;
import ar.com.damian.drinkbros_backend.model.entity.User;
import ar.com.damian.drinkbros_backend.model.request.WarehouseRequest;
import ar.com.damian.drinkbros_backend.model.response.WarehouseResponse;
import ar.com.damian.drinkbros_backend.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WarehouseController implements WarehousesControlerApi {

    private final WarehouseService warehouseService;

    @Override
    public ResponseEntity<PageResponse<WarehouseResponse>> getWarehouses(User user, String name, String city, int size, int page) {
        return ResponseEntity.ok(warehouseService.getWarehouses(user.getDrinkBrotherId(), name, city, size, page));
    }

    @Override
    public ResponseEntity<WarehouseResponse> createWarehouse(User user, WarehouseRequest warehouseRequest) {
        WarehouseResponse response = warehouseService.createWarehouse(warehouseRequest, user.getDrinkBrotherId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<WarehouseResponse> deleteWarehouse(User user, Long warehouseId) {
        WarehouseResponse response = warehouseService.deleteWarehouse(user.getDrinkBrotherId(), warehouseId);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<WarehouseResponse> updateWarehouse(User user, Long warehouseId, WarehouseRequest warehouseRequest) {
        WarehouseResponse response = warehouseService.updateWarehouse(user.getDrinkBrotherId(), warehouseId, warehouseRequest);
        return ResponseEntity.ok(response);
    }
}
