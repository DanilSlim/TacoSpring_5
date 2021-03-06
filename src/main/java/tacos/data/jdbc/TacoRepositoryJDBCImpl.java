package tacos.data.jdbc;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import tacos.Ingredient;
import tacos.Taco;
import tacos.data.interfaces.TacoRepository;

@Repository
public class TacoRepositoryJDBCImpl implements TacoRepository {
	
	private JdbcTemplate jdbc;
	
	@Autowired
	public TacoRepositoryJDBCImpl(JdbcTemplate jdbc) {
		
		this.jdbc=jdbc;
	}

	@Override
	public Taco save(Taco design) {
		long tacoId = saveTacoInfo(design);
		design.setId(tacoId);
		
		for (Ingredient ingredient : design.getIngredients()) {
			saveIngredientToTaco(ingredient, tacoId);
			}
			return design;
	}
	
	
	private long saveTacoInfo(Taco taco) {
		
		taco.setCreatedAt(new Date());
		
		PreparedStatementCreatorFactory preparedStatementCreatorFactory= new PreparedStatementCreatorFactory(
				"insert into Taco (name, createdAt) values (?, ?)", Types.VARCHAR, Types.TIMESTAMP);
		
		//true - return ID for use in KeyHolder 
		preparedStatementCreatorFactory.setReturnGeneratedKeys(true);
		
		PreparedStatementCreator psc = preparedStatementCreatorFactory.newPreparedStatementCreator(
		Arrays.asList( taco.getName(), new Timestamp(taco.getCreatedAt().getTime()))); 
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbc.update(psc, keyHolder);
		
		
		return keyHolder.getKey().longValue();
		}
	
	
	private void saveIngredientToTaco(Ingredient ingredient, long tacoId) {
			jdbc.update("insert into Taco_Ingredients (taco, ingredient) values (?, ?)",
			tacoId, ingredient.getId());
			}

}
