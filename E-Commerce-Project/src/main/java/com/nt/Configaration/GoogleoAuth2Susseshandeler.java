package com.nt.Configaration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.nt.Model.Role;
import com.nt.Model.User;
import com.nt.Repository.RoleRepository;
import com.nt.Repository.UserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class GoogleoAuth2Susseshandeler implements AuthenticationSuccessHandler{
@Autowired

UserRepository UserRepository;

@Autowired
RoleRepository RoleRepository;

private RedirectStrategy RedirectStretegy =new DefaultRedirectStrategy();


@Override
public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) throws IOException, ServletException {
	// TODO Auto-generated method stub
	OAuth2AuthenticationToken token =(OAuth2AuthenticationToken)authentication;
	String email=token.getPrincipal().getAttributes().get("email").toString();
	if( UserRepository.findUserByEmail(email).isPresent()) {
		
	}
	else {
		User user=new User();
		user.setFirstName(token.getPrincipal().getAttributes().get("given_name").toString());
		user.setLastName(token.getPrincipal().getAttributes().get("family_name").toString());
		user.setEmail(email);
         List<Role> role=new ArrayList<>();
         role.add(RoleRepository.findById(2).get());
         user.setRoles(role);
         UserRepository.save(user);
	}
	RedirectStretegy.sendRedirect(request, response, "/");
}
	
}
