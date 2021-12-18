package tacos.data.interfaces.jpa;

import org.springframework.data.repository.CrudRepository;


import tacos.jpa.IngredientJPA;

/*CrudRepository<Ingredient, String> first parameter is Entity for persist,
 * second parameter type of Id element for this Entity.
 * For Ingredient class is Id is String -> CrudRepository<Ingredient, String>
 */
public interface IngredientRepositoryJPA extends CrudRepository<IngredientJPA, String> {

}
