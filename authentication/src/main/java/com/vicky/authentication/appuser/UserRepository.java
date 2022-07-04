package com.vicky.authentication.appuser;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByEmail(String email);

	@Transactional
	@Modifying
	@Query("UPDATE User u SET u.enabled=true WHERE u.email=?1")
	void enableUser(String email);
	
}
