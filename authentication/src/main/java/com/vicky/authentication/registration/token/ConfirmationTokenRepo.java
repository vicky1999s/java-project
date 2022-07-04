package com.vicky.authentication.registration.token;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationTokenRepo extends JpaRepository<ConfirmationToken, Long>{
	Optional<ConfirmationToken> findByToken(String token);
	
	@Modifying
	@Transactional
	@Query("UPDATE ConfirmationToken c SET c.confirmedAt=?1 WHERE c.token=?2")
	int updateConfirmedAt(LocalDateTime confirmedAt, String token);
}
