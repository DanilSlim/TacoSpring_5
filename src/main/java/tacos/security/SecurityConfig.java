package tacos.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
	
	
	/* JDBC Authentication
	 * Used default security schema of Spring, and default DataSource 
	 * Using tables:
	 * 
	 *  users
	 *	username varchar(20) not null,
	 *	password varchar(20) not null,
	 *  enabled boolean 
	 *  
	 *  authorities(
	 *  username varchar(20) not null,
	 *	authority varchar(20) not null
	 */
	@Autowired
	DataSource dataSource; //default datasource 
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		
		auth.jdbcAuthentication().dataSource(dataSource);
	}
	
	
	
	
	/*
	 * //Authentication in memory
	 * @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		
		//Authentication in memory
		auth
		.inMemoryAuthentication()
		.withUser("buzz")
		.password("{noop}infinity")
		.authorities("ROLE_USER")
		.and()
		.withUser("woody")
		.password("{noop}bullseye")
		.authorities("ROLE_USER");
		
	}*/

}
