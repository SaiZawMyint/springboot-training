package com.example.demo.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.services.auth.AuthenticationService;
import com.example.demo.services.auth.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	private final JwtService jwtService;
	private final AuthenticationService authService;

	// private final UserService userService;

	public JwtAuthFilter(JwtService jwtService, AuthenticationService authService) {
		this.jwtService = jwtService;
		this.authService = authService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) // when
																														// income
																														// req
			throws ServletException, IOException {

		if (noStartWithAPI(request)) { // nostartwithlogin
			filterChain.doFilter(request, response);
			return;
		}

		if (isPublicEndpoint(request)) { // noneedlogin
			filterChain.doFilter(request, response);
			return;
		}

		final String authHeader = request.getHeader("Authorization");

		try {
			final String jwt = authHeader.substring(7);
			final String userEmail = jwtService.extractUsername(jwt);

			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

			if (userEmail != null && authentication == null) {
				UserDetails userDetails = this.authService.loadUserByUsername(userEmail);

				if (jwtService.validateToken(jwt, userDetails)) {
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
							null, userDetails.getAuthorities());

					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
			}

			filterChain.doFilter(request, response); // gotocontroller
		} catch (Exception exception) {
			exception.printStackTrace();
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write("Invalid JWT token");
		}
	}

	private boolean isPublicEndpoint(HttpServletRequest request) {
		String path = request.getRequestURI();
		return path.startsWith("/api/auth/");
	}

	private boolean noStartWithAPI(HttpServletRequest request) {
		String path = request.getRequestURI();
		return !path.startsWith("/api/");
	}
}
