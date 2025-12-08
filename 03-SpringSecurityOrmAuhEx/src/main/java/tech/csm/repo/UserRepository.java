package tech.csm.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.csm.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUserEmail(String userEmail);
}
