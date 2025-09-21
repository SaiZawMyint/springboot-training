package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.services.auth.AuthenticationService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	private final AuthenticationService authenticationService;
	private final JwtAuthFilter jwtAuthFilter;

	public SecurityConfig(AuthenticationService authService, JwtAuthFilter jwtAuthFilter) {
		authenticationService = authService;
		this.jwtAuthFilter = jwtAuthFilter;
	}

	@Bean
	@Order(1)
	public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
		http.securityMatcher("/api/**").csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	@Order(2)
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				authorize -> authorize.requestMatchers("/login", "/css/**", "/js/**", "/plugins/**").permitAll()

						.requestMatchers("/api/**").permitAll()

						// Product setup page -> only ADMIN
						.requestMatchers(HttpMethod.GET, "/product-setup").hasRole("ADMIN")
						.requestMatchers(HttpMethod.POST, "/porduct-setup").hasAnyRole("ADMIN", "MANAGER")
						// Edit + delete product -> ADMIN or MANAGER
						.requestMatchers("/edit/**", "/delete/**").hasAnyRole("ADMIN", "MANAGER") // USER

						// Product list -> both ADMIN and MANAGER can view
						.requestMatchers("/product-list").hasAnyRole("ADMIN", "MANAGER")

						// Everything else -> must be authenticated
						.anyRequest().authenticated())
				.formLogin(form -> form.loginPage("/login").permitAll()).logout(logout -> logout.permitAll());

		return http.build();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(authenticationService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
