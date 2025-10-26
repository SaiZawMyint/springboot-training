package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
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
				.authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
				.authenticationProvider(authenticationProvider())
			.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

	return http.build();
	}

	@Bean
	@Order(2)
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		// System.out.println(passwordEncoder().encode("12345678"));

		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers("/login", "/error", "/css/**", "/js/**", "/plugins/**", "/api/**").permitAll()

						// Product setup page -> only ADMIN

						/* .requestMatchers(HttpMethod.GET,"/product-setup").hasRole("ADMIN")
						 .requestMatchers(HttpMethod.POST, "/porduct-setup").hasAnyRole("ADMIN",
						 "MANAGER")

						 // Edit + delete product -> ADMIN or MANAGER
						 .requestMatchers("/edit/**", "/delete/**")
						 .hasAnyRole("ADMIN", "MANAGER")
						 // USER // Product list -> both
						 // ADMIN and MANAGER can view
						.requestMatchers("/product-list").hasAnyRole("ADMIN", "MANAGER")

						  .requestMatchers(HttpMethod.GET,"/user_setup").hasRole("ADMIN")
						 .requestMatchers(HttpMethod.POST, "/user_setup").hasAnyRole("ADMIN",
						 "MANAGER") .requestMatchers("/user_list").hasAnyRole("ADMIN", "MANAGER")

						  .requestMatchers(HttpMethod.GET,"/role_setup").hasRole("ADMIN")
						 .requestMatchers(HttpMethod.POST, "/role_setup").hasAnyRole("ADMIN",
						 "MANAGER") .requestMatchers("/role_list").hasAnyRole("ADMIN", "MANAGER")

						 .requestMatchers(HttpMethod.GET,"/productcategory-setup").hasRole("ADMIN")
						 .requestMatchers(HttpMethod.POST,
						"/productcategory_setup").hasAnyRole("ADMIN", "MANAGER")
						 .requestMatchers("/productcategory-list").hasAnyRole("ADMIN", "MANAGER")*/

						// Everything else -> must be authenticated
						.anyRequest().authenticated())
						.formLogin(form -> form.loginPage("/login")
						.loginProcessingUrl("/layouts/base-layout")
						.defaultSuccessUrl("/", false).failureUrl("/login?error=true").permitAll())
				.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login?logout").permitAll());

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