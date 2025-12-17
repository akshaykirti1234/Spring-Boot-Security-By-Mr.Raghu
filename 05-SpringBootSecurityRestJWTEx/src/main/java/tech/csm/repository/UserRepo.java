package tech.csm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.csm.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	public Optional<User> findByUsername(String username);
}
