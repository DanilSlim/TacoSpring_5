package web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Taco;

@Controller
@RequestMapping("/design")
public class DesignTacoController {
	
	
@ModelAttribute	
private void addIngridientsToModel (Model model) {
		
		List<Ingredient> ingredients = Arrays.asList(
				new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
				new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
				new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
				new Ingredient("CARN", "Carnitas", Type.PROTEIN),
				new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
				new Ingredient("LETC", "Lettuce", Type.VEGGIES),
				new Ingredient("CHED", "Cheddar", Type.CHEESE),
				new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
				new Ingredient("SLSA", "Salsa", Type.SAUCE),
				new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
				);
		
		Type[] types = Ingredient.Type.values();
		
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(),filterByType(ingredients, type));
		}
		
	}


	@ModelAttribute(name = "design")
		public Taco taco() {
			return new Taco();
		}
	
	@GetMapping
	public String showDesignForm(Model model) {
		
		addIngridientsToModel(model);
		
		model.addAttribute("design", new Taco());
		
		return "design";
		
	}
	
	
	private List<Ingredient> filterByType(List<Ingredient>ingredients,Type type){
		
				List<Ingredient> filteredIngredients = new ArrayList<Ingredient>(); 
		
				for(Ingredient ingredient : ingredients) {
			
					if(type.toString().equals(ingredient.getType().toString()))  filteredIngredients.add(ingredient);
			
				}
		
		return filteredIngredients;
		
	}
	
	
	
	
	@PostMapping
	public String processDesign(@Valid @ModelAttribute("design") Taco design, Errors errors, Model model) {
		
		//в модели возвращается только ingredient.id в виде List<String>
		
		if (errors.hasErrors()) {
			
			//addIngridientsToModel(model);
			
			//List<Ingredient> listIngredient=(List<Ingredient>) model.getAttribute("wrap");
			
			//List<Ingredient> listIngredient = new ArrayList<Ingredient>();
			
			if (!(design.getIngredients()==null)) {
			
			List<Ingredient> tacoIngredient=design.getIngredients();
			
			Type[] types = Ingredient.Type.values();
			
			for (Type type : types) {
				
				@SuppressWarnings("unchecked")
				List<Ingredient>listIngredient=(List<Ingredient>) model.getAttribute(type.toString().toLowerCase());
				
				listIngredient.forEach(i->{
					
					tacoIngredient.forEach(t->{
						
						if(t.getId().equals(i.getId())) {
							
							i.setChecked(true);
							
							//model.addAttribute(type.toString().toLowerCase(), i); 
							}
						
						});
					});
						
				}
			
			}
			
			return "design";
		}
		
		return "redirect:/orders/current";
	}

}
