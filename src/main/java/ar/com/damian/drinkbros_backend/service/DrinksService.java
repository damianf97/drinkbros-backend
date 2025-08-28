package ar.com.damian.drinkbros_backend.service;

import ar.com.damian.drinkbros_backend.exception.ResourceNotFoundException;
import ar.com.damian.drinkbros_backend.mapper.DrinkMapper;
import ar.com.damian.drinkbros_backend.model.dtos.PageResponse;
import ar.com.damian.drinkbros_backend.model.entity.Drink;
import ar.com.damian.drinkbros_backend.model.request.DrinkRequest;
import ar.com.damian.drinkbros_backend.model.response.DrinkResponse;
import ar.com.damian.drinkbros_backend.repository.DrinkRepository;
import ar.com.damian.drinkbros_backend.util.CommonFunctions;
import ar.com.damian.drinkbros_backend.util.MessageBundle;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DrinksService {
    private final DrinkRepository drinkRepository;
    private final DrinkMapper drinkMapper;

    public PageResponse<DrinkResponse> getDrinks(Long drinkBrotherId, String name, Pageable pageable) {
        Page<Drink> result = drinkRepository.findDrinks(
                drinkBrotherId, CommonFunctions.prepareStringToSearch(name), pageable);
        List<Drink> drinksList = result.getContent();
        List<DrinkResponse> drinkResponses = drinkMapper.mapToListResponse(drinksList);
        return new PageResponse<>(result, drinkResponses);
    }

    @Transactional
    public DrinkResponse createDrink(DrinkRequest drinkRequest, Long drinkBrotherId) {
        Drink entity = drinkMapper.mapToEntity(drinkRequest);
        entity.setDrinkBrotherId(drinkBrotherId);
        Drink saved = drinkRepository.save(entity);
        return drinkMapper.mapDrinkToResponse(saved);
    }

    @Transactional
    public DrinkResponse deleteDrink(Long drinkBrotherId, Long drinkId) {
        Drink drink = findByDrinkIdAndDrinkBrotherId(drinkId, drinkBrotherId);

        drinkRepository.delete(drink);
        return drinkMapper.mapDrinkToResponse(drink);
    }

    @Transactional
    public DrinkResponse updateDrink(Long drinkBrotherId, Long drinkId, DrinkRequest drinkRequest) {
        Drink drink = findByDrinkIdAndDrinkBrotherId(drinkId, drinkBrotherId);

        drink.setName(drinkRequest.getName());
        drink.setAlc(drinkRequest.getAlc() != null ? drinkRequest.getAlc() : drink.getAlc());
        drink.setBarCode(drinkRequest.getBarCode() != null ? drinkRequest.getBarCode() : drink.getBarCode());

        Drink saved = drinkRepository.save(drink);
        return drinkMapper.mapDrinkToResponse(saved);
    }

    public Drink findByDrinkIdAndDrinkBrotherId(Long drinkId, Long drinkBrotherId) {
        return drinkRepository.findByDrinkIdAndDrinkBrotherId(drinkId, drinkBrotherId)
                .orElseThrow(() -> new ResourceNotFoundException(MessageBundle.DRINK_NOT_FOUND));
    }
}
