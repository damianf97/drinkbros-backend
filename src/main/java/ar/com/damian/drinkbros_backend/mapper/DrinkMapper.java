package ar.com.damian.drinkbros_backend.mapper;

import ar.com.damian.drinkbros_backend.model.entity.Drink;
import ar.com.damian.drinkbros_backend.model.request.DrinkRequest;
import ar.com.damian.drinkbros_backend.model.response.DrinkResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DrinkMapper {

    DrinkResponse mapDrinkToResponse(Drink drink);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "barCode", target = "barCode")
    @Mapping(source = "alc", target = "alc")
    Drink mapToEntity(DrinkRequest drink);

    List<DrinkResponse> mapToListResponse(List<Drink> drinks);
}
