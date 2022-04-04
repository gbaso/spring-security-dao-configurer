package com.example.daoconfigurer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DaoConfigurerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DaoConfigurerApplication.class, args);
	}

	@GetMapping
	public String hello(@AuthenticationPrincipal User user) {
		return "Hello " + user.getUsername() + "!";
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.httpBasic(basic -> {})
				.authorizeRequests(authorize -> authorize.anyRequest().authenticated())
				.authenticationProvider(firstProvider())
				.authenticationProvider(secondProvider())
				.build();
	}

	@Bean
	@SuppressWarnings("deprecation")
	public static UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withDefaultPasswordEncoder().username("user").password("user").roles("USER").build());
		return manager;
	}

	// @Bean
	public AuthenticationProvider firstProvider() {
		return new AuthenticationProvider() {

			@Override
			public boolean supports(Class<?> authentication) {
				return false;
			}

			@Override
			public Authentication authenticate(Authentication authentication) throws AuthenticationException {
				return null;
			}
		};
	}

	// @Bean
	public AuthenticationProvider secondProvider() {
		return new AuthenticationProvider() {

			@Override
			public boolean supports(Class<?> authentication) {
				return false;
			}

			@Override
			public Authentication authenticate(Authentication authentication) throws AuthenticationException {
				return null;
			}
		};
	}

}
