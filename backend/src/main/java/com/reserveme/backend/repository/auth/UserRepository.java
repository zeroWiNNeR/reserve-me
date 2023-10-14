package com.reserveme.backend.repository.auth;

import com.reserveme.backend.model.entity.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value = "SELECT u FROM User u WHERE UPPER(u.email) = UPPER(:email)")
	Optional<User> findByEmailIgnoreCase(@Param("email") String email);

	@Query(value = "SELECT u.id FROM User u WHERE UPPER(u.email) = UPPER(:email)")
	Long findUserIdByEmailIgnoreCase(@Param("email") String email);

}
