package com.cadfuncionario.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter{
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("Admin")
		.password("{noop}739eb7610fc7").roles("USER");
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http.
			authorizeRequests()
			.antMatchers("/h2-console/**").permitAll()
			.antMatchers("/funcionarios/lista/**").permitAll() 
			.antMatchers("/swagger-ui.html/**").permitAll()
			.anyRequest().authenticated()
			.and().headers().frameOptions().sameOrigin()
			.and()
			.httpBasic()
				     .and()
			.csrf().disable();
	}
	



}
