package tech.csm.service;

import tech.csm.entity.User;

public interface UserService {
	User findByUsername(String username);

	User saveUser(User user);
}
