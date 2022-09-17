package com.renova.demoApplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import com.renova.demoApplication.auth.CustomUserDetailsService;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;

	@Bean 
	public PasswordEncoder passwordEncoder() { 
	    return new BCryptPasswordEncoder(); 
	}
	
	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(customUserDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}
	
	@Override 
	protected void configure(HttpSecurity http) throws Exception {
		http.exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/e-commerce/login"));
		http.authorizeHttpRequests().antMatchers("/e-commerce/orders").hasAuthority("USER")
		.and().authorizeHttpRequests().antMatchers("/e-commerce/products").hasAuthority("USER")
		.and().authorizeHttpRequests().antMatchers("/e-commerce/previous_carts").hasAuthority("USER")
		.and().authorizeHttpRequests().antMatchers("/e-commerce/addCart").hasAuthority("USER")
		.and().authorizeHttpRequests().antMatchers("/e-commerce/add").hasAuthority("USER")
		.and().authorizeHttpRequests().antMatchers("/e-commerce/previous_cart").hasAuthority("USER")
		.and().authorizeHttpRequests().antMatchers("/e-commerce/update").hasAuthority("USER")
		.and().authorizeHttpRequests().antMatchers("/e-commerce/delete").hasAuthority("USER")
		.and().authorizeHttpRequests().antMatchers("/e-commerce/login").permitAll()
		.and().authorizeHttpRequests().antMatchers("/e-commerce/checkLogin").permitAll()
		.and().authorizeHttpRequests().anyRequest().authenticated()
		.and().httpBasic()
		.and().csrf().disable();
		http.formLogin().loginPage("/e-commerce/login")
		.defaultSuccessUrl("/e-commerce/products")
		.failureUrl("/e-commerce/login");
	}
}