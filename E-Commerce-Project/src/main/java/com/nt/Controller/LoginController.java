package com.nt.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.nt.Model.Role;
import com.nt.Model.User;
import com.nt.Repository.RoleRepository;
import com.nt.Repository.UserRepository;
import com.nt.global.GlobalData;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

@Autowired	

private BCryptPasswordEncoder bCryptPasswordEncoder;
@Autowired	
UserRepository UserRepository;
@Autowired	
RoleRepository RoleRepository;

@GetMapping("/login")
public String Login() {
	GlobalData.cart.clear();
	return"login";
}

@GetMapping("/register")
public String ragister() {
	return"register";
}
@PostMapping("/register")
public String registerPost(@ModelAttribute("user")User user,HttpServletRequest request)throws ServletException{
	String password=user.getPassword();
	user.setPassword(bCryptPasswordEncoder.encode(password));
	 List<Role> role=new ArrayList<>();
     role.add(RoleRepository.findById(2).get());
     user.setRoles(role);
     UserRepository.save(user);
     request.login(user.getEmail(), password);
	return"redirect:/home";
	
}
}
