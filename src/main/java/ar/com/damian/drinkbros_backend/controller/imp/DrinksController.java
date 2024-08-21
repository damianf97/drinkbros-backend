package ar.com.damian.drinkbros_backend.controller.imp;

import ar.com.damian.drinkbros_backend.controller.DrinksControlerApi;
import ar.com.damian.drinkbros_backend.model.dtos.PageResponse;
import ar.com.damian.drinkbros_backend.model.entity.User;
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
    public ResponseEntity<PageResponse<DrinkResponse>> getDrinks(User user, String name, int size, int page) throws Exception {
        return ResponseEntity.ok(drinksService.getDrinks(user.getDrinkBrotherId(), name, size, page));
    }

    @Override
    public ResponseEntity<DrinkResponse> createDrink(User user, DrinkRequest drinkRequest) {
        DrinkResponse response = drinksService.createDrink(drinkRequest, user.getDrinkBrotherId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<DrinkResponse> deleteDrink(User user, Long drinkId) {
        DrinkResponse response = drinksService.deleteDrink(user.getDrinkBrotherId(), drinkId);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<DrinkResponse> updateDrink(User user, Long drinkId, DrinkRequest drinkRequest) {
        DrinkResponse response = drinksService.updateDrink(user.getDrinkBrotherId(), drinkId, drinkRequest);
        return ResponseEntity.ok(response);
    }
}
