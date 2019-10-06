package com.gsenas.ecommerce.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gsenas.ecommerce.model.User;
import com.gsenas.ecommerce.repository.UserRepository;

@Service
public class AuthService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optional = userRepository.findByEmail(username);
		if(optional.isPresent()) {
			return optional.get();
		}
		
		throw new UsernameNotFoundException("Username or password incorrect!");
		
	}

}
