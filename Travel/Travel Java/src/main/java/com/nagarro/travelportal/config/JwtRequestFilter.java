package com.nagarro.travelportal.config;

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

import com.nagarro.travelportal.Service.JwtUserDetailService;
import com.sun.istack.logging.Logger;

import io.jsonwebtoken.ExpiredJwtException;


/**
 * The Class JwtRequestFilter.
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	/** The log. */
	private Logger log = Logger.getLogger(JwtRequestFilter.class);
	
	/** The jwt user details service. */
	@Autowired
	private JwtUserDetailService jwtUserDetailsService;

	/** The jwt token util. */
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	/* (non-Javadoc)
	 * @see org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader("Authorization");

		String username = null;
		String jwtToken = null;
		/*
		 * JWT Token is in the form "Bearer token". Remove Bearer word and get only the
		 * Token
		 */
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				log.info("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				log.info("JWT Token has expired");
			}catch(Exception e){
			log.info(e.getMessage());		}
		} //else {
//			log.info("JWT Token does not begin with Bearer String");
//		}

		// Once we get the token validate it.
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

			/*
			 * if token is valid configure Spring Security to manually set authentication
			 */

			if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		chain.doFilter(request, response);
	}

}
