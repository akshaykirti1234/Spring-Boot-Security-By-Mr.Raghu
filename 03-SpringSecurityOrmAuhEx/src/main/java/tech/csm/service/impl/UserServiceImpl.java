package tech.csm.service.impl;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tech.csm.model.User;
import tech.csm.repo.UserRepository;
import tech.csm.service.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public Optional<User> getUserByEmail(String email) {
		return userRepository.findByUserEmail(email);
	}

	@Override
	public User saveUser(User user) {
		user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userByEmail = getUserByEmail(username);
		if (userByEmail.isEmpty()) {
			throw new UsernameNotFoundException(username + "not found");
		}
		User user = userByEmail.get();

		return new org.springframework.security.core.userdetails.User(user.getUserEmail(), user.getUserPassword(),
				user.getUserRoles().stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toSet()));
	}

}
