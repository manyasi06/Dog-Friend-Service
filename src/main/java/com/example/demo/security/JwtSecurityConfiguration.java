package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@EnableWebSecurity
public class JwtSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	 @Bean( name="myAuthenticationManager")
	 @Override
	 public AuthenticationManager authenticationManagerBean() throws Exception {
	     return super.authenticationManagerBean();
	 }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
			.addFilter(new JWTAuthorizationFilter(authenticationManager()))
			.authorizeRequests(authorizeRequests -> 
						authorizeRequests.anyRequest().authenticated()
					).sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
}
