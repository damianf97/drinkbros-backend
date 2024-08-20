package ar.com.damian.drinkbros_backend.service;

import ar.com.damian.drinkbros_backend.mapper.DrinkMapper;
import ar.com.damian.drinkbros_backend.model.entity.Drink;
import ar.com.damian.drinkbros_backend.model.request.DrinkRequest;
import ar.com.damian.drinkbros_backend.model.response.DrinkResponse;
import ar.com.damian.drinkbros_backend.repository.DrinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DrinksService {
    private final DrinkRepository drinkRepository;
//    private DrinkMapper drinkMapper;

    public DrinkResponse getDrinks(String name) throws Exception {
        Drink result = drinkRepository.findByName(name);
        if (result == null) {
            throw new Exception("No encontrado");
        }
        return DrinkMapper.mapToResponse(result);
    }

    public DrinkResponse createDrink(DrinkRequest drinkRequest) {
        Drink entity = DrinkMapper.mapToEntity(drinkRequest);
        Drink saved = drinkRepository.save(entity);
        return DrinkMapper.mapToResponse(saved);
    }
}
