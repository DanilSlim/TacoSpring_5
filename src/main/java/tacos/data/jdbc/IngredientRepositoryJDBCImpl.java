package tacos.data.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import tacos.Ingredient;
import tacos.data.interfaces.IngredientRepository;

@Repository
public class IngredientRepositoryJDBCImpl implements IngredientRepository {
	
	private JdbcTemplate jdbc;
	
	@Autowired
	public  IngredientRepositoryJDBCImpl(JdbcTemplate jdbc) {
		
		this.jdbc=jdbc;
	}

	@Override
	public Iterable<Ingredient> findAll() {
		return jdbc.query("select id, name, type from Ingredient", this::mapRowToIngredient);
	}

	@Override
	public Ingredient findOne(String id) {
		return jdbc.queryForObject(
				"select id, name, type from Ingredient where id=?",
				//явная реализация RowMapper
				new RowMapper<Ingredient>() {
				public Ingredient mapRow(ResultSet rs, int rowNum)
				throws SQLException {
				return new Ingredient(
				rs.getString("id"),
				rs.getString("name"),
				Ingredient.Type.valueOf(rs.getString("type")));
				};
				}, id);
	}

	@Override
	public Ingredient save(Ingredient ingredient) {
		jdbc.update(
				"insert into Ingredient (id, name, type) values (?, ?, ?)",
				ingredient.getId(),
				ingredient.getName(),
				ingredient.getType().toString());
				return ingredient;
	}
	
	
	
	
	//реализация интерфейса RowMapper метод MapRow
	private Ingredient mapRowToIngredient(ResultSet rs, int rowNum)
			throws SQLException {
			return new Ingredient(
			rs.getString("id"),
			rs.getString("name"),
			Ingredient.Type.valueOf(rs.getString("type")));
			}

}
