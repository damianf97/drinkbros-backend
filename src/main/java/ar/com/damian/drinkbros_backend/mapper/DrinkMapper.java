package ar.com.damian.drinkbros_backend.mapper;

import ar.com.damian.drinkbros_backend.model.entity.Drink;
import ar.com.damian.drinkbros_backend.model.request.DrinkRequest;
import ar.com.damian.drinkbros_backend.model.response.DrinkResponse;

public class DrinkMapper {

    public static DrinkResponse mapToResponse(Drink drink) {
        return new DrinkResponse(drink.getDrinkId(), drink.getName(), drink.getBarCode());
    }
    public static Drink mapToEntity(DrinkRequest drink) {
        Drink drinkEntity = new Drink();
        drinkEntity.setName(drink.getName());
        drinkEntity.setBarCode(drink.getBarCode());
        return drinkEntity;
    }
}
