package ar.com.damian.drinkbros_backend.service;

import ar.com.damian.drinkbros_backend.exception.ResourceNotFoundException;
import ar.com.damian.drinkbros_backend.mapper.WarehouseMapper;
import ar.com.damian.drinkbros_backend.model.dtos.PageResponse;
import ar.com.damian.drinkbros_backend.model.entity.Warehouse;
import ar.com.damian.drinkbros_backend.model.request.WarehouseRequest;
import ar.com.damian.drinkbros_backend.model.response.WarehouseResponse;
import ar.com.damian.drinkbros_backend.repository.WarehouseRepository;
import ar.com.damian.drinkbros_backend.util.CommonFunctions;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;
    private final WarehouseMapper warehouseMapper;

    public PageResponse<WarehouseResponse> getWarehouses(Long drinkBrotherId, String name,  String city, int size, int page) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Warehouse> result = warehouseRepository.findWarehouses(drinkBrotherId, CommonFunctions.prepareStringToSearch(name), CommonFunctions.prepareStringToSearch(city), pageable);
        List<Warehouse> warehouses = result.getContent();
        List<WarehouseResponse> warehouseResponses = warehouseMapper.mapListToResponseList(warehouses);
        return new PageResponse<>(result, warehouseResponses);
    }

    public WarehouseResponse createWarehouse(WarehouseRequest warehouseRequest, Long drinkBrotherId) {
        Warehouse entity = warehouseMapper.mapToEntity(warehouseRequest);
        entity.setDrinkBrotherId(drinkBrotherId);
        Warehouse saved = warehouseRepository.save(entity);
        return warehouseMapper.mapWarehouseToResponse(saved);
    }

    public WarehouseResponse deleteWarehouse(Long drinkBrotherId, Long warehouseId) {
        Warehouse warehouse = warehouseRepository.findWarehousesByWarehouseIdAndDrinkBrotherId(warehouseId, drinkBrotherId).orElseThrow(() -> new ResourceNotFoundException("Warehouse not found"));
        warehouseRepository.delete(warehouse);
        return warehouseMapper.mapWarehouseToResponse(warehouse);
    }

    public WarehouseResponse updateWarehouse(Long drinkBrotherId, Long warehouseId, WarehouseRequest warehouseRequest) {
        Warehouse warehouse = warehouseRepository.findWarehousesByWarehouseIdAndDrinkBrotherId(warehouseId, drinkBrotherId).orElseThrow(() -> new ResourceNotFoundException("Warehouse not found"));

        warehouse.setName(warehouseRequest.getName());
        warehouse.setCity(warehouseRequest.getCity() != null ? warehouseRequest.getCity() : warehouse.getCity());
        warehouse.setAddress(warehouseRequest.getAddress() != null ? warehouseRequest.getAddress() : warehouse.getAddress());

        Warehouse saved = warehouseRepository.save(warehouse);
        return warehouseMapper.mapWarehouseToResponse(saved);
    }
}
