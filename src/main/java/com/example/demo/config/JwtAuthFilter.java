package com.example.demo.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.api.response.BaseResponse;
import com.example.demo.services.auth.AuthenticationService;
import com.example.demo.services.auth.JwtService;
import com.example.demo.utils.CommonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	private final JwtService jwtService;
	private final AuthenticationService authService;
	private final ObjectMapper mapper;

	public JwtAuthFilter(JwtService jwtService, AuthenticationService authService) {
		this.jwtService = jwtService;
		this.authService = authService;
		mapper = new ObjectMapper();
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String path = request.getRequestURI();
		if(!path.startsWith("/api/")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		if (isPublicEndpoint(request)) {
			filterChain.doFilter(request, response);
			return;
		}

		final String authHeader = request.getHeader("Authorization");

		try {
			if(authHeader != null) {
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
			}else {
				BaseResponse<String> error = commandErrorResponse("", "Invalid token!");
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				String responseString = mapper.writeValueAsString(error); // json string
				response.getWriter().write(responseString);
				return;
			}
			
			filterChain.doFilter(request, response);
		} catch (Exception exception) {
			exception.printStackTrace();
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			
			
			String data = null;
			
			String responseString = mapper.writeValueAsString(commandErrorResponse(data, authHeader)); // json string
			
			response.getWriter().write(responseString);
		}
	}
	
	private <T> BaseResponse<T> commandErrorResponse(T data, String message){
		BaseResponse<T> errorResponse = new BaseResponse<T>();
		errorResponse.setStatusCode(-1);
		errorResponse.setSuccess(false);
		errorResponse.setMessage(message);
		errorResponse.setData(data);
		return errorResponse;
	}

	private boolean isPublicEndpoint(HttpServletRequest request) {
		String path = request.getRequestURI();
		return path.startsWith("/api/auth/");
	}

}
