package ar.com.damian.drinkbros_backend.controller.imp;

import ar.com.damian.drinkbros_backend.controller.DrinksControlerApi;
import ar.com.damian.drinkbros_backend.model.request.DrinkRequest;
import ar.com.damian.drinkbros_backend.model.response.DrinkResponse;
import ar.com.damian.drinkbros_backend.service.DrinksService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DrinksController implements DrinksControlerApi {

    private final DrinksService drinksService;

    @Override
    public ResponseEntity<DrinkResponse> getDrinks(String name) throws Exception {
        return ResponseEntity.ok(drinksService.getDrinks(name));
    }

    @Override
    public ResponseEntity<DrinkResponse> createDrink(DrinkRequest drinkRequest) {
        DrinkResponse response = drinksService.createDrink(drinkRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
