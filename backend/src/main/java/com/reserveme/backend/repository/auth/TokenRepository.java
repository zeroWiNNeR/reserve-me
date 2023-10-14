package com.reserveme.backend.repository.auth;

import com.reserveme.backend.model.entity.auth.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

	Optional<Token> findByToken(String token);

	void deleteByTokenIgnoreCase(String token);

	@Modifying
	@Query(value = "delete from Token t where t.user.id = :userId")
	void deleteAllByUserId(@Param("userId") Long userId);

}
