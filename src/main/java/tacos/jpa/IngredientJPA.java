package tacos.jpa;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Ingredient")
public class IngredientJPA {
	
	@Id //For JPA using
	private final String id;
	
	private final String name;
	
	@Transient
	private boolean checked=false;
	
	@Enumerated(EnumType.STRING)
	private final Type type;
	
	public static enum Type {
	WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}
	
	
	//JPA требует конструктор по умолчанию
	@SuppressWarnings("unused")
	private IngredientJPA() {
		
		this.id=null;
		this.name=null;
		this.checked=false;
		this.type=null;
	}
	
	
	
	
	public IngredientJPA (String id,String name, Type type) {
		
		this.id=id;
		
		this.name=name;
		
		this.type=type;
		
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Type getType() {
		return type;
	}
	
	public boolean getChecked() {
		
		return this.checked;
	}
	
	
	public void setChecked(boolean checked) {
		
		this.checked=checked;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		IngredientJPA other = (IngredientJPA) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ingredient [id=" + id + ", name=" + name + ", type=" + type + "]";
	}
	
	

}
