package web.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;


import tacos.data.interfaces.jpa.IngredientRepositoryJPA;
import tacos.data.interfaces.jpa.TacoRepositoryJPA;
import tacos.jpa.IngredientJPA;
import tacos.jpa.OrderJPA;
import tacos.jpa.TacoJPA;
import tacos.jpa.IngredientJPA.Type;

@Controller
@RequestMapping("/designjpa")
@SessionAttributes("orderjpa")
public class DesignTacoControllerJPA {
	
	private final IngredientRepositoryJPA ingredientRepo;
	
	private final TacoRepositoryJPA tacoRepo;
	
	@Autowired
	public DesignTacoControllerJPA(IngredientRepositoryJPA ingredientRepo, TacoRepositoryJPA tacoRepo) {
		
		this.ingredientRepo=ingredientRepo;
		this.tacoRepo=tacoRepo;
	}
	
	@ModelAttribute(name = "orderjpa")
	  public OrderJPA order() {
	    return new OrderJPA();
	  }
	
	
	@GetMapping
	public String showDesignForm (Model model) {
		
		addIngredientsToModel(model);
		
		model.addAttribute("design", new TacoJPA());
		
		return "design";
	}
	
	
	@PostMapping
	public String processDesign(@Valid @ModelAttribute("design") TacoJPA design, Errors errors, Model model, @ModelAttribute("orderjpa") OrderJPA order) {
		
		if (errors.hasErrors()) {
			
			//addIngridientsToModel(model);
			
			//List<Ingredient> listIngredient=(List<Ingredient>) model.getAttribute("wrap");
			
			//List<Ingredient> listIngredient = new ArrayList<Ingredient>();
			
			if (!(design.getIngredients()==null)) {
			
			List<IngredientJPA> tacoIngredient=design.getIngredients();
			
			Type[] types = IngredientJPA.Type.values();
			
			for (Type type : types) {
				
				@SuppressWarnings("unchecked")
				List<IngredientJPA>listIngredient=(List<IngredientJPA>) model.getAttribute(type.toString().toLowerCase());
				
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
		
		TacoJPA savedTaco=tacoRepo.save(design);
		order.addDesign(savedTaco);
		
		return "redirect:/ordersjpa/current";
	}
	
	
	
	
	
	private List<IngredientJPA> filterByType(List<IngredientJPA>ingredients, Type type){
		
		List<IngredientJPA> filteredIngredients = new ArrayList<IngredientJPA>(); 

		for(IngredientJPA ingredient : ingredients) {
	
			if(type.toString().equals(ingredient.getType().toString()))  filteredIngredients.add(ingredient);
	
		}

		return filteredIngredients;

	}
	
	
	@ModelAttribute //Эти атрибуты будут присутствовать в каждой модели этого контроллера
	private void addIngredientsToModel(Model model) {
		
		List<IngredientJPA> ingredients=new ArrayList<>();//список ингридиентов из БД
		
		ingredientRepo.findAll().forEach(i->ingredients.add(i));
		
		Type [] types=IngredientJPA.Type.values();
		
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(),filterByType(ingredients, type));
			}
	}

}
