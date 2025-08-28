package ar.com.damian.drinkbros_backend.service;

import ar.com.damian.drinkbros_backend.mapper.ProductStockMapper;
import ar.com.damian.drinkbros_backend.model.entity.Drink;
import ar.com.damian.drinkbros_backend.model.entity.ProductStock;
import ar.com.damian.drinkbros_backend.model.entity.ProductStockId;
import ar.com.damian.drinkbros_backend.model.entity.Warehouse;
import ar.com.damian.drinkbros_backend.model.request.ProductStockRequest;
import ar.com.damian.drinkbros_backend.model.response.ProductStockResponse;
import ar.com.damian.drinkbros_backend.repository.ProductStockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProductStockServiceTest {

    @InjectMocks
    private ProductStockService systemUnderTest;

    @Mock
    private ProductStockRepository productStockRepositoryMock;

    @Mock
    private DrinksService drinksServiceMock;

    @Mock
    private WarehouseService warehouseServiceMock;

    @Mock
    private ProductStockMapper productStockMapperMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterProductStock() {
        ProductStockRequest request = new ProductStockRequest();
        request.setDrinkId(23L);
        request.setWarehouseId(13L);
        request.setQuantity(BigDecimal.valueOf(50));

        Drink drink = new Drink();
        drink.setDrinkId(23L);
        drink.setDrinkBrotherId(15L);
        drink.setName("FERNET");
        drink.setAlc(40D);

        when(drinksServiceMock.findByDrinkIdAndDrinkBrotherId(23L, 15L)).thenReturn(drink);

        Warehouse warehouse = new Warehouse();
        warehouse.setWarehouseId(13L);
        warehouse.setDrinkBrotherId(15L);
        warehouse.setName("Warehouse1");

        when(warehouseServiceMock.findByWarehouseIdAndDrinkBrotherId(13L, 15L)).thenReturn(warehouse);

        ProductStockId id = new ProductStockId();
        id.setDrinkId(23L);
        request.setWarehouseId(13L);

        ProductStock stock = new ProductStock();
        stock.setDrinkId(23L);
        stock.setWarehouseId(13L);
        stock.setQuantity(BigDecimal.valueOf(30));
        when(productStockRepositoryMock.findById(refEq(id))).thenReturn(Optional.of(stock));

        ProductStock saved = new ProductStock();
        saved.setDrinkId(23L);
        saved.setWarehouseId(13L);
        saved.setQuantity(BigDecimal.valueOf(50));
        when(productStockRepositoryMock.save(refEq(saved))).thenReturn(saved);

        ProductStockResponse response = new ProductStockResponse();
        response.setDrinkId(23L);
        response.setWarehouseId(13L);
        response.setQuantity(BigDecimal.valueOf(50));
        when(productStockMapperMock.mapEntityToResponse(refEq(saved))).thenReturn(response);

        ProductStockResponse result = systemUnderTest.registerProductStock(15L, request);

        assertNotNull(result);
        assertEquals(BigDecimal.valueOf(50), result.getQuantity());
        verify(productStockRepositoryMock).save(refEq(saved));
    }
}
