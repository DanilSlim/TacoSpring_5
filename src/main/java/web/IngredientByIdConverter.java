package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import tacos.Ingredient;
import tacos.data.interfaces.IngredientRepository;


/**
 * @author Wall
 * Класс реализует конвертер, который конвертирует тип String в тип Ingridient
 * для корректного заполнения поля List<Ingredient> ingredients класса Taco
 * при передачи из вьюшки
 *
 */
@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {
	
	private IngredientRepository ingredientRepo;
	
	@Autowired
    public IngredientByIdConverter(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

	@Override
	public Ingredient convert(String id) {
		
		 return ingredientRepo.findOne(id);
	}

}
