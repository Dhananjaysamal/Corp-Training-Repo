package com.example.demo.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
public class SecurityConfiguration 	extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
					.csrf().disable()
					.authorizeRequests()
					.antMatchers("/foos").hasRole("USER")
					.and()
					.httpBasic();
		}

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.inMemoryAuthentication()
					.withUser("user")
					.password("user")
					.roles("USER")
					.and()
					.withUser("admin")
					.password("admin")
					.roles("USER", "ADMIN");
		}
	}