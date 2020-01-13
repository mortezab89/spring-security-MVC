package com.mkyong.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.NullRememberMeServices;
import org.springframework.security.web.authentication.RememberMeServices;

@Configuration //can be removed
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	//encrypt password using BCrypt
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("mkyong").password(BCrypt.hashpw("123456", BCrypt.gensalt())).roles("USER");
		auth.inMemoryAuthentication().withUser("admin").password(BCrypt.hashpw("123456", BCrypt.gensalt())).roles("ADMIN");
		auth.inMemoryAuthentication().withUser("dba").password(BCrypt.hashpw("123456", BCrypt.gensalt())).roles("DBA");
	}


//	@Bean
//	public UserDetailsService userDetailsService() {
//		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//		manager.createUser(
//				User.withDefaultPasswordEncoder().username("admin").password("admin123").roles("ADMIN").build());
//		return manager;
//	}

	/*
	* formLogin -> creates login form
	* */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
//				.antMatchers("/index", "/user","/").permitAll()
				.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
					.antMatchers("/dba/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DBA')")

				.and()
				.formLogin()
				.loginPage("/login")

				.and()
				.rememberMe()
				.key("rem-me-key")
				.rememberMeParameter("remember")
				.rememberMeCookieName("rememberLogin")
//				.tokenValiditySeconds(86400)

				.and().logout().logoutUrl("j_spring_security_logout").logoutSuccessUrl("/");


	}
}