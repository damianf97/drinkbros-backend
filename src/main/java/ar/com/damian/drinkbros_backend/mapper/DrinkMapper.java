package ar.com.damian.drinkbros_backend.mapper;

import ar.com.damian.drinkbros_backend.model.entity.Drink;
import ar.com.damian.drinkbros_backend.model.request.DrinkRequest;
import ar.com.damian.drinkbros_backend.model.response.DrinkResponse;

import java.util.ArrayList;
import java.util.List;

public class DrinkMapper {

    public static DrinkResponse mapToResponse(Drink drink) {
        return new DrinkResponse(drink.getDrinkId(), drink.getName(), drink.getBarCode(), drink.getAlc());
    }

    public static Drink mapToEntity(DrinkRequest drink) {
        Drink drinkEntity = new Drink();
        drinkEntity.setName(drink.getName());
        drinkEntity.setBarCode(drink.getBarCode());
        drinkEntity.setAlc(drink.getAlc());
        return drinkEntity;
    }

    public static List<DrinkResponse> mapToListResponse(List<Drink> drinks) {
        List<DrinkResponse> drinkResponses = new ArrayList<>();
        for (Drink drink : drinks) {
            DrinkResponse drinkEntity = mapToResponse(drink);
            drinkResponses.add(drinkEntity);
        }
        return drinkResponses;
    }
}
