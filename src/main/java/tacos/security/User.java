package tacos.security;


import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="Person")
public class User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;
	  
	  private final String username;
	  private final String password;
	  private final String fullname;
	  private final String street;
	  private final String city;
	  private final String state;
	  private final String zip;
	  private final String phone;
	  
	  
	  
	@SuppressWarnings("unused")
	private User() {
		  
		  this.username=null;
		  this.password=null;
		  this.fullname=null;
		  this.street=null;
		  this.city=null;
		  this.state=null;
		  this.zip=null;
		  this.phone=null;
		  
	  }
	  
	  public User (String username,String password, String fullname, String street, 
             String city, String state, String zip, String phone) {
		  
		  this.username=username;
		  this.password=password;
		  this.fullname=fullname;
		  this.street=street;
		  this.city=city;
		  this.state=state;
		  this.zip=zip;
		  this.phone=phone;
		  
	  }
	  
	  
	  
	  
	  
	


	public String getFullname() {
		return this.fullname;
	}

	public String getStreet() {
		return this.street;
	}

	public String getCity() {
		return this.city;
	}

	public String getState() {
		return this.state;
	}

	public String getZip() {
		return this.zip;
	}

	public String getPhoneNumber() {
		return this.phone;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getPassword() {
		
		return this.password;
	}

	@Override
	public String getUsername() {
		
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

}
