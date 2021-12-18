package tacos.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Taco")
public class TacoJPA {
	
	
	@NotNull
	@Size(min = 5, message="Name must be at least 5 characters long")
	private String name;
	
	
	
	@ManyToMany(targetEntity = IngredientJPA.class)
	@JoinTable(name="Taco_Ingredients",
				joinColumns=@JoinColumn(name="taco"),
				inverseJoinColumns = @JoinColumn(name="ingredient"))
	@NotEmpty(message = "You must choose at least 1 ingredient")
	@Size(min = 1, message="Name must be at least 1 ingredient")
	private List<IngredientJPA> ingredients;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name="ID")
	private Long id;
	
	@Column(name = "CREATEDAT")
	private Date createdAt;
	
	
	//For JPA using. Call  before Entity will be persist 
	@PrePersist
	private void createdAt() {
		
		this.createdAt=new Date();
	}

	public String getName() {
		return name;
	}

	public List<IngredientJPA> getIngredients() {
		return ingredients;
	}

	public Long getId() {
		return id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIngredients(List<IngredientJPA> ingredients) {
		this.ingredients = ingredients;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ingredients == null) ? 0 : ingredients.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TacoJPA other = (TacoJPA) obj;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ingredients == null) {
			if (other.ingredients != null)
				return false;
		} else if (!ingredients.equals(other.ingredients))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Taco [name=" + name + ", ingredients=" + ingredients + ", id=" + id + ", createdAt=" + createdAt + "]";
	}
	
	

}
