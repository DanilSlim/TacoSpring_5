package tacos;


public class Ingredient {
	
	private final String id;
	
	private final String name;
	
	private boolean checked=false;
	
	private final Type type;
	
	public static enum Type {
	WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}
	
	public Ingredient (String id,String name, Type type) {
		
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
		Ingredient other = (Ingredient) obj;
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
