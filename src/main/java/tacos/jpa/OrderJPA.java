package tacos.jpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

@Entity
@Table(name = "Taco_Order")
public class OrderJPA {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Date placedAt;
	
	
	
	@NotBlank(message="Name is required")
	private String name;
	
	@NotBlank(message="Street is required")
	private String street;
	
	@NotBlank(message="City is required")
	private String city;
	
	@NotBlank(message="State is required")
	private String state;
	
	@NotBlank(message="Zip code is required")
	private String zip;
	
	@CreditCardNumber(message="Not a valid credit card number")
	private String ccNumber;
	
	@Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message="Must be formatted MM/YY")
	private String ccExpiration;
	
	@Digits(integer=3, fraction=0, message="Invalid CVV")
	private String ccCVV;
	
	@ManyToMany(targetEntity = TacoJPA.class,mappedBy = "ingredients")
	private List<TacoJPA> tacos = new ArrayList<>();
	
	
	//For JPA using. Call  before Entity will be persist 
	@PrePersist
	private void placedAt() {
		
		this.placedAt=new Date();
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getPlacedAt() {
		return placedAt;
	}

	public void setPlacedAt(Date placedAt) {
		this.placedAt = placedAt;
	}

	public void addDesign(TacoJPA design) {
		
		this.tacos.add(design);
		
	}
	
	public List<TacoJPA> getTacos(){
		
		return this.tacos ;
	}
	

	public String getName() {
		return name;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZip() {
		return zip;
	}

	public String getCcNumber() {
		return ccNumber;
	}

	public String getCcExpiration() {
		return ccExpiration;
	}

	public String getCcCVV() {
		return ccCVV;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}

	public void setCcExpiration(String ccExpiration) {
		this.ccExpiration = ccExpiration;
	}

	public void setCcCVV(String ccCVV) {
		this.ccCVV = ccCVV;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ccCVV == null) ? 0 : ccCVV.hashCode());
		result = prime * result + ((ccExpiration == null) ? 0 : ccExpiration.hashCode());
		result = prime * result + ((ccNumber == null) ? 0 : ccNumber.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + ((zip == null) ? 0 : zip.hashCode());
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
		OrderJPA other = (OrderJPA) obj;
		if (ccCVV == null) {
			if (other.ccCVV != null)
				return false;
		} else if (!ccCVV.equals(other.ccCVV))
			return false;
		if (ccExpiration == null) {
			if (other.ccExpiration != null)
				return false;
		} else if (!ccExpiration.equals(other.ccExpiration))
			return false;
		if (ccNumber == null) {
			if (other.ccNumber != null)
				return false;
		} else if (!ccNumber.equals(other.ccNumber))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		if (zip == null) {
			if (other.zip != null)
				return false;
		} else if (!zip.equals(other.zip))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderJPA [name=" + name + ", street=" + street + ", city=" + city + ", state=" + state + ", zip=" + zip
				+ ", ccNumber=" + ccNumber + ", ccExpiration=" + ccExpiration + ", ccCVV=" + ccCVV + "]";
	}
	
	

}
