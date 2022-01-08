package tacos.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers("/design*","/orders*")
									.access("hasRole('ROLE_USER')")
									.antMatchers("/", "/**").access("permitAll")
									.and().formLogin().loginPage("/login").defaultSuccessUrl("/designjpa", true)
									.and().logout().logoutSuccessUrl("/")
									.and().csrf().ignoringAntMatchers("/h2-console/**")
									 .and()  
								        .headers()
								          .frameOptions()
								            .sameOrigin();
								            //.and().cors().and().csrf().disable(); or in
		//view need add <input type="hidden" name="_csrf" th:value="${_csrf.token}"/> for POST request work
	}
	
	
	
	
	@Bean
	public PasswordEncoder encoder() {
	return new BCryptPasswordEncoder();
	}
	
	
	@Autowired
	private UserDetailsService userDetailsSrvice;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsSrvice).passwordEncoder(encoder());
		
	}
	
	
	
	
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
	 *
	@Autowired
	DataSource dataSource; //default datasource 
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		
		auth.jdbcAuthentication().dataSource(dataSource);
	}*/
	
	
	
	
	
	
	
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
