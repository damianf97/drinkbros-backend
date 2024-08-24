package ar.com.damian.drinkbros_backend.service;

import ar.com.damian.drinkbros_backend.exception.ResourceNotFoundException;
import ar.com.damian.drinkbros_backend.mapper.DrinkMapper;
import ar.com.damian.drinkbros_backend.model.dtos.PageResponse;
import ar.com.damian.drinkbros_backend.model.entity.Drink;
import ar.com.damian.drinkbros_backend.model.request.DrinkRequest;
import ar.com.damian.drinkbros_backend.model.response.DrinkResponse;
import ar.com.damian.drinkbros_backend.repository.DrinkRepository;
import ar.com.damian.drinkbros_backend.util.CommonFunctions;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DrinksService {
    private final DrinkRepository drinkRepository;
    private final DrinkMapper drinkMapper;

    public PageResponse<DrinkResponse> getDrinks(Long drinkBrotherId, String name, int size, int page) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Drink> result = drinkRepository.findDrinks(drinkBrotherId, CommonFunctions.prepareStringToSearch(name), pageable);
        List<Drink> drinksList = result.getContent();
        List<DrinkResponse> drinkResponses = drinkMapper.mapToListResponse(drinksList);
        return new PageResponse<>(result, drinkResponses);
    }

    public DrinkResponse createDrink(DrinkRequest drinkRequest, Long drinkBrotherId) {
        Drink entity = drinkMapper.mapToEntity(drinkRequest);
        entity.setDrinkBrotherId(drinkBrotherId);
        Drink saved = drinkRepository.save(entity);
        return drinkMapper.mapDrinToDrinkResponse(saved);
    }

    public DrinkResponse deleteDrink(Long drinkBrotherId, Long drinkId) {
        Drink drink = drinkRepository.findByDrinkIdAndDrinkBrotherId(drinkId, drinkBrotherId).orElseThrow(() -> new ResourceNotFoundException("Drink not found"));
        drinkRepository.delete(drink);
        return drinkMapper.mapDrinToDrinkResponse(drink);
    }

    public DrinkResponse updateDrink(Long drinkBrotherId, Long drinkId, DrinkRequest drinkRequest) {
        Drink drink = drinkRepository.findByDrinkIdAndDrinkBrotherId(drinkId, drinkBrotherId).orElseThrow(() -> new ResourceNotFoundException("Drink not found"));

        drink.setName(drinkRequest.getName());
        drink.setAlc(drinkRequest.getAlc() != null ? drinkRequest.getAlc() : drink.getAlc());
        drink.setBarCode(drinkRequest.getBarCode() != null ? drinkRequest.getBarCode() : drink.getBarCode());

        Drink saved = drinkRepository.save(drink);
        return drinkMapper.mapDrinToDrinkResponse(saved);
    }
}
