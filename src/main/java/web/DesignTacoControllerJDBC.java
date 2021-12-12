package web;

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

import tacos.Ingredient;
import tacos.Taco;
import tacos.Ingredient.Type;
import tacos.Order;
import tacos.data.interfaces.IngredientRepository;
import tacos.data.interfaces.TacoRepository;


@Controller
@RequestMapping("/designjdbc")
@SessionAttributes("order")
public class DesignTacoControllerJDBC {
	
	private final IngredientRepository ingredientRepo;
	
	private TacoRepository tacoRepo;
	
	@Autowired
	public DesignTacoControllerJDBC(IngredientRepository ingredientRepo, TacoRepository  tacoRepo) {
		
		this.ingredientRepo=ingredientRepo;
		
		this.tacoRepo=tacoRepo;
	}
	
	
	@ModelAttribute(name = "order")
	  public Order order() {
	    return new Order();
	  }
	
	
	@GetMapping
	public String showDesignForm (Model model) {
		
		addIngredientsToModel(model);
		
		model.addAttribute("design", new Taco());
		
		return "design";
	}
	
	
	@PostMapping
	public String processDesign(@Valid @ModelAttribute("design") Taco design, Errors errors, Model model, @ModelAttribute Order order) {
		
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
		
		Taco savedTaco=tacoRepo.save(design);
		order.addDesign(savedTaco);
		
		return "redirect:/ordersjdbc/current";
	}
	
	
	private List<Ingredient> filterByType(List<Ingredient>ingredients,Type type){
		
		List<Ingredient> filteredIngredients = new ArrayList<Ingredient>(); 

		for(Ingredient ingredient : ingredients) {
	
			if(type.toString().equals(ingredient.getType().toString()))  filteredIngredients.add(ingredient);
	
		}

		return filteredIngredients;

	}
	
	
	@ModelAttribute //Эти атрибуты будут присутствовать в каждой модели этого контроллера
	private void addIngredientsToModel(Model model) {
		
		List<Ingredient> ingredients=new ArrayList<>();//список ингридиентов из БД
		
		ingredientRepo.findAll().forEach(i->ingredients.add(i));
		
		Type [] types=Ingredient.Type.values();
		
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(),filterByType(ingredients, type));
			}
	}
}
