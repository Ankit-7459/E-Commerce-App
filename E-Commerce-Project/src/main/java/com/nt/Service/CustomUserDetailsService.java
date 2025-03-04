/**
 * 
 */
package com.nt.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nt.Model.CustomUserDetails;
import com.nt.Model.User;
import com.nt.Repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository UserRepository;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> user=UserRepository.findUserByEmail(email);
		user.orElseThrow(()->new UsernameNotFoundException("user not found"));
		return user.map(CustomUserDetails::new).get();
	}

}
