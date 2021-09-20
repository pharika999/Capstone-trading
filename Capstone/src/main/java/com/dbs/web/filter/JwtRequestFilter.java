package com.dbs.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dbs.web.services.AccountUserDetailsService;
import com.dbs.web.util.JwtUtil;




@Component
public class JwtRequestFilter extends OncePerRequestFilter{

	@Autowired
	private AccountUserDetailsService userservice;
	
	@Autowired
	private JwtUtil jwtutil;
	
	@Override
	// Authorization: Bearer <jwt token>
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String header = request.getHeader("Authorization");
		String username = null;
		String jwt = null;
		System.out.println("************ JWT FILTER ***************");
		System.out.println(header);
		response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
	    response.setHeader("Access-Control-Allow-Headers", "authorization, content-type, xsrf-token");
		if(header!=null && header.startsWith("Bearer"))
		{
			System.out.println("jwt exists");
			jwt = header.substring(7);
			System.out.println(jwt);
			username = jwtutil.extractUsername(jwt);
			System.out.println(username);
		}
		// /auth => authenticated => jwt back to the client => sends the jwt token and making a request for /cid
		if ( username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
		{
			System.out.println("username not null in jwt filter "+username);
			UserDetails userDetails = this.userservice.loadUserByUsername(username);
			System.out.println(jwt);
			System.out.println("USERDETAILS: "+userDetails);
			if(jwtutil.validateToken(jwt, userDetails)) {
				System.out.println("token validated");
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				System.out.println(usernamePasswordAuthenticationToken);
			}
		}
		// servlets, filter and listeners
		filterChain.doFilter(request, response);
	}
	

}
