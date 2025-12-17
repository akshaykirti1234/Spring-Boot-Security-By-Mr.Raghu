package tech.csm.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tech.csm.entity.User;
import tech.csm.repository.UserRepo;
import tech.csm.service.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public User findByUsername(String username) {
		return userRepo.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
	}

	@Override
	public User saveUser(User user) {
		userRepo.findByUsername(user.getUsername()).ifPresent((u) -> {
			throw new RuntimeException("User already exists: " + user.getUsername());
		});

		user.setPassword(encoder.encode(user.getPassword()));
		return userRepo.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByUsername(username);
		List<SimpleGrantedAuthority> rolesList = user.getRole().stream().map(role -> new SimpleGrantedAuthority(role))
				.collect(Collectors.toList());
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				rolesList);
	}

}
